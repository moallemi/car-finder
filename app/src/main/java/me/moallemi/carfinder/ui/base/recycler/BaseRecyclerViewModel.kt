package me.moallemi.carfinder.ui.base.recycler

import androidx.lifecycle.MediatorLiveData
import me.moallemi.carfinder.model.RecyclerData
import me.moallemi.carfinder.model.Resource
import me.moallemi.carfinder.model.ResourceState.ERROR
import me.moallemi.carfinder.model.ResourceState.ERROR_LOAD_MORE
import me.moallemi.carfinder.model.ResourceState.LOADING
import me.moallemi.carfinder.model.ResourceState.LOADING_LOAD_MORE
import me.moallemi.carfinder.model.ResourceState.SUCCESS
import me.moallemi.carfinder.model.ResourceState.SUCCESS_LOAD_MORE
import me.moallemi.carfinder.ui.base.BaseViewModel

abstract class BaseRecyclerViewModel<T : RecyclerData, Params> : BaseViewModel() {

    private val _items: MediatorLiveData<Resource<List<T>>> = MediatorLiveData()
    val items: MediatorLiveData<Resource<List<T>>> = _items

    var allItems: Resource<List<T>>? = null

    protected open var pageSize = 15
    protected open val page
        get() = allItems?.data?.size?.div(pageSize) ?: 0

    private var endOfList = false

    protected abstract fun makeData(params: Params)

    fun load(params: Params) {
        if (allItems?.data?.isNotEmpty() == true) {
            _items.value = allItems
        } else {
            _items.value = Resource(LOADING)
            makeData(params)
        }
    }

    fun loadMore(params: Params) {
        if (!endOfList && _items.value?.resourceState != LOADING_LOAD_MORE) {
            _items.value = Resource(LOADING_LOAD_MORE, _items.value?.data)
            makeData(params)
        }
    }

    protected fun error(throwable: Throwable) {
        _items.value =
            Resource(
                if (_items.value?.resourceState == LOADING) ERROR else ERROR_LOAD_MORE,
                _items.value?.data,
                throwable
            )
    }

    protected fun handleSuccess(items: List<T>) {
        endOfList = items.size < pageSize

        if (_items.value?.resourceState == LOADING_LOAD_MORE) {
            this.allItems = Resource(SUCCESS, this.allItems?.data!!.plus(items))
            _items.value = Resource(SUCCESS_LOAD_MORE, items)
        } else {
            val resource = Resource(SUCCESS, items)
            _items.value = resource
            this.allItems = resource
        }
    }
}
