package me.moallemi.carfinder.remote.dto

import com.google.gson.annotations.SerializedName

data class PagedResponseDto(
    @SerializedName("page") val page: Int?,
    @SerializedName("pageSize") val pageSize: Int?,
    @SerializedName("totalPageCount") val totalPageCount: Int?,
    @SerializedName("wkda") val items: Map<String, String>?
)