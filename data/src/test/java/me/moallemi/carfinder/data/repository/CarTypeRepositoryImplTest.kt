package me.moallemi.carfinder.data.repository

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import me.moallemi.carfinder.data.datasource.CarTypeRemoteDataSource
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CarTypeRepositoryImplTest {

    private val dataSource: CarTypeRemoteDataSource = mockk()
    private val repository = CarTypeRepositoryImpl(dataSource)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `getManufacturers when successful`() {
        val pagedResult = CarTypeFactory.createManufacturerPagedResult()
        every {
            dataSource.getManufacturers(
                any(),
                any()
            )
        } returns Single.just(pagedResult)

        repository.getManufacturers(0, 10)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(pagedResult)
    }

    @Test
    fun `getManufacturers when fails`() {
        every {
            dataSource.getManufacturers(
                any(),
                any()
            )
        } returns Single.error(IOException())

        repository.getManufacturers(0, 10)
            .test()
            .assertNotComplete()
            .assertFailure(IOException::class.java)
    }

    @Test
    fun `getMainTypes when successful`() {
        val pagedResult = CarTypeFactory.createMainTypes()
        every {
            dataSource.getMainTypes(
                any(),
                any(),
                any()
            )
        } returns Single.just(pagedResult)

        repository.getMainTypes("107", 0, 10)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(pagedResult)
    }

    @Test
    fun `getMainTypes when fails`() {
        every {
            dataSource.getMainTypes(
                any(),
                any(),
                any()
            )
        } returns Single.error(IOException())

        repository.getMainTypes("107", 0, 10)
            .test()
            .assertNotComplete()
            .assertFailure(IOException::class.java)
    }

    @Test
    fun `getBuiltDates when successful`() {
        every {
            dataSource.getBuiltDates(
                any(),
                any()
            )
        } returns Single.just(listOf())

        repository.getBuiltDates("Kia", "Pride")
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(listOf())
    }

    @Test
    fun `getBuiltDates when fails`() {
        every {
            dataSource.getBuiltDates(
                any(),
                any()
            )
        } returns Single.error(IOException())

        repository.getBuiltDates("Kia", "Pride")
            .test()
            .assertNotComplete()
            .assertFailure(IOException::class.java)
    }
}