package me.moallemi.carfinder.ui.cartype.search.builtdate

import me.moallemi.carfinder.domain.interactor.GetAllBuiltDatesUseCase
import me.moallemi.carfinder.model.BuiltDateItem
import me.moallemi.carfinder.ui.cartype.search.base.BaseSearchableRecyclerViewModel
import javax.inject.Inject

class BuiltDateSearchViewModel @Inject constructor(
    private val getAllBuiltDatesUseCase: GetAllBuiltDatesUseCase
) : BaseSearchableRecyclerViewModel<BuiltDateItem, GetAllBuiltDatesUseCase.Params>() {

    init {
        useCases += getAllBuiltDatesUseCase
    }

    override fun makeData(params: GetAllBuiltDatesUseCase.Params) {
        getAllBuiltDatesUseCase.execute(
            params,
            ::success,
            ::error
        )
    }

    override fun search(query: String, items: List<BuiltDateItem>) {
        items.filter { mainTypeItem ->
            mainTypeItem.year.contains(query, ignoreCase = true)
        }.also { result ->
            handleSearchSuccess(result)
        }
    }

    private fun success(result: List<String>) {
        handleSuccess(
            result.map { title -> BuiltDateItem(title) }
        )
    }
}