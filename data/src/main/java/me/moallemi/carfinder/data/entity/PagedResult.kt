package me.moallemi.carfinder.data.entity

interface PagedResult<T> {
    val totalPageCount: Int
    val items: List<T>
}