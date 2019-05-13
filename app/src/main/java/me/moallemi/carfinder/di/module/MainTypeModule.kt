package me.moallemi.carfinder.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import me.moallemi.carfinder.di.annotation.ViewModelKey
import me.moallemi.carfinder.ui.cartype.browse.maintype.MainTypeBrowseFragment
import me.moallemi.carfinder.ui.cartype.browse.maintype.MainTypeViewModel

@Module
abstract class MainTypeModule {

    @ContributesAndroidInjector
    internal abstract fun mainTypeBrowseFragment(): MainTypeBrowseFragment

    @Binds
    @IntoMap
    @ViewModelKey(MainTypeViewModel::class)
    abstract fun bindManufacturerViewModel(mainTypeViewModel: MainTypeViewModel): ViewModel
}