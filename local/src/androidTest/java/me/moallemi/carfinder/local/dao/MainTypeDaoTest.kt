package me.moallemi.carfinder.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import me.moallemi.carfinder.local.db.AppDatabase
import me.moallemi.carfinder.local.entity.LocalMainType
import me.moallemi.carfinder.local.entity.LocalManufacturer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainTypeDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: MainTypeDao
    private lateinit var localMainTypeA: LocalMainType
    private lateinit var localMainTypeB: LocalMainType
    private val manufacturerCode = "A"

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = database.mainTypeDao()

        // To avoid foreign key constraint
        database.manufacturerDao().insert(LocalManufacturer(manufacturerCode, "NAME"))

        localMainTypeA = LocalMainType("NameA", manufacturerCode)
        localMainTypeB = LocalMainType("NameB", manufacturerCode)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun all() {
        dao.all(manufacturerCode)
            .test()
            .assertNoErrors()
            .assertValue {
                it == emptyList<LocalManufacturer>()
            }
    }

    @Test
    fun insert() {
        dao.insert(localMainTypeA, localMainTypeB)

        dao.all(manufacturerCode)
            .test()
            .assertValue {
                it.size == 2
            }
    }

    @Test
    fun deleteAll() {
        dao.insert(localMainTypeA, localMainTypeB)
        dao.deleteAll(manufacturerCode)

        dao.all(manufacturerCode)
            .test()
            .assertNoErrors()
            .assertValue {
                it == emptyList<LocalManufacturer>()
            }
    }
}