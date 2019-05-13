package me.moallemi.carfinder.ui.cartype.browse.manufacturer

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
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
        private val root = itemView.findViewById<View>(R.id.root)

        override fun bind(item: ManufacturerItem) {
            title.text = item.name
            if (item.isEven) {
                root.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.evenRowBackgroundColor))
            } else {
                root.setBackgroundColor(Color.TRANSPARENT)
            }
            itemView.setOnClickListener { onItemClickListener?.onItemClick(item) }
        }
    }
}