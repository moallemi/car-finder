package me.moallemi.carfinder.ui.cartype.search.base

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.subjects.PublishSubject
import me.moallemi.carfinder.model.RecyclerData
import me.moallemi.carfinder.model.Resource
import me.moallemi.carfinder.model.ResourceState
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerViewModel
import java.util.concurrent.TimeUnit

abstract class BaseSearchableRecyclerViewModel<T : RecyclerData, Params> :
    BaseRecyclerViewModel<T, Params>() {

    private var _filteredItems = MutableLiveData<Resource<List<T>>>()
    var filteredItems: LiveData<Resource<List<T>>> = _filteredItems

    var allFilteredItems: Resource<List<T>>? = null

    private val titlePublishSubject = PublishSubject.create<String>().apply {
        debounce(300, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribe { searchTerm ->
                allItems?.data?.let { items ->
                    search(searchTerm, items)
                }
            }
    }

    abstract fun search(query: String, items: List<T>)

    fun onSearchQueryChanged(text: String) {
        titlePublishSubject.onNext(text.trim())
    }

    protected fun handleSearchSuccess(items: List<T>) {
        val resource = Resource(ResourceState.SUCCESS, items)
        _filteredItems.postValue(resource)
        allFilteredItems = resource
    }

    override fun onSaveState(bundle: Bundle) {
        super.onSaveState(bundle)

        bundle.putSerializable(KEY_SAVED_FILTERED_DATA, allFilteredItems)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onRestoreState(bundle: Bundle) {
        super.onRestoreState(bundle)

        allFilteredItems = bundle.getSerializable(KEY_SAVED_FILTERED_DATA) as Resource<List<T>>?
        _filteredItems.value = allFilteredItems
    }

    companion object {
        private const val KEY_SAVED_FILTERED_DATA = "savedFilteredData"
    }
}