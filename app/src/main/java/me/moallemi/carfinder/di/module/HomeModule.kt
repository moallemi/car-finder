package me.moallemi.carfinder.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.moallemi.carfinder.ui.summary.SummaryFragment

@Module
abstract class HomeModule {

    @ContributesAndroidInjector
    internal abstract fun homeFragment(): SummaryFragment
}