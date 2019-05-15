package me.moallemi.carfinder.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "built_date",
    primaryKeys = ["year", "manufacturer_code", "main_type"],
    indices = [
        Index(
            value = ["main_type", "manufacturer_code", "year"],
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
        ), ForeignKey(
            entity = LocalMainType::class,
            parentColumns = ["name"],
            childColumns = ["main_type"]
        )]
)
data class LocalBuiltDate(
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "manufacturer_code") val manufacturerCode: String,
    @ColumnInfo(name = "main_type") val mainType: String
)