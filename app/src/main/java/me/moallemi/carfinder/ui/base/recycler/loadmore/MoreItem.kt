package me.moallemi.carfinder.ui.base.recycler.loadmore

import me.moallemi.carfinder.R
import me.moallemi.carfinder.model.RecyclerData

data class MoreItem(val state: State, val message: String? = null) : RecyclerData {

    override val viewType: Int
        get() = VIEW_TYPE

    companion object {
        const val VIEW_TYPE: Int = R.layout.item_load_more
    }
}

enum class State {
    Error,
    Loading
}