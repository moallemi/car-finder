package me.moallemi.carfinder.ui.cartype.browse.manufacturer

import me.moallemi.carfinder.domain.interactor.GetManufacturersUseCase
import me.moallemi.carfinder.extension.createViewModel
import me.moallemi.carfinder.model.ManufacturerItem
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerFragment

class ManufacturerBrowseFragment :
    BaseRecyclerFragment<ManufacturerItem, GetManufacturersUseCase.Params, ManufacturerViewModel>() {

    override val adapter = ManufacturerListAdapter()

    override fun makeViewModel(): ManufacturerViewModel {
        return createViewModel(viewModelFactory)
    }

    override fun getParams(): GetManufacturersUseCase.Params {
        return GetManufacturersUseCase.Params(0, 15)
    }

    companion object {
        fun newInstance() = ManufacturerBrowseFragment()
    }
}