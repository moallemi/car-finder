package me.moallemi.carfinder.domain.repository

import io.reactivex.Observable
import io.reactivex.Single
import me.moallemi.carfinder.domain.model.Manufacturer
import me.moallemi.carfinder.domain.model.ManufacturerPagedResult
import me.moallemi.carfinder.domain.model.StringPagedResult

interface CarTypeRepository {

    // Manufacturers
    fun getManufacturers(page: Int, pageSize: Int): Single<ManufacturerPagedResult>

    fun getManufacturersStream(): Observable<List<Manufacturer>>

    fun storeManufacturers(items: List<Manufacturer>)

    // MainTypes
    fun getMainTypes(manufacturer: String, page: Int, pageSize: Int): Single<StringPagedResult>

    fun getMainTypesStream(manufacturerCode: String): Observable<List<String>>

    fun storeMainTypes(manufacturerCode: String, items: List<String>)

    // BuiltDates
    fun getBuiltDates(manufacturer: String, mainType: String): Single<List<String>>
}