package me.moallemi.carfinder.ui.cartype.search.maintype

import me.moallemi.carfinder.domain.interactor.maintype.GetAllMainTypesUseCase
import me.moallemi.carfinder.model.MainTypeItem
import me.moallemi.carfinder.ui.cartype.search.base.BaseSearchableRecyclerViewModel
import javax.inject.Inject

class MainTypeSearchViewModel @Inject constructor(
    private val getAllMainTypesUseCase: GetAllMainTypesUseCase
) : BaseSearchableRecyclerViewModel<MainTypeItem, GetAllMainTypesUseCase.Params>() {

    init {
        useCases += getAllMainTypesUseCase
    }

    override fun makeData(params: GetAllMainTypesUseCase.Params) {
        getAllMainTypesUseCase.execute(
            params,
            ::success,
            ::error
        )
    }

    override fun search(query: String, items: List<MainTypeItem>) {
        items.filter { mainTypeItem ->
            mainTypeItem.name.contains(query, ignoreCase = true)
        }.also { result ->
            handleSearchSuccess(
                result.mapIndexed { index: Int, mainTypeItem: MainTypeItem ->
                    mainTypeItem.apply {
                        isEven = index % 2 == 0
                    }
                }
            )
        }
    }

    private fun success(result: List<String>) {
        val currentItemSize = allItems?.data?.size ?: 0

        handleSuccess(
            result.mapIndexed { index: Int, title ->
                MainTypeItem(title, (currentItemSize + index) % 2 == 0)
            }
        )
    }
}