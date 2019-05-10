package me.moallemi.carfinder.remote.api.cartype

import io.reactivex.Single
import me.moallemi.carfinder.remote.dto.PagedResponseDto
import me.moallemi.carfinder.remote.dto.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CarTypeService {

    @GET("v1/car-types/manufacturer")
    fun getManufacturer(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Single<PagedResponseDto>

    @GET("v1/car-types/main-types")
    fun getMainTypes(
        @Query("manufacturer") manufacturer: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Single<PagedResponseDto>

    @GET("v1/car-types/built-dates")
    fun getBuiltDates(
        @Query("manufacturer") manufacturer: String,
        @Query("main-type") mainType: String
    ): Single<ResponseDto>
}