package me.moallemi.carfinder.ui.cartype.search.manufacturer

import me.moallemi.carfinder.domain.interactor.manufacturer.GetAllManufacturersUseCase
import me.moallemi.carfinder.domain.model.Manufacturer
import me.moallemi.carfinder.model.ManufacturerItem
import me.moallemi.carfinder.model.toManufactureItem
import me.moallemi.carfinder.ui.cartype.search.base.BaseSearchableRecyclerViewModel
import javax.inject.Inject

class ManufacturerSearchViewModel @Inject constructor(
    private val getAllManufacturersUseCase: GetAllManufacturersUseCase
) : BaseSearchableRecyclerViewModel<ManufacturerItem, GetAllManufacturersUseCase.Params>() {

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

    override fun search(query: String, items: List<ManufacturerItem>) {
        items.filter { manufacturerItem ->
            manufacturerItem.code.contains(query, ignoreCase = true) ||
                manufacturerItem.name.contains(query, ignoreCase = true)
        }.also { result ->
            handleSearchSuccess(
                result.mapIndexed { index: Int, manufacturerItem: ManufacturerItem ->
                    manufacturerItem.apply {
                        isEven = index % 2 == 0
                    }
                }
            )
        }
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