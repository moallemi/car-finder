package me.moallemi.carfinder.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import me.moallemi.carfinder.local.db.AppDatabase
import me.moallemi.carfinder.local.entity.LocalBuiltDate
import me.moallemi.carfinder.local.entity.LocalMainType
import me.moallemi.carfinder.local.entity.LocalManufacturer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BuiltDateDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: BuiltDateDao
    private lateinit var localBuiltDateA: LocalBuiltDate
    private lateinit var localBuiltDateB: LocalBuiltDate
    private val manufacturerCode = "A"
    private val mainType = "AA"

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = database.builtDateDao()

        // To avoid foreign key constraint
        database.manufacturerDao().insert(LocalManufacturer(manufacturerCode, "NAME"))
        database.mainTypeDao().insert(LocalMainType(mainType, manufacturerCode))

        localBuiltDateA = LocalBuiltDate("1991", manufacturerCode, mainType)
        localBuiltDateB = LocalBuiltDate("2018", manufacturerCode, mainType)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun all() {
        dao.all(manufacturerCode, mainType)
            .test()
            .assertNoErrors()
            .assertValue {
                it == emptyList<LocalManufacturer>()
            }
    }

    @Test
    fun insert() {
        dao.insert(localBuiltDateA, localBuiltDateB)

        dao.all(manufacturerCode, mainType)
            .test()
            .assertValue {
                it.size == 2
            }
    }

    @Test
    fun deleteAll() {
        dao.insert(localBuiltDateA, localBuiltDateB)
        dao.deleteAll(manufacturerCode, mainType)

        dao.all(manufacturerCode, mainType)
            .test()
            .assertNoErrors()
            .assertValue {
                it == emptyList<LocalManufacturer>()
            }
    }
}