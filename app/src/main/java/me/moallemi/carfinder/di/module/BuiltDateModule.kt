package me.moallemi.carfinder.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import me.moallemi.carfinder.di.annotation.ViewModelKey
import me.moallemi.carfinder.ui.cartype.browse.builtdate.BuiltDateBrowseFragment
import me.moallemi.carfinder.ui.cartype.browse.builtdate.BuiltDateViewModel

@Module
abstract class BuiltDateModule {

    @ContributesAndroidInjector
    internal abstract fun builtDateBrowseFragment(): BuiltDateBrowseFragment

    @Binds
    @IntoMap
    @ViewModelKey(BuiltDateViewModel::class)
    abstract fun bindManufacturerViewModel(builtDateViewModel: BuiltDateViewModel): ViewModel
}