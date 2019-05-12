package me.moallemi.carfinder.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import me.moallemi.carfinder.di.component.DaggerAppComponent

class CarFinderApp : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}