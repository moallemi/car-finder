package me.moallemi.carfinder.remote.api.cartype

import io.reactivex.Single
import me.moallemi.carfinder.remote.dto.PagedResponseDto
import me.moallemi.carfinder.remote.dto.ResponseDto
import retrofit2.mock.BehaviorDelegate

class CarTypeServiceMockSuccess(private val delegate: BehaviorDelegate<CarTypeService>) : CarTypeService {

    override fun getManufacturer(page: Int, pageSize: Int): Single<PagedResponseDto> {
        val response = ResponseFactory.createManufacturerDto(page, pageSize)
        return delegate.returningResponse(response).getManufacturer(page, pageSize)
    }

    override fun getMainTypes(manufacturer: String, page: Int, pageSize: Int): Single<PagedResponseDto> {
        val response = ResponseFactory.createMainTypeDto(page, pageSize)
        return delegate.returningResponse(response).getMainTypes(manufacturer, page, pageSize)
    }

    override fun getBuiltDates(manufacturer: String, mainType: String): Single<ResponseDto> {
        val response = ResponseFactory.createBuiltDatesDto()
        return delegate.returningResponse(response).getBuiltDates(manufacturer, mainType)
    }
}