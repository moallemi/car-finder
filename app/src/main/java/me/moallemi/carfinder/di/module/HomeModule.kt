package me.moallemi.carfinder.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import me.moallemi.carfinder.di.annotation.ViewModelKey
import me.moallemi.carfinder.ui.home.HomeFragment
import me.moallemi.carfinder.ui.home.HomeViewModel

@Module
abstract class HomeModule {

    @ContributesAndroidInjector
    internal abstract fun homeFragment(): HomeFragment

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel
}