package me.moallemi.carfinder.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.moallemi.carfinder.R
import me.moallemi.carfinder.ui.summary.SummaryFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: supportFragmentManager?.beginTransaction()
            ?.replace(R.id.contentFrame, SummaryFragment.newInstance())
            ?.commit()
    }
}
