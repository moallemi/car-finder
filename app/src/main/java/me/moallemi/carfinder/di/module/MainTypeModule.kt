package me.moallemi.carfinder.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import me.moallemi.carfinder.di.annotation.ViewModelKey
import me.moallemi.carfinder.ui.cartype.browse.maintype.MainTypeBrowseFragment
import me.moallemi.carfinder.ui.cartype.browse.maintype.MainTypeViewModel
import me.moallemi.carfinder.ui.cartype.search.maintype.MainTypeSearchFragment
import me.moallemi.carfinder.ui.cartype.search.maintype.MainTypeSearchViewModel

@Module
abstract class MainTypeModule {

    @ContributesAndroidInjector
    internal abstract fun mainTypeBrowseFragment(): MainTypeBrowseFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainTypeViewModel::class)
    abstract fun bindMainTypeBrowseViewModel(mainTypeViewModel: MainTypeViewModel): ViewModel

    @ContributesAndroidInjector
    internal abstract fun mainTypeSearchFragment(): MainTypeSearchFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainTypeSearchViewModel::class)
    abstract fun bindMainTypeSearchViewModel(mainTypeSearchViewModel: MainTypeSearchViewModel): ViewModel
}