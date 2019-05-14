package me.moallemi.carfinder.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import me.moallemi.carfinder.di.scope.AppScope
import me.moallemi.carfinder.local.db.AppDatabase

@Module
class DatabaseModule {

    @Provides
    @AppScope
    fun providesDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
}