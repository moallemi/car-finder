package me.moallemi.carfinder.model

import java.io.Serializable

data class Resource<out T>(
    val resourceState: ResourceState,
    val data: T? = null,
    val failure: Throwable? = null
) : Serializable