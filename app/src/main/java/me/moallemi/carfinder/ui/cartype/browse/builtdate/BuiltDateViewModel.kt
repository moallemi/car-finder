package me.moallemi.carfinder.ui.cartype.browse.builtdate

import me.moallemi.carfinder.domain.interactor.GetBuiltDatesUseCase
import me.moallemi.carfinder.model.BuiltDateItem
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerViewModel
import javax.inject.Inject

class BuiltDateViewModel @Inject constructor(private val getBuiltDatesUseCase: GetBuiltDatesUseCase) :
    BaseRecyclerViewModel<BuiltDateItem, GetBuiltDatesUseCase.Params>() {

    init {
        useCases += getBuiltDatesUseCase
    }

    override fun makeData(params: GetBuiltDatesUseCase.Params) {
        getBuiltDatesUseCase.execute(params, ::success, ::error)
    }

    private fun success(result: List<String>) {
        handleSuccess(
            result.map { title -> BuiltDateItem(title) }
        )
    }
}