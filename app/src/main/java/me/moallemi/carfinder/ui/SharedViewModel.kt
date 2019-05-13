package me.moallemi.carfinder.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.moallemi.carfinder.model.ManufacturerItem
import me.moallemi.carfinder.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Shares data between HomeFragment and carTypeFragments (Manufacturer, MainType and BuildDates Fragments)
 */
class SharedViewModel @Inject constructor() : BaseViewModel() {

    private val _manufacturerItem = MutableLiveData<ManufacturerItem>()
    val manufacturerItem: LiveData<ManufacturerItem> = _manufacturerItem

    fun selectManufacturerItem(manufacturerItem: ManufacturerItem) {
        _manufacturerItem.value = manufacturerItem
    }
}