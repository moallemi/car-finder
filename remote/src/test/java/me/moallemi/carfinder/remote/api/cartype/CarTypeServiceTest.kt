package me.moallemi.carfinder.remote.api.cartype

import io.mockk.clearAllMocks
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.mock.MockRetrofit
import retrofit2.mock.NetworkBehavior

class CarTypeServiceTest {

    private lateinit var mockRetrofit: MockRetrofit
    private lateinit var carTypeServiceMockSuccess: CarTypeServiceMockSuccess
    private lateinit var carTypeServiceMockFailure: CarTypeServiceMockFailure

    @Before
    fun setUp() {
        clearAllMocks()

        val retrofit = Retrofit.Builder().baseUrl("http://test.com")
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val behavior = NetworkBehavior.create()

        mockRetrofit = MockRetrofit.Builder(retrofit)
            .networkBehavior(behavior)
            .build()

        val successDelegate = mockRetrofit.create(CarTypeService::class.java)
        val failureDelegate = mockRetrofit.create(CarTypeService::class.java)
        carTypeServiceMockSuccess = CarTypeServiceMockSuccess(successDelegate)
        carTypeServiceMockFailure = CarTypeServiceMockFailure(failureDelegate)
    }

    @Test
    fun `getManufacturer when successful`() {
        val page = 0
        val pageSize = 10

        carTypeServiceMockSuccess.getManufacturer(page, pageSize)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(ResponseFactory.createManufacturerDto(page, pageSize))
    }

    @Test
    fun `getManufacturer when fails`() {
        carTypeServiceMockFailure.getManufacturer(0, 10)
            .test()
            .assertNotComplete()
            .assertFailure(HttpException::class.java)
            .assertError { error ->
                (error as HttpException).code() == 404
            }
    }

    @Test
    fun `getMainTypes when successful`() {
        val manufacturer = "107"
        val page = 0
        val pageSize = 10

        carTypeServiceMockSuccess.getMainTypes(manufacturer, page, pageSize)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(ResponseFactory.createMainTypeDto(page, pageSize))
    }

    @Test
    fun `getMainTypes when fails`() {
        carTypeServiceMockFailure.getMainTypes("107", 0, 10)
            .test()
            .assertNotComplete()
            .assertFailure(HttpException::class.java)
            .assertError { error ->
                (error as HttpException).code() == 404
            }
    }

    @Test
    fun `getBuiltDates when successful`() {
        carTypeServiceMockSuccess.getBuiltDates("107", "Azure")
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(ResponseFactory.createBuiltDatesDto())
    }

    @Test
    fun `getBuiltDates when fails`() {
        carTypeServiceMockFailure.getBuiltDates("107", "Azure")
            .test()
            .assertNotComplete()
            .assertFailure(HttpException::class.java)
            .assertError { error ->
                (error as HttpException).code() == 404
            }
    }
}