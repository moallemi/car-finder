package me.moallemi.carfinder.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.moallemi.carfinder.R
import me.moallemi.carfinder.extension.inTransaction
import me.moallemi.carfinder.ui.cartype.browse.builtdate.BuiltDateBrowseFragmentArgs
import me.moallemi.carfinder.ui.cartype.search.builtdate.BuiltDateSearchFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState ?: supportFragmentManager?.inTransaction {
            replace(R.id.contentFrame, BuiltDateSearchFragment.newInstance(BuiltDateBrowseFragmentArgs("130", "X3")))
        }
    }
}
