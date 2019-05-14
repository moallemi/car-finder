package me.moallemi.carfinder.local.entity

import me.moallemi.carfinder.domain.model.Manufacturer

fun LocalManufacturer.toManufacturer() = Manufacturer(
    code = code,
    name = name
)

fun Manufacturer.toLocalManufacturer() = LocalManufacturer(
    code = code,
    name = name
)