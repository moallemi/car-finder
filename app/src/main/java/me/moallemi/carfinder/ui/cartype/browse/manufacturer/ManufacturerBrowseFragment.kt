package me.moallemi.carfinder.ui.cartype.browse.manufacturer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.moallemi.carfinder.R
import me.moallemi.carfinder.extension.createViewModel
import me.moallemi.carfinder.ui.base.BaseFragment

class ManufacturerBrowseFragment : BaseFragment() {

    lateinit var viewModel: ManufacturerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = createViewModel(viewModelFactory)
        viewModel.load()
    }

    companion object {
        fun newInstance() = ManufacturerBrowseFragment()
    }
}