package me.moallemi.carfinder.model

import java.io.Serializable

enum class ResourceState : Serializable {
    SUCCESS,
    LOADING,
    ERROR
}