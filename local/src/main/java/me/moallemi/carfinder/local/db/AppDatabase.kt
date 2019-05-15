package me.moallemi.carfinder.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.moallemi.carfinder.local.dao.BuiltDateDao
import me.moallemi.carfinder.local.dao.MainTypeDao
import me.moallemi.carfinder.local.dao.ManufacturerDao
import me.moallemi.carfinder.local.entity.LocalBuiltDate
import me.moallemi.carfinder.local.entity.LocalMainType
import me.moallemi.carfinder.local.entity.LocalManufacturer

@Database(
    entities = [LocalManufacturer::class, LocalMainType::class, LocalBuiltDate::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun manufacturerDao(): ManufacturerDao

    abstract fun mainTypeDao(): MainTypeDao

    abstract fun builtDateDao(): BuiltDateDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "data.db")
                    .build()
                    .also {
                        instance = it
                    }
            }
        }
    }
}