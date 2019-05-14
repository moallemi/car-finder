package me.moallemi.carfinder.data.datasource

import io.reactivex.Observable
import me.moallemi.carfinder.domain.model.Manufacturer

interface CarTypeLocalDataSource {

    fun getManufacturers(): Observable<List<Manufacturer>>

    fun updateManufacturers(items: List<Manufacturer>)
}