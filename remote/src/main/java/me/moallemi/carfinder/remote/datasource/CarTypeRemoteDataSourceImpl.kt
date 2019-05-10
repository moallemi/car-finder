package me.moallemi.carfinder.remote.datasource

import io.reactivex.Single
import me.moallemi.carfinder.data.datasource.CarTypeRemoteDataSource
import me.moallemi.carfinder.data.entity.ManufacturerPagedResult
import me.moallemi.carfinder.data.entity.StringPagedResult
import me.moallemi.carfinder.remote.api.cartype.CarTypeService
import javax.inject.Inject

class CarTypeRemoteDataSourceImpl @Inject constructor(private val carTypeService: CarTypeService) :
    CarTypeRemoteDataSource {

    override fun getManufacturers(page: Int, pageSize: Int): Single<ManufacturerPagedResult> {
        return carTypeService.getManufacturer(page, pageSize)
            .map { pagedResponseDto ->
                pagedResponseDto.toManufacturerPagedResult()
            }
    }

    override fun getMainTypes(manufacturer: String, page: Int, pageSize: Int): Single<StringPagedResult> {
        return carTypeService.getMainTypes(manufacturer, page, pageSize)
            .map { pagedResponseDto ->
                pagedResponseDto.toStringPagedResult()
            }
    }

    override fun getBuiltDates(manufacturer: String, mainType: String): Single<List<String>> {
        return carTypeService.getBuiltDates(manufacturer, mainType)
            .map { responseDto ->
                responseDto.items.keys.toList()
            }
    }
}