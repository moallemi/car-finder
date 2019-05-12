package me.moallemi.carfinder.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.moallemi.carfinder.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager?.beginTransaction()
            ?.replace(R.id.contentFrame, HomeFragment.newInstance())
            ?.commit()
    }
}
