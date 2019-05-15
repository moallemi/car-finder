package me.moallemi.carfinder.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_home.*
import me.moallemi.carfinder.R
import me.moallemi.carfinder.extension.createSharedViewModel
import me.moallemi.carfinder.extension.navigateTo
import me.moallemi.carfinder.ui.base.BaseFragment
import me.moallemi.carfinder.ui.cartype.base.SharedViewModel
import me.moallemi.carfinder.ui.summary.SummaryFragment
import me.moallemi.carfinder.ui.summary.SummaryFragmentArgs

class HomeFragment : BaseFragment() {

    lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = createSharedViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectMainTask.setOnClickListener {
            sharedViewModel.clearAllData()

            navigateTo(
                SummaryFragment.newInstance(
                    SummaryFragmentArgs(SummaryFragment.Mode.BROWSE)
                )
            )
        }

        selectOptionalTask.setOnClickListener {
            sharedViewModel.clearAllData()

            navigateTo(
                SummaryFragment.newInstance(
                    SummaryFragmentArgs(SummaryFragment.Mode.SEARCH)
                )
            )
        }
    }
}
