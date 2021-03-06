package me.moallemi.carfinder.data.datasource

import io.reactivex.Single
import me.moallemi.carfinder.domain.model.ManufacturerPagedResult
import me.moallemi.carfinder.domain.model.StringPagedResult

interface CarTypeRemoteDataSource {

    fun getManufacturers(page: Int, pageSize: Int): Single<ManufacturerPagedResult>

    fun getMainTypes(manufacturer: String, page: Int, pageSize: Int): Single<StringPagedResult>

    fun getBuiltDates(manufacturer: String, mainType: String): Single<List<String>>
}