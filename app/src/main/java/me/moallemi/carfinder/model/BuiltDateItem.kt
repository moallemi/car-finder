package me.moallemi.carfinder.model

data class BuiltDateItem(
    val year: String
) : RecyclerData {
    override val viewType: Int = 0 // We set this value later in adapter
}