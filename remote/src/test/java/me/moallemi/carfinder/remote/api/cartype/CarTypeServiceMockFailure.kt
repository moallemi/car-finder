package me.moallemi.carfinder.remote.api.cartype

import io.reactivex.Single
import me.moallemi.carfinder.remote.dto.PagedResponseDto
import me.moallemi.carfinder.remote.dto.ResponseDto
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.mock.BehaviorDelegate
import retrofit2.mock.Calls

class CarTypeServiceMockFailure(private val delegate: BehaviorDelegate<CarTypeService>) : CarTypeService {

    override fun getManufacturer(page: Int, pageSize: Int): Single<PagedResponseDto> {
        val response = Response.error<PagedResponseDto>(
            404,
            ResponseBody.create(MediaType.parse("application/json"), "{}")
        )
        return delegate.returning(Calls.response(response)).getManufacturer(page, pageSize)
    }

    override fun getMainTypes(manufacturer: String, page: Int, pageSize: Int): Single<PagedResponseDto> {
        val response = Response.error<PagedResponseDto>(
            404,
            ResponseBody.create(MediaType.parse("application/json"), "{}")
        )
        return delegate.returning(Calls.response(response)).getMainTypes(manufacturer, page, pageSize)
    }

    override fun getBuiltDates(manufacturer: String, mainType: String): Single<ResponseDto> {
        val response = Response.error<PagedResponseDto>(
            404,
            ResponseBody.create(MediaType.parse("application/json"), "{}")
        )
        return delegate.returning(Calls.response(response)).getBuiltDates(manufacturer, mainType)
    }
}