package me.moallemi.carfinder.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import me.moallemi.carfinder.R
import me.moallemi.carfinder.extension.createSharedViewModel
import me.moallemi.carfinder.extension.navigateTo
import me.moallemi.carfinder.extension.observe
import me.moallemi.carfinder.ui.SharedViewModel
import me.moallemi.carfinder.ui.base.BaseFragment
import me.moallemi.carfinder.ui.cartype.browse.maintype.MainTypeBrowseFragment
import me.moallemi.carfinder.ui.cartype.browse.maintype.MainTypeBrowseFragmentArgs
import me.moallemi.carfinder.ui.cartype.browse.manufacturer.ManufacturerBrowseFragment

class HomeFragment : BaseFragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectManufacturer.setOnClickListener {
            navigateTo(ManufacturerBrowseFragment.newInstance())
        }
        selectMainType.setOnClickListener {
            // TODO get correct manufacturer
            navigateTo(
                MainTypeBrowseFragment.newInstance(
                    MainTypeBrowseFragmentArgs(manufacturer.text.toString())
                )
            )
        }

        sharedViewModel = createSharedViewModel(viewModelFactory) {
            observe(manufacturerItem) {
                manufacturer.text = it?.name
            }
            observe(mainTypeItem) {
                mainType.text = it?.name
            }
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}