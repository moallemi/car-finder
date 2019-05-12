package me.moallemi.carfinder.di.module

import dagger.Module
import dagger.Provides
import me.moallemi.carfinder.di.scope.AppScope
import me.moallemi.carfinder.remote.api.cartype.CarTypeService
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    @AppScope
    fun provideCarServiceApi(retrofit: Retrofit): CarTypeService {
        return retrofit.create(CarTypeService::class.java)
    }
}