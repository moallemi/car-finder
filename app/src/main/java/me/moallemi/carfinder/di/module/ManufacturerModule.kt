package me.moallemi.carfinder.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import me.moallemi.carfinder.data.datasource.CarTypeRemoteDataSource
import me.moallemi.carfinder.data.repository.CarTypeRepositoryImpl
import me.moallemi.carfinder.di.annotation.ViewModelKey
import me.moallemi.carfinder.domain.repository.CarTypeRepository
import me.moallemi.carfinder.remote.datasource.CarTypeRemoteDataSourceImpl
import me.moallemi.carfinder.ui.cartype.browse.manufacturer.ManufacturerBrowseFragment
import me.moallemi.carfinder.ui.cartype.browse.manufacturer.ManufacturerViewModel

@Module
abstract class ManufacturerModule {

    @ContributesAndroidInjector
    internal abstract fun manufacturerBrowseFragment(): ManufacturerBrowseFragment

    @Binds
    @IntoMap
    @ViewModelKey(ManufacturerViewModel::class)
    abstract fun bindManufacturerViewModel(manufacturerViewModel: ManufacturerViewModel): ViewModel

    @Binds
    abstract fun bindCarTypeRepository(carTypeRepositoryImpl: CarTypeRepositoryImpl): CarTypeRepository

    @Binds
    abstract fun binCarTypeRemoteDataSource(
        carTypeRemoteDataSourceImpl: CarTypeRemoteDataSourceImpl
    ): CarTypeRemoteDataSource
}