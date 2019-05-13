package me.moallemi.carfinder.ui.cartype.browse.maintype

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import me.moallemi.carfinder.R
import me.moallemi.carfinder.model.MainTypeItem
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerAdapter
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerViewHolder

class MainTypeListAdapter : BaseRecyclerAdapter<MainTypeItem>() {

    override fun makeViewHolder(parent: ViewGroup, viewType: Int): BaseRecyclerViewHolder<MainTypeItem>? {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return MainTypeViewHolder(view)
    }

    class MainTypeViewHolder(itemView: View) : BaseRecyclerViewHolder<MainTypeItem>(itemView) {

        private val title = itemView.findViewById<AppCompatTextView>(R.id.title)

        override fun bind(item: MainTypeItem) {
            title.text = item.name
            itemView.setOnClickListener { onItemClickListener?.onItemClick(item) }
        }
    }
}