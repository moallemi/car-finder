package me.moallemi.carfinder.ui.cartype.search.manufacturer

import android.os.Bundle
import me.moallemi.carfinder.domain.interactor.GetAllManufacturersUseCase
import me.moallemi.carfinder.extension.createSharedViewModel
import me.moallemi.carfinder.extension.createViewModel
import me.moallemi.carfinder.model.ManufacturerItem
import me.moallemi.carfinder.ui.SharedViewModel
import me.moallemi.carfinder.ui.base.listener.OnRecyclerItemClickListener
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerFragment
import me.moallemi.carfinder.ui.cartype.browse.manufacturer.ManufacturerListAdapter

class ManufacturerSearchFragment :
    BaseRecyclerFragment<ManufacturerItem, GetAllManufacturersUseCase.Params, ManufacturerSearchViewModel>(),
    OnRecyclerItemClickListener<ManufacturerItem> {

    private lateinit var sharedViewModel: SharedViewModel

    override val adapter = ManufacturerListAdapter()

    init {
        recyclerItemClickListener = this
        isEndless = false
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