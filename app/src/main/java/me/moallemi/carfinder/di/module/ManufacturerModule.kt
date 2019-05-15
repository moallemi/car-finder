package me.moallemi.carfinder.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import me.moallemi.carfinder.data.datasource.CarTypeLocalDataSource
import me.moallemi.carfinder.data.datasource.CarTypeRemoteDataSource
import me.moallemi.carfinder.data.repository.CarTypeRepositoryImpl
import me.moallemi.carfinder.di.annotation.ViewModelKey
import me.moallemi.carfinder.domain.repository.CarTypeRepository
import me.moallemi.carfinder.local.datasource.CarTypeLocalDataSourceImpl
import me.moallemi.carfinder.remote.datasource.CarTypeRemoteDataSourceImpl
import me.moallemi.carfinder.ui.cartype.browse.manufacturer.ManufacturerBrowseFragment
import me.moallemi.carfinder.ui.cartype.browse.manufacturer.ManufacturerBrowseViewModel
import me.moallemi.carfinder.ui.cartype.search.manufacturer.ManufacturerSearchFragment
import me.moallemi.carfinder.ui.cartype.search.manufacturer.ManufacturerSearchViewModel

@Module
abstract class ManufacturerModule {

    @ContributesAndroidInjector
    internal abstract fun manufacturerBrowseFragment(): ManufacturerBrowseFragment

    @Binds
    @IntoMap
    @ViewModelKey(ManufacturerBrowseViewModel::class)
    abstract fun bindManufacturerListViewModel(manufacturerBrowseViewModel: ManufacturerBrowseViewModel): ViewModel

    @ContributesAndroidInjector
    internal abstract fun manufacturerSearchFragment(): ManufacturerSearchFragment

    @Binds
    @IntoMap
    @ViewModelKey(ManufacturerSearchViewModel::class)
    abstract fun bindManufacturerSearchViewModel(manufacturerSearchViewModel: ManufacturerSearchViewModel): ViewModel

    @Binds
    abstract fun bindCarTypeRepository(carTypeRepositoryImpl: CarTypeRepositoryImpl): CarTypeRepository

    @Binds
    abstract fun binCarTypeRemoteDataSource(
        carTypeRemoteDataSourceImpl: CarTypeRemoteDataSourceImpl
    ): CarTypeRemoteDataSource

    @Binds
    abstract fun binCarTypeLocalDataSource(
        carTypeLocalDataSourceImpl: CarTypeLocalDataSourceImpl
    ): CarTypeLocalDataSource
}