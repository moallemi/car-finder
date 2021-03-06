package me.moallemi.carfinder.data.repository

import io.reactivex.Observable
import io.reactivex.Single
import me.moallemi.carfinder.data.datasource.CarTypeLocalDataSource
import me.moallemi.carfinder.data.datasource.CarTypeRemoteDataSource
import me.moallemi.carfinder.domain.model.Manufacturer
import me.moallemi.carfinder.domain.model.ManufacturerPagedResult
import me.moallemi.carfinder.domain.model.StringPagedResult
import me.moallemi.carfinder.domain.repository.CarTypeRepository
import javax.inject.Inject

class CarTypeRepositoryImpl @Inject constructor(
    private val carTypeRemoteDataSource: CarTypeRemoteDataSource,
    private val carTypeLocalDataSource: CarTypeLocalDataSource
) : CarTypeRepository {

    override fun getManufacturers(page: Int, pageSize: Int): Single<ManufacturerPagedResult> {
        return carTypeRemoteDataSource.getManufacturers(page, pageSize)
    }

    override fun getManufacturersStream(): Observable<List<Manufacturer>> {
        return carTypeLocalDataSource.getManufacturers()
    }

    override fun storeManufacturers(items: List<Manufacturer>) {
        carTypeLocalDataSource.updateManufacturers(items)
    }

    override fun getMainTypes(manufacturer: String, page: Int, pageSize: Int): Single<StringPagedResult> {
        return carTypeRemoteDataSource.getMainTypes(manufacturer, page, pageSize)
    }

    override fun getMainTypesStream(manufacturerCode: String): Observable<List<String>> {
        return carTypeLocalDataSource.getMainTypes(manufacturerCode)
    }

    override fun storeMainTypes(manufacturerCode: String, items: List<String>) {
        carTypeLocalDataSource.updateMainTypes(manufacturerCode, items)
    }

    override fun getBuiltDates(manufacturer: String, mainType: String): Single<List<String>> {
        return carTypeRemoteDataSource.getBuiltDates(manufacturer, mainType)
    }

    override fun getBuiltDatesStream(manufacturerCode: String, mainType: String): Observable<List<String>> {
        return carTypeLocalDataSource.getBuiltDates(manufacturerCode, mainType)
    }

    override fun storeBuiltDates(manufacturerCode: String, mainType: String, items: List<String>) {
        carTypeLocalDataSource.updateBuiltDates(manufacturerCode, mainType, items)
    }
}