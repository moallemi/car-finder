package me.moallemi.carfinder.model

import me.moallemi.carfinder.domain.model.Manufacturer

fun Manufacturer.toManufactureItem() = ManufacturerItem(
    code = code,
    name = name
)