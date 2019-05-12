package me.moallemi.carfinder.ui.cartype.browse.manufacturer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.moallemi.carfinder.R
import me.moallemi.carfinder.domain.model.Manufacturer

class ManufacturerListAdapter : RecyclerView.Adapter<ManufacturerListAdapter.ManufacturerViewHolder>() {

    var items = mutableListOf<Manufacturer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManufacturerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_manufacturer_item, parent, false)
        return ManufacturerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ManufacturerViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ManufacturerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.findViewById<TextView>(R.id.title)

        fun bind(manufacturer: Manufacturer) {
            title.text = manufacturer.name
        }
    }
}