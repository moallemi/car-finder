package me.moallemi.carfinder.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Observable
import me.moallemi.carfinder.local.entity.LocalMainType

@Dao
interface MainTypeDao {

    @Query("SELECT * FROM main_type WHERE manufacturer_code = :manufacturerCode ORDER BY name ASC")
    fun all(manufacturerCode: String): Observable<List<LocalMainType>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg items: LocalMainType)

    @Query("DELETE FROM main_type WHERE manufacturer_code = :manufacturerCode")
    fun deleteAll(manufacturerCode: String)

    @Transaction
    fun updateAll(manufacturerCode: String, vararg items: LocalMainType) {
        deleteAll(manufacturerCode)
        insert(*items)
    }
}