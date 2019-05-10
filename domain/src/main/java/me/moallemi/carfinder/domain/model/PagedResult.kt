package me.moallemi.carfinder.domain.model

interface PagedResult<T> {
    val totalPageCount: Int
    val items: List<T>
}