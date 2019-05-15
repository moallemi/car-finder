package me.moallemi.carfinder.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import me.moallemi.carfinder.di.annotation.ViewModelKey
import me.moallemi.carfinder.ui.cartype.browse.builtdate.BuiltDateBrowseFragment
import me.moallemi.carfinder.ui.cartype.browse.builtdate.BuiltDateViewModel
import me.moallemi.carfinder.ui.cartype.search.builtdate.BuiltDateSearchFragment
import me.moallemi.carfinder.ui.cartype.search.builtdate.BuiltDateSearchViewModel

@Module
abstract class BuiltDateModule {

    @ContributesAndroidInjector
    internal abstract fun builtDateBrowseFragment(): BuiltDateBrowseFragment

    @Binds
    @IntoMap
    @ViewModelKey(BuiltDateViewModel::class)
    abstract fun bindBuiltDateViewModel(builtDateViewModel: BuiltDateViewModel): ViewModel

    @ContributesAndroidInjector
    internal abstract fun builtDateSearchFragment(): BuiltDateSearchFragment

    @Binds
    @IntoMap
    @ViewModelKey(BuiltDateSearchViewModel::class)
    abstract fun bindBuiltDateSearchViewModel(builtDateSearchViewModel: BuiltDateSearchViewModel): ViewModel
}