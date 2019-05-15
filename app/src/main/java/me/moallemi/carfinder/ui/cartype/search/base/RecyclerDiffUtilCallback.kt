package me.moallemi.carfinder.ui.cartype.search.base

import androidx.recyclerview.widget.DiffUtil
import me.moallemi.carfinder.model.ManufacturerItem
import me.moallemi.carfinder.model.RecyclerData

class RecyclerDiffUtilCallback<T : RecyclerData>(private val oldList: List<T>, private val newList: List<T>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].viewType == newList[newItemPosition].viewType

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return if (oldList[oldItemPosition] is ManufacturerItem) {
            (oldList[oldItemPosition] as ManufacturerItem).code ==
                (newList[newItemPosition] as ManufacturerItem).code &&
                (oldList[oldItemPosition] as ManufacturerItem).name ==
                (newList[newItemPosition] as ManufacturerItem).name
        } else {
            false
        }
    }
}