package me.moallemi.carfinder.remote.api.cartype

import me.moallemi.carfinder.remote.dto.PagedResponseDto
import me.moallemi.carfinder.remote.dto.ResponseDto

object ResponseFactory {

    fun createManufacturerDto(page: Int, pageSize: Int) = PagedResponseDto(
        page = page,
        pageSize = pageSize,
        totalPageCount = 1,
        items = mapOf(
            "107" to "Bentley",
            "130" to "BMW"
        )
    )

    fun createMainTypeDto(page: Int, pageSize: Int) = PagedResponseDto(
        page = page,
        pageSize = pageSize,
        totalPageCount = 1,
        items = mapOf(
            "Besta" to "Besta",
            "Carens" to "Carens",
            "Carnival" to "Carnival"
        )
    )

    fun createBuiltDatesDto() = ResponseDto(
        items = mapOf(
            "1998" to "1998",
            "2001" to "2001",
            "2005" to "2005"
        )
    )
}