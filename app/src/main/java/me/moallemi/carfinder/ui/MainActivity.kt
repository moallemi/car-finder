package me.moallemi.carfinder.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.moallemi.carfinder.R
import me.moallemi.carfinder.extension.inTransaction
import me.moallemi.carfinder.ui.cartype.search.manufacturer.ManufacturerSearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: supportFragmentManager?.inTransaction {
            replace(R.id.contentFrame, ManufacturerSearchFragment.newInstance())
        }
    }
}
