package me.moallemi.carfinder.ui.cartype.browse.manufacturer

import me.moallemi.carfinder.domain.interactor.GetManufacturersUseCase
import me.moallemi.carfinder.domain.model.ManufacturerPagedResult
import me.moallemi.carfinder.model.ManufacturerItem
import me.moallemi.carfinder.model.toManufactureItem
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerViewModel
import javax.inject.Inject

class ManufacturerViewModel @Inject constructor(private val getManufacturersUseCase: GetManufacturersUseCase) :
    BaseRecyclerViewModel<ManufacturerItem, GetManufacturersUseCase.Params>() {

    init {
        useCases += getManufacturersUseCase
    }

    override fun makeData(params: GetManufacturersUseCase.Params) {
        getManufacturersUseCase.execute(
            params.apply { this.page = this@ManufacturerViewModel.page },
            ::success,
            ::error
        )
    }

    private fun success(result: ManufacturerPagedResult) {
        handleSuccess(
            result.items.map { manufacturer -> manufacturer.toManufactureItem() }
        )
    }
}