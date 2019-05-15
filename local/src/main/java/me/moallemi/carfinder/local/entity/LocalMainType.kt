package me.moallemi.carfinder.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "main_type",
    indices = [
        Index(
            value = ["manufacturer_code", "name"],
            unique = true
        )],
    foreignKeys = [
        ForeignKey(
            entity = LocalManufacturer::class,
            parentColumns = ["code"],
            childColumns = ["manufacturer_code"]
        )]
)
data class LocalMainType(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "manufacturer_code") val manufacturerCode: String,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int? = null
)