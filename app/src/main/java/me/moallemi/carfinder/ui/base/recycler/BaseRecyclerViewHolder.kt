package me.moallemi.carfinder.ui.base.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import me.moallemi.carfinder.model.RecyclerData
import me.moallemi.carfinder.ui.base.listener.OnRecyclerItemClickListener

abstract class BaseRecyclerViewHolder<T : RecyclerData>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var onItemClickListener: OnRecyclerItemClickListener<T>? = null

    abstract fun bind(item: T)
}