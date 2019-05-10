package me.moallemi.carfinder.remote.datasource

import me.moallemi.carfinder.data.entity.ManufacturerEntity
import me.moallemi.carfinder.data.entity.ManufacturerPagedResult
import me.moallemi.carfinder.data.entity.StringPagedResult
import me.moallemi.carfinder.remote.dto.PagedResponseDto

fun PagedResponseDto.toManufacturerPagedResult() = ManufacturerPagedResult(
    totalPageCount = totalPageCount,
    items = items.map { ManufacturerEntity(it.key, it.value) }
)

fun PagedResponseDto.toStringPagedResult() = StringPagedResult(
    totalPageCount = totalPageCount,
    items = items.keys.toList()
)