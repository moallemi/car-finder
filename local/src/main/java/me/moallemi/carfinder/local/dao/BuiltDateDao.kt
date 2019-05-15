package me.moallemi.carfinder.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.reactivex.Observable
import me.moallemi.carfinder.local.entity.LocalBuiltDate

@Dao
interface BuiltDateDao {

    @Query(
        """SELECT * FROM built_date
            WHERE manufacturer_code = :manufacturerCode and main_type = :mainType
            ORDER BY year ASC"""
    )
    fun all(manufacturerCode: String, mainType: String): Observable<List<LocalBuiltDate>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg items: LocalBuiltDate)

    @Query("DELETE FROM built_date WHERE manufacturer_code = :manufacturerCode and main_type = :mainType")
    fun deleteAll(manufacturerCode: String, mainType: String)

    @Transaction
    fun updateAll(manufacturerCode: String, mainType: String, vararg items: LocalBuiltDate) {
        deleteAll(manufacturerCode, mainType)
        insert(*items)
    }
}