package me.moallemi.carfinder.di.component

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import me.moallemi.carfinder.app.CarFinderApp
import me.moallemi.carfinder.di.module.AppModule

@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<CarFinderApp> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<CarFinderApp>
}