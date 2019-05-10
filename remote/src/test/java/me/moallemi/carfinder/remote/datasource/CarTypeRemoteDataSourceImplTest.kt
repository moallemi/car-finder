package me.moallemi.carfinder.remote.datasource

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import me.moallemi.carfinder.remote.api.cartype.CarTypeService
import me.moallemi.carfinder.remote.api.cartype.ResponseFactory
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CarTypeRemoteDataSourceImplTest {

    private val service: CarTypeService = mockk()
    private val dataSource = CarTypeRemoteDataSourceImpl(service)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `getManufacturer when successful`() {
        val page = 0
        val pageSize = 10
        val pagedResponseDto = ResponseFactory.createManufacturerDto(page, pageSize)
        every { service.getManufacturer(page, pageSize) } returns Single.just(pagedResponseDto)

        dataSource.getManufacturers(page, pageSize)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(pagedResponseDto.toManufacturerPagedResult())
    }

    @Test
    fun `getManufacturer when fails`() {
        every { service.getManufacturer(any(), any()) } returns Single.error(IOException())
        dataSource.getManufacturers(0, 10)
            .test()
            .assertNotComplete()
            .assertError(IOException::class.java)
    }

    @Test
    fun `getMainTypes when successful`() {
        val manufacturer = "107"
        val page = 0
        val pageSize = 10
        val pagedResponseDto = ResponseFactory.createMainTypeDto(page, pageSize)
        every { service.getMainTypes(manufacturer, page, pageSize) } returns Single.just(pagedResponseDto)

        dataSource.getMainTypes(manufacturer, page, pageSize)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(pagedResponseDto.toStringPagedResult())
    }

    @Test
    fun `getMainTypes when fails`() {
        every { service.getMainTypes(any(), any(), any()) } returns Single.error(IOException())
        dataSource.getMainTypes("107", 0, 10)
            .test()
            .assertNotComplete()
            .assertError(IOException::class.java)
    }

    @Test
    fun `getBuiltDates when successful`() {
        val manufacturer = "107"
        val mainType = "Azure"
        val responseDto = ResponseFactory.createBuiltDatesDto()
        every { service.getBuiltDates(manufacturer, mainType) } returns Single.just(responseDto)

        dataSource.getBuiltDates(manufacturer, mainType)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(responseDto.toList())
    }

    @Test
    fun `getBuiltDates when fails`() {
        every { service.getBuiltDates(any(), any()) } returns Single.error(IOException())
        dataSource.getBuiltDates("107", "Azure")
            .test()
            .assertNotComplete()
            .assertError(IOException::class.java)
    }
}