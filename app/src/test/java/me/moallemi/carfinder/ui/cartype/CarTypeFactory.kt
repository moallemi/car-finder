package me.moallemi.carfinder.ui.cartype

import me.moallemi.carfinder.domain.model.Manufacturer
import me.moallemi.carfinder.domain.model.ManufacturerPagedResult
import me.moallemi.carfinder.domain.model.StringPagedResult

object CarTypeFactory {

    fun createManufacturerPagedResult() = ManufacturerPagedResult(
        totalPageCount = 1,
        items = listOf(
            MANUFACTURER_A,
            MANUFACTURER_B
        )
    )

    fun createMainTypes() = StringPagedResult(
        totalPageCount = 1,
        items = listOf("A", "B", "C")
    )

    fun createManufacturerList() = listOf(MANUFACTURER_A, MANUFACTURER_B)

    val MANUFACTURER_A = Manufacturer("A", "AA")
    val MANUFACTURER_B = Manufacturer("B", "BB")

    const val MANUFACTURER_CODE = "CODE"
    const val MAIN_TYPE = "MAIN_TYPE"
}