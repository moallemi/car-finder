package me.moallemi.carfinder.model

import java.io.Serializable

enum class ResourceState : Serializable {
    SUCCESS,
    LOADING,
    ERROR,
    SUCCESS_LOAD_MORE,
    LOADING_LOAD_MORE,
    ERROR_LOAD_MORE
}