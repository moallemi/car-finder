package me.moallemi.carfinder.model

import me.moallemi.carfinder.R

data class ManufacturerItem(
    val code: String,
    val name: String
) : RecyclerData {
    override val viewType: Int = R.layout.item_manufacturer_item
}