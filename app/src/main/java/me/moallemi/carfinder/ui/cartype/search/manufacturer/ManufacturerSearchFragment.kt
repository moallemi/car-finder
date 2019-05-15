package me.moallemi.carfinder.ui.cartype.search.manufacturer

import android.os.Bundle
import me.moallemi.carfinder.domain.interactor.manufacturer.GetAllManufacturersUseCase
import me.moallemi.carfinder.extension.createSharedViewModel
import me.moallemi.carfinder.extension.createViewModel
import me.moallemi.carfinder.model.ManufacturerItem
import me.moallemi.carfinder.ui.cartype.base.SharedViewModel
import me.moallemi.carfinder.ui.base.listener.OnRecyclerItemClickListener
import me.moallemi.carfinder.ui.cartype.browse.manufacturer.ManufacturerBrowseAdapter
import me.moallemi.carfinder.ui.cartype.search.base.BaseSearchableRecyclerFragment

class ManufacturerSearchFragment :
    BaseSearchableRecyclerFragment<ManufacturerItem, GetAllManufacturersUseCase.Params, ManufacturerSearchViewModel>(),
    OnRecyclerItemClickListener<ManufacturerItem> {

    private lateinit var sharedViewModel: SharedViewModel

    override val adapter = ManufacturerBrowseAdapter()

    init {
        recyclerItemClickListener = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = createSharedViewModel()
    }

    override fun makeViewModel(): ManufacturerSearchViewModel {
        return createViewModel(viewModelFactory)
    }

    override fun getParams(): GetAllManufacturersUseCase.Params {
        return GetAllManufacturersUseCase.Params()
    }

    override fun onItemClick(item: ManufacturerItem) {
        sharedViewModel.selectManufacturerItem(item)
        activity?.onBackPressed()
    }

    companion object {
        fun newInstance() = ManufacturerSearchFragment()
    }
}