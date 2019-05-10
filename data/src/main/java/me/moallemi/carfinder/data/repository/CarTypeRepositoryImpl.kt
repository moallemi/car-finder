package me.moallemi.carfinder.data.repository

import io.reactivex.Single
import me.moallemi.carfinder.data.datasource.CarTypeRemoteDataSource
import me.moallemi.carfinder.domain.model.ManufacturerPagedResult
import me.moallemi.carfinder.domain.model.StringPagedResult
import me.moallemi.carfinder.domain.repository.CarTypeRepository
import javax.inject.Inject

class CarTypeRepositoryImpl @Inject constructor(
    private val carTypeRemoteDataSource: CarTypeRemoteDataSource
) : CarTypeRepository {

    override fun getManufacturers(page: Int, pageSize: Int): Single<ManufacturerPagedResult> {
        return carTypeRemoteDataSource.getManufacturers(page, pageSize)
    }

    override fun getMainTypes(manufacturer: String, page: Int, pageSize: Int): Single<StringPagedResult> {
        return carTypeRemoteDataSource.getMainTypes(manufacturer, page, pageSize)
    }

    override fun getBuiltDates(manufacturer: String, mainType: String): Single<List<String>> {
        return carTypeRemoteDataSource.getBuiltDates(manufacturer, mainType)
    }
}