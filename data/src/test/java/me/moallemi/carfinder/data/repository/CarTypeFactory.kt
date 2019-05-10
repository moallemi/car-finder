package me.moallemi.carfinder.data.repository

import me.moallemi.carfinder.domain.model.Manufacturer
import me.moallemi.carfinder.domain.model.ManufacturerPagedResult
import me.moallemi.carfinder.domain.model.StringPagedResult

object CarTypeFactory {

    fun createManufacturerPagedResult() = ManufacturerPagedResult(
        totalPageCount = 1,
        items = listOf(
            Manufacturer("101", "BMW"),
            Manufacturer("102", "Kia")
        )
    )

    fun createMainTypes() = StringPagedResult(
        totalPageCount = 1,
        items = listOf("A", "B", "C")
    )
}