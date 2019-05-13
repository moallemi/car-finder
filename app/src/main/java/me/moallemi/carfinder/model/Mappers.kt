package me.moallemi.carfinder.model

import me.moallemi.carfinder.domain.model.Manufacturer

fun Manufacturer.toManufactureItem(isEven: Boolean) = ManufacturerItem(
    code = code,
    name = name,
    isEven = isEven
)