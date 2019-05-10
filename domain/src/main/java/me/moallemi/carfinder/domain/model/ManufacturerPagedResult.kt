package me.moallemi.carfinder.domain.model

data class ManufacturerPagedResult(
    override val totalPageCount: Int,
    override val items: List<Manufacturer>
) : PagedResult<Manufacturer>