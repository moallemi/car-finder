package me.moallemi.carfinder.data.datasource

import io.reactivex.Observable
import me.moallemi.carfinder.domain.model.Manufacturer

interface CarTypeLocalDataSource {

    fun getManufacturers(): Observable<List<Manufacturer>>

    fun updateManufacturers(items: List<Manufacturer>)

    fun getMainTypes(manufacturerCode: String): Observable<List<String>>

    fun updateMainTypes(manufacturerCode: String, items: List<String>)

    fun getBuiltDates(manufacturerCode: String, mainType: String): Observable<List<String>>

    fun updateBuiltDates(manufacturerCode: String, mainType: String, items: List<String>)
}