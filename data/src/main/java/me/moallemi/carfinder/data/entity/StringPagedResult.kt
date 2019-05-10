package me.moallemi.carfinder.data.entity

data class StringPagedResult(
    override val totalPageCount: Int,
    override val items: List<String>
) : PagedResult<String>