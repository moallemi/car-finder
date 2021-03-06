package me.moallemi.carfinder.ui.base.recycler.loadmore

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import me.moallemi.carfinder.R
import me.moallemi.carfinder.ui.base.listener.TryAgainClickListener
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerViewHolder

class MoreViewHolder(itemView: View, private val tryAgainListener: TryAgainClickListener?) :
    BaseRecyclerViewHolder<MoreItem>(itemView) {

    private val errorText = itemView.findViewById<TextView>(R.id.errorText)
    private val loading = itemView.findViewById<ProgressBar>(R.id.loading)
    private val refresh = itemView.findViewById<ImageView>(R.id.refresh)

    override fun bind(item: MoreItem) {
        when (item.state) {
            State.Error -> {
                item.message?.let { message -> errorText.text = message }
                errorText.visibility = View.VISIBLE
                loading.visibility = View.GONE

                refresh.visibility = View.VISIBLE
                refresh.setOnClickListener { tryAgainListener?.tryAgain() }
            }
            State.Loading -> {
                errorText.visibility = View.GONE
                refresh.visibility = View.GONE
                loading.visibility = View.VISIBLE
            }
        }
    }
}