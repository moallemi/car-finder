package me.moallemi.carfinder.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment_summary.*
import me.moallemi.carfinder.R
import me.moallemi.carfinder.extension.createSharedViewModel
import me.moallemi.carfinder.extension.navigateTo
import me.moallemi.carfinder.extension.observe
import me.moallemi.carfinder.model.BuiltDateItem
import me.moallemi.carfinder.model.MainTypeItem
import me.moallemi.carfinder.model.ManufacturerItem
import me.moallemi.carfinder.ui.SharedViewModel
import me.moallemi.carfinder.ui.base.BaseFragment
import me.moallemi.carfinder.ui.cartype.browse.builtdate.BuiltDateBrowseFragment
import me.moallemi.carfinder.ui.cartype.browse.builtdate.BuiltDateBrowseFragmentArgs
import me.moallemi.carfinder.ui.cartype.browse.maintype.MainTypeBrowseFragment
import me.moallemi.carfinder.ui.cartype.browse.maintype.MainTypeBrowseFragmentArgs
import me.moallemi.carfinder.ui.cartype.browse.manufacturer.ManufacturerBrowseFragment

class SummaryFragment : BaseFragment() {

    private var manufacturerItem: ManufacturerItem? = null
    private var mainTypeItem: MainTypeItem? = null
    private var builtDateItem: BuiltDateItem? = null

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = createSharedViewModel {
            observe(manufacturerItem) {
                if (it != null) {
                    manufacturer.text = it.name
                    this@SummaryFragment.manufacturerItem = it

                    mainTypeGroup.isVisible = true
                    mainType.setText(R.string.select)
                    this@SummaryFragment.mainTypeItem = null

                    builtDateGroup.isVisible = false
                    this@SummaryFragment.builtDateItem = null
                }
            }
            observe(mainTypeItem) {
                if (it != null) {
                    mainType.text = it.name
                    this@SummaryFragment.mainTypeItem = it

                    builtDateGroup.isVisible = true
                    builtDate.setText(R.string.select)
                    this@SummaryFragment.builtDateItem = null
                }
            }
            observe(builtDateItem) {
                if (it != null) {
                    builtDate.text = it.year
                    this@SummaryFragment.builtDateItem = it
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putSerializable(KEY_MANUFACTURER_DATA, manufacturerItem)
        outState.putSerializable(KEY_MAIN_TYPE_DATA, mainTypeItem)
        outState.putSerializable(KEY_BUILT_DATE_DATA, builtDateItem)
    }

    override fun onRestoreState(bundle: Bundle) {
        super.onRestoreState(bundle)

        manufacturerItem = bundle.getSerializable(KEY_MANUFACTURER_DATA) as? ManufacturerItem
        mainTypeItem = bundle.getSerializable(KEY_MAIN_TYPE_DATA) as? MainTypeItem
        builtDateItem = bundle.getSerializable(KEY_BUILT_DATE_DATA) as? BuiltDateItem
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            onRestoreState(savedInstanceState)
        }

        selectManufacturer.setOnClickListener {
            navigateTo(ManufacturerBrowseFragment.newInstance())
        }
        selectMainType.setOnClickListener {
            navigateTo(
                MainTypeBrowseFragment.newInstance(
                    MainTypeBrowseFragmentArgs(manufacturerItem!!.code)
                )
            )
        }
        selectBuiltDate.setOnClickListener {
            navigateTo(
                BuiltDateBrowseFragment.newInstance(
                    BuiltDateBrowseFragmentArgs(
                        manufacturerItem!!.code,
                        mainTypeItem!!.name
                    )
                )
            )
        }
    }

    companion object {
        fun newInstance() = SummaryFragment()

        private const val KEY_MANUFACTURER_DATA = "manufacturerData"
        private const val KEY_MAIN_TYPE_DATA = "mainTypeData"
        private const val KEY_BUILT_DATE_DATA = "builtDateData"
    }
}