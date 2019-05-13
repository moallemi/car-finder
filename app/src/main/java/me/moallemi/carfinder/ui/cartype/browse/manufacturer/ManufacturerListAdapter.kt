package me.moallemi.carfinder.ui.cartype.browse.manufacturer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import me.moallemi.carfinder.R
import me.moallemi.carfinder.model.ManufacturerItem
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerAdapter
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerViewHolder

class ManufacturerListAdapter : BaseRecyclerAdapter<ManufacturerItem>() {

    override fun makeViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<ManufacturerItem>? {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return ManufacturerViewHolder(view)
    }

    class ManufacturerViewHolder(itemView: View) : BaseRecyclerViewHolder<ManufacturerItem>(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.title)

        override fun bind(item: ManufacturerItem) {
            title.text = item.name
            itemView.setOnClickListener { onItemClickListener?.onItemClick(item) }
        }
    }
}