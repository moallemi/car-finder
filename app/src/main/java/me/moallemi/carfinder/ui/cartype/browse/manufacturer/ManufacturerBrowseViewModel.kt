package me.moallemi.carfinder.ui.cartype.browse.manufacturer

import me.moallemi.carfinder.domain.interactor.manufacturer.GetManufacturersUseCase
import me.moallemi.carfinder.domain.model.Manufacturer
import me.moallemi.carfinder.domain.model.ManufacturerPagedResult
import me.moallemi.carfinder.model.ManufacturerItem
import me.moallemi.carfinder.model.toManufactureItem
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerViewModel
import javax.inject.Inject

class ManufacturerBrowseViewModel @Inject constructor(private val getManufacturersUseCase: GetManufacturersUseCase) :
    BaseRecyclerViewModel<ManufacturerItem, GetManufacturersUseCase.Params>() {

    init {
        useCases += getManufacturersUseCase
    }

    override fun makeData(params: GetManufacturersUseCase.Params) {
        getManufacturersUseCase.execute(
            params.apply { this.page = this@ManufacturerBrowseViewModel.page },
            ::success,
            ::error
        )
    }

    private fun success(result: ManufacturerPagedResult) {
        val currentItemSize = allItems?.data?.size ?: 0

        handleSuccess(
            result.items.mapIndexed { index: Int, manufacturer: Manufacturer ->
                manufacturer.toManufactureItem((currentItemSize + index) % 2 == 0)
            }
        )
    }
}