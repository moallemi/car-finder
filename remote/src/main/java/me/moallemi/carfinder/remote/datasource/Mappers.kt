package me.moallemi.carfinder.remote.datasource

import me.moallemi.carfinder.domain.model.Manufacturer
import me.moallemi.carfinder.domain.model.ManufacturerPagedResult
import me.moallemi.carfinder.domain.model.StringPagedResult
import me.moallemi.carfinder.remote.dto.PagedResponseDto
import me.moallemi.carfinder.remote.dto.ResponseDto

fun PagedResponseDto.toManufacturerPagedResult() = ManufacturerPagedResult(
    totalPageCount = totalPageCount,
    items = items.map { Manufacturer(it.key, it.value) }
)

fun PagedResponseDto.toStringPagedResult() = StringPagedResult(
    totalPageCount = totalPageCount,
    items = items.keys.toList()
)

fun ResponseDto.toList() = items.keys.toList()