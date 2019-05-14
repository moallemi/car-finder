package me.moallemi.carfinder.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import me.moallemi.carfinder.local.db.AppDatabase
import me.moallemi.carfinder.local.entity.LocalManufacturer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ManufacturerDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: ManufacturerDao
    private lateinit var localManufacturerA: LocalManufacturer
    private lateinit var localManufacturerB: LocalManufacturer

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = database.manufacturerDao()

        localManufacturerA = LocalManufacturer("130", "Kia")
        localManufacturerB = LocalManufacturer("107", "BMW")
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun insert() {
        dao.insert(localManufacturerA, localManufacturerB)

        dao.all()
            .test()
            .assertValue {
                it.size == 2
            }
    }

    @Test
    fun all() {
        dao.all()
            .test()
            .assertNoErrors()
            .assertValue {
                it == emptyList<LocalManufacturer>()
            }
    }

    @Test
    fun deleteAll() {
        dao.insert(localManufacturerA, localManufacturerB)
        dao.deleteAll()

        dao.all()
            .test()
            .assertNoErrors()
            .assertValue {
                it == emptyList<LocalManufacturer>()
            }
    }
}