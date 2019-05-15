package me.moallemi.carfinder.ui.cartype.browse.manufacturer

import android.os.Bundle
import me.moallemi.carfinder.domain.interactor.manufacturer.GetManufacturersUseCase
import me.moallemi.carfinder.extension.createSharedViewModel
import me.moallemi.carfinder.extension.createViewModel
import me.moallemi.carfinder.model.ManufacturerItem
import me.moallemi.carfinder.ui.cartype.base.SharedViewModel
import me.moallemi.carfinder.ui.base.listener.OnRecyclerItemClickListener
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerFragment

class ManufacturerBrowseFragment :
    BaseRecyclerFragment<ManufacturerItem, GetManufacturersUseCase.Params, ManufacturerBrowseViewModel>(),
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

    override fun makeViewModel(): ManufacturerBrowseViewModel {
        return createViewModel(viewModelFactory)
    }

    override fun getParams(): GetManufacturersUseCase.Params {
        return GetManufacturersUseCase.Params(0, 15)
    }

    override fun onItemClick(item: ManufacturerItem) {
        sharedViewModel.selectManufacturerItem(item)
        activity?.onBackPressed()
    }

    companion object {
        fun newInstance() = ManufacturerBrowseFragment()
    }
}