package me.moallemi.carfinder.ui.cartype.browse.maintype

import me.moallemi.carfinder.domain.interactor.GetMainTypesUseCase
import me.moallemi.carfinder.domain.model.StringPagedResult
import me.moallemi.carfinder.model.MainTypeItem
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerViewModel
import javax.inject.Inject

class MainTypeViewModel @Inject constructor(private val getMainTypesUseCase: GetMainTypesUseCase) :
    BaseRecyclerViewModel<MainTypeItem, GetMainTypesUseCase.Params>() {

    init {
        useCases += getMainTypesUseCase
    }

    override fun makeData(params: GetMainTypesUseCase.Params) {
        getMainTypesUseCase.execute(
            params.apply { this.page = this@MainTypeViewModel.page },
            ::success,
            ::error
        )
    }

    private fun success(result: StringPagedResult) {
        handleSuccess(
            result.items.map { title -> MainTypeItem(title) }
        )
    }
}