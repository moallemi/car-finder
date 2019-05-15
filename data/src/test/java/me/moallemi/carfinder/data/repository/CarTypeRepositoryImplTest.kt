package me.moallemi.carfinder.data.repository

import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.Single
import me.moallemi.carfinder.data.datasource.CarTypeLocalDataSource
import me.moallemi.carfinder.data.datasource.CarTypeRemoteDataSource
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CarTypeRepositoryImplTest {

    private val remoteDataSource: CarTypeRemoteDataSource = mockk()
    private val localDataSource: CarTypeLocalDataSource = mockk()
    private val repository = CarTypeRepositoryImpl(remoteDataSource, localDataSource)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `getManufacturers when successful`() {
        val pagedResult = CarTypeFactory.createManufacturerPagedResult()
        every {
            remoteDataSource.getManufacturers(
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
            remoteDataSource.getManufacturers(
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
    fun `getManufacturersStream when successful`() {
        every { localDataSource.getManufacturers() } returns Observable.just(listOf(CarTypeFactory.MANUFACTURER_A))

        repository.getManufacturersStream()
            .test()
            .assertResult(listOf(CarTypeFactory.MANUFACTURER_A))
    }

    @Test
    fun `getManufacturersStream when fails`() {
        every { localDataSource.getManufacturers() } returns Observable.error(Exception())

        repository.getManufacturersStream()
            .test()
            .assertFailure(Exception::class.java)
    }

    @Test
    fun `storeManufacturers when successful`() {
        every { localDataSource.updateManufacturers(any()) } just Runs

        repository.storeManufacturers(listOf(CarTypeFactory.MANUFACTURER_A))

        verify(exactly = 1) {
            localDataSource.updateManufacturers(listOf(CarTypeFactory.MANUFACTURER_A))
        }
    }

    @Test
    fun `getMainTypes when successful`() {
        val pagedResult = CarTypeFactory.createMainTypes()
        every {
            remoteDataSource.getMainTypes(
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
            remoteDataSource.getMainTypes(
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
    fun `getMainTypesStream when successful`() {
        every { localDataSource.getMainTypes(any()) } returns Observable.just(listOf("A"))

        repository.getMainTypesStream(CarTypeFactory.MANUFACTURER_CODE)
            .test()
            .assertResult(listOf("A"))
    }

    @Test
    fun `getMainTypesStream when fails`() {
        every { localDataSource.getMainTypes(any()) } returns Observable.error(Exception())

        repository.getMainTypesStream(CarTypeFactory.MANUFACTURER_CODE)
            .test()
            .assertFailure(Exception::class.java)
    }

    @Test
    fun `storeMainTypes when successful`() {
        every { localDataSource.updateMainTypes(any(), any()) } just Runs

        repository.storeMainTypes(CarTypeFactory.MANUFACTURER_CODE, listOf("A"))

        verify(exactly = 1) {
            localDataSource.updateMainTypes(CarTypeFactory.MANUFACTURER_CODE, listOf("A"))
        }
    }

    @Test
    fun `getBuiltDates when successful`() {
        every {
            remoteDataSource.getBuiltDates(
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
            remoteDataSource.getBuiltDates(
                any(),
                any()
            )
        } returns Single.error(IOException())

        repository.getBuiltDates("Kia", "Pride")
            .test()
            .assertNotComplete()
            .assertFailure(IOException::class.java)
    }

    @Test
    fun `getBuiltDatesStream when successful`() {
        every { localDataSource.getBuiltDates(any(), any()) } returns Observable.just(listOf("A"))

        repository.getBuiltDatesStream(CarTypeFactory.MANUFACTURER_CODE, CarTypeFactory.MAIN_TYPE)
            .test()
            .assertResult(listOf("A"))
    }

    @Test
    fun `getBuiltDatesStream when fails`() {
        every { localDataSource.getBuiltDates(any(), any()) } returns Observable.error(Exception())

        repository.getBuiltDatesStream(CarTypeFactory.MANUFACTURER_CODE, CarTypeFactory.MAIN_TYPE)
            .test()
            .assertFailure(Exception::class.java)
    }

    @Test
    fun `storeBuiltDates when successful`() {
        every { localDataSource.updateBuiltDates(any(), any(), any()) } just Runs

        repository.storeBuiltDates(CarTypeFactory.MANUFACTURER_CODE, CarTypeFactory.MAIN_TYPE, listOf("A"))

        verify(exactly = 1) {
            localDataSource.updateBuiltDates(CarTypeFactory.MANUFACTURER_CODE, CarTypeFactory.MAIN_TYPE, listOf("A"))
        }
    }
}