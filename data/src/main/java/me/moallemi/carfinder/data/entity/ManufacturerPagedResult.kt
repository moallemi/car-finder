package me.moallemi.carfinder.data.entity

data class ManufacturerPagedResult(
    override val totalPageCount: Int,
    override val items: List<ManufacturerEntity>
) : PagedResult<ManufacturerEntity>