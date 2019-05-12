package me.moallemi.carfinder.ui.cartype.browse.manufacturer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.moallemi.carfinder.domain.interactor.GetManufacturersUseCase
import me.moallemi.carfinder.domain.model.Manufacturer
import me.moallemi.carfinder.domain.model.ManufacturerPagedResult
import me.moallemi.carfinder.model.Resource
import me.moallemi.carfinder.model.ResourceState
import me.moallemi.carfinder.ui.base.BaseViewModel
import javax.inject.Inject

class ManufacturerViewModel @Inject constructor(private val getManufacturersUseCase: GetManufacturersUseCase) :
    BaseViewModel() {

    private val _items = MutableLiveData<Resource<List<Manufacturer>>>()
    val items: LiveData<Resource<List<Manufacturer>>> = _items

    init {
        useCases += getManufacturersUseCase
    }

    fun load() {
        _items.value = Resource(ResourceState.LOADING)
        getManufacturersUseCase.execute(GetManufacturersUseCase.Params(0, 15), ::success, ::error)
    }

    private fun success(result: ManufacturerPagedResult) {
        _items.value = Resource(ResourceState.SUCCESS, result.items)
    }

    private fun error(throwable: Throwable) {
        _items.value = Resource(ResourceState.ERROR, failure = throwable)
    }
}