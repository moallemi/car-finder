package me.moallemi.carfinder.ui.cartype.browse.builtdate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import me.moallemi.carfinder.R
import me.moallemi.carfinder.model.BuiltDateItem
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerAdapter
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerViewHolder

class BuiltDateListAdapter : BaseRecyclerAdapter<BuiltDateItem>() {

    override fun makeViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<BuiltDateItem>? {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return BuiltDateViewHolder(view)
    }

    class BuiltDateViewHolder(itemView: View) : BaseRecyclerViewHolder<BuiltDateItem>(itemView) {

        private val year = itemView.findViewById<AppCompatTextView>(R.id.year)

        override fun bind(item: BuiltDateItem) {
            year.text = item.year
            itemView.setOnClickListener { onItemClickListener?.onItemClick(item) }
        }
    }
}