package me.moallemi.carfinder.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Observable
import me.moallemi.carfinder.local.entity.LocalManufacturer

@Dao
interface ManufacturerDao {

    @Query("SELECT * FROM manufacturer ORDER BY name ASC")
    fun all(): Observable<List<LocalManufacturer>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg items: LocalManufacturer)

    @Query("DELETE FROM manufacturer")
    fun deleteAll()

    @Transaction
    fun updateAll(vararg items: LocalManufacturer) {
        deleteAll()
        insert(*items)
    }
}