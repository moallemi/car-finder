package me.moallemi.carfinder.di.component

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.moallemi.carfinder.app.CarFinderApp
import me.moallemi.carfinder.di.module.ApiModule
import me.moallemi.carfinder.di.module.AppModule
import me.moallemi.carfinder.di.module.HomeModule
import me.moallemi.carfinder.di.module.ManufacturerModule
import me.moallemi.carfinder.di.module.NetworkModule
import me.moallemi.carfinder.di.module.SharedViewModelModule
import me.moallemi.carfinder.di.module.ViewModelFactoryModule
import me.moallemi.carfinder.di.scope.AppScope

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ApiModule::class,
        ViewModelFactoryModule::class,
        HomeModule::class,
        ManufacturerModule::class,
        SharedViewModelModule::class
    ]
)
@AppScope
interface AppComponent : AndroidInjector<CarFinderApp> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<CarFinderApp>
}