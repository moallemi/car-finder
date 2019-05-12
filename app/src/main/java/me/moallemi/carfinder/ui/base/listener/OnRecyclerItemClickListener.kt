package me.moallemi.carfinder.ui.base.listener

import me.moallemi.carfinder.model.RecyclerData

interface OnRecyclerItemClickListener<T : RecyclerData> {

    fun onItemClick(item: T)
}