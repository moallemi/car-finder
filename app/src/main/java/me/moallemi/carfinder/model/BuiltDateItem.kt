package me.moallemi.carfinder.model

import me.moallemi.carfinder.R

data class BuiltDateItem(
    val year: String
) : RecyclerData {
    override val viewType: Int
        get() = R.layout.item_built_date_item
}