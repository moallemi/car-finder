package me.moallemi.carfinder.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "main_type",
    primaryKeys = ["name", "manufacturer_code"],
    indices = [
        Index(
            value = ["name"],
            unique = true
        ),
        Index(
            value = ["manufacturer_code"]
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
    @ColumnInfo(name = "manufacturer_code") val manufacturerCode: String
)