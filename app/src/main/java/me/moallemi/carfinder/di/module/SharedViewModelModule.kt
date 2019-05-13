package me.moallemi.carfinder.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.moallemi.carfinder.di.annotation.ViewModelKey
import me.moallemi.carfinder.ui.SharedViewModel

@Module
abstract class SharedViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    abstract fun bindSharedViewModel(sharedViewModel: SharedViewModel): ViewModel
}