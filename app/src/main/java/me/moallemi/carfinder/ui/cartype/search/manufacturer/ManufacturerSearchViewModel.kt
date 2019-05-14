package me.moallemi.carfinder.ui.cartype.search.manufacturer

import me.moallemi.carfinder.domain.interactor.GetAllManufacturersUseCase
import me.moallemi.carfinder.domain.model.Manufacturer
import me.moallemi.carfinder.model.ManufacturerItem
import me.moallemi.carfinder.model.toManufactureItem
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerViewModel
import javax.inject.Inject

class ManufacturerSearchViewModel @Inject constructor(
    private val getAllManufacturersUseCase: GetAllManufacturersUseCase
) : BaseRecyclerViewModel<ManufacturerItem, GetAllManufacturersUseCase.Params>() {

    init {
        useCases += getAllManufacturersUseCase
    }

    override fun makeData(params: GetAllManufacturersUseCase.Params) {
        getAllManufacturersUseCase.execute(
            params,
            ::success,
            ::error
        )
    }

    private fun success(result: List<Manufacturer>) {
        val currentItemSize = allItems?.data?.size ?: 0

        handleSuccess(
            result.mapIndexed { index: Int, manufacturer: Manufacturer ->
                manufacturer.toManufactureItem((currentItemSize + index) % 2 == 0)
            }
        )
    }
}