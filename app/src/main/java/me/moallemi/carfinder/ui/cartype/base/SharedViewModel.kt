package me.moallemi.carfinder.ui.cartype.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.moallemi.carfinder.model.BuiltDateItem
import me.moallemi.carfinder.model.MainTypeItem
import me.moallemi.carfinder.model.ManufacturerItem
import me.moallemi.carfinder.ui.base.BaseViewModel
import javax.inject.Inject

/**
 * Shares data between SummaryFragment and carTypeFragments (Manufacturer, MainType and BuildDates Fragments)
 */
class SharedViewModel @Inject constructor() : BaseViewModel() {

    private val _manufacturerItem = MutableLiveData<ManufacturerItem>()
    val manufacturerItem: LiveData<ManufacturerItem> = _manufacturerItem

    private val _mainTypeItem = MutableLiveData<MainTypeItem>()
    val mainTypeItem: LiveData<MainTypeItem> = _mainTypeItem

    private val _builtDateItem = MutableLiveData<BuiltDateItem>()
    val builtDateItem: LiveData<BuiltDateItem> = _builtDateItem

    fun selectManufacturerItem(manufacturerItem: ManufacturerItem) {
        _manufacturerItem.value = manufacturerItem
    }

    fun selectMainTypeItem(mainTypeItem: MainTypeItem) {
        _mainTypeItem.value = mainTypeItem
    }

    fun selectBuiltDateItem(builtDateItem: BuiltDateItem) {
        _builtDateItem.value = builtDateItem
    }
}