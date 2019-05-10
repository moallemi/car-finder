package me.moallemi.carfinder.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseDto(
    @SerializedName("wkda") val items: Map<String, String>?
)