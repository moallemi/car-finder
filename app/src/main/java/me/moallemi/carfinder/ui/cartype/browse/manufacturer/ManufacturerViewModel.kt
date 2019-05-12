package me.moallemi.carfinder.ui.cartype.browse.manufacturer

import me.moallemi.carfinder.domain.interactor.GetManufacturersUseCase
import me.moallemi.carfinder.domain.model.ManufacturerPagedResult
import me.moallemi.carfinder.ui.base.BaseViewModel
import javax.inject.Inject

class ManufacturerViewModel @Inject constructor(private val getManufacturersUseCase: GetManufacturersUseCase) :
    BaseViewModel() {

    init {
        useCases += getManufacturersUseCase
    }

    fun load() {
        getManufacturersUseCase.execute(GetManufacturersUseCase.Params(0, 15), ::success, ::error)
    }

    private fun success(result: ManufacturerPagedResult) {
        println(result)
    }

    private fun error(throwable: Throwable) {
        println(throwable)
    }
}