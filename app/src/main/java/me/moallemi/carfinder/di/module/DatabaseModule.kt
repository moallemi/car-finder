package me.moallemi.carfinder.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import me.moallemi.carfinder.di.scope.AppScope
import me.moallemi.carfinder.local.dao.BuiltDateDao
import me.moallemi.carfinder.local.dao.MainTypeDao
import me.moallemi.carfinder.local.dao.ManufacturerDao
import me.moallemi.carfinder.local.db.AppDatabase

@Module
class DatabaseModule {

    @Provides
    @AppScope
    fun providesDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @AppScope
    fun providesManufacturerDao(appDatabase: AppDatabase): ManufacturerDao {
        return appDatabase.manufacturerDao()
    }

    @Provides
    @AppScope
    fun providesMainTypeDao(appDatabase: AppDatabase): MainTypeDao {
        return appDatabase.mainTypeDao()
    }

    @Provides
    @AppScope
    fun providesBuiltDateDao(appDatabase: AppDatabase): BuiltDateDao {
        return appDatabase.builtDateDao()
    }
}