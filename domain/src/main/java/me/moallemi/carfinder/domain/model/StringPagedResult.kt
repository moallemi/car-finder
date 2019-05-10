package me.moallemi.carfinder.domain.model

data class StringPagedResult(
    override val totalPageCount: Int,
    override val items: List<String>
) : PagedResult<String>