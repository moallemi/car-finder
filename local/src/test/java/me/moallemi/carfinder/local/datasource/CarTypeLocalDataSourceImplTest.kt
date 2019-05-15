package me.moallemi.carfinder.local.datasource

import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import me.moallemi.carfinder.domain.model.Manufacturer
import me.moallemi.carfinder.local.dao.BuiltDateDao
import me.moallemi.carfinder.local.dao.MainTypeDao
import me.moallemi.carfinder.local.dao.ManufacturerDao
import me.moallemi.carfinder.local.entity.LocalManufacturer
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.sql.SQLException

class CarTypeLocalDataSourceImplTest {
    private val manufacturerDao: ManufacturerDao = mockk(relaxed = true)
    private val mainTypeDao: MainTypeDao = mockk()
    private val builtDateDao: BuiltDateDao = mockk()

    private val manufacturerA = Manufacturer("A", "AA")
    private val manufacturerB = Manufacturer("B", "BB")

    private val localManufacturerA = LocalManufacturer("A", "AA")
    private val localManufacturerB = LocalManufacturer("B", "BB")

    private lateinit var dataSource: CarTypeLocalDataSourceImpl

    @Before
    fun setUp() {
        dataSource = CarTypeLocalDataSourceImpl(manufacturerDao, mainTypeDao, builtDateDao)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `getManufacturers returns results`() {
        every { manufacturerDao.all() } returns Observable.just(listOf(localManufacturerA, localManufacturerB))

        dataSource.getManufacturers()
            .test()
            .assertResult(listOf(manufacturerA, manufacturerB))
    }

    @Test
    fun `getManufacturers returns empty`() {
        every { manufacturerDao.all() } returns Observable.just(listOf())

        dataSource.getManufacturers()
            .test()
            .assertResult(listOf())
    }

    @Test
    fun `getManufacturers when fails`() {
        every { manufacturerDao.all() } returns Observable.error(SQLException())

        dataSource.getManufacturers()
            .test()
            .assertFailure(SQLException::class.java)
    }

    @Test
    fun `updateManufacturers when successful`() {
        every { manufacturerDao.updateAll(any()) } just Runs

        dataSource.updateManufacturers(listOf(manufacturerA, manufacturerB))

        verify(exactly = 1) {
            manufacturerDao.updateAll(localManufacturerA, localManufacturerB)
        }
    }
}