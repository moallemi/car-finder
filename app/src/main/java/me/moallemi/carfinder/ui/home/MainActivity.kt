package me.moallemi.carfinder.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.moallemi.carfinder.R
import me.moallemi.carfinder.ui.cartype.browse.manufacturer.ManufacturerBrowseFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager?.beginTransaction()
                ?.replace(R.id.contentFrame, ManufacturerBrowseFragment.newInstance())
                ?.commit()
        }
    }
}
