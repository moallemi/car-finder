package me.moallemi.carfinder.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import me.moallemi.carfinder.R
import me.moallemi.carfinder.extension.navigateTo
import me.moallemi.carfinder.ui.summary.SummaryFragment
import me.moallemi.carfinder.ui.summary.SummaryFragmentArgs

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectMainTask.setOnClickListener {
            navigateTo(
                SummaryFragment.newInstance(
                    SummaryFragmentArgs(SummaryFragment.Mode.BROWSE)
                )
            )
        }

        selectOptionalTask.setOnClickListener {
            navigateTo(
                SummaryFragment.newInstance(
                    SummaryFragmentArgs(SummaryFragment.Mode.SEARCH)
                )
            )
        }
    }
}
