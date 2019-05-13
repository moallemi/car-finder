package me.moallemi.carfinder.ui.cartype.browse.maintype

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
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
        private val root = itemView.findViewById<View>(R.id.root)

        override fun bind(item: MainTypeItem) {
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