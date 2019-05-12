package me.moallemi.carfinder.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import me.moallemi.carfinder.app.CarFinderApp
import me.moallemi.carfinder.app.executor.BackgroundThread
import me.moallemi.carfinder.app.executor.MainThread
import me.moallemi.carfinder.di.scope.AppScope
import me.moallemi.carfinder.domain.executor.PostExecutorThread
import me.moallemi.carfinder.domain.executor.UseCaseExecutorThread

@Module
class AppModule {

    @Provides
    @AppScope
    fun provideContext(application: CarFinderApp): Context = application.applicationContext

    @Provides
    @AppScope
    fun provideMainThread(): PostExecutorThread = MainThread()

    @Provides
    @AppScope
    fun provideBackgroundThread(): UseCaseExecutorThread = BackgroundThread()
}