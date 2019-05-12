package me.moallemi.carfinder.model

import java.io.Serializable

interface RecyclerData : Serializable {
    val viewType: Int
}