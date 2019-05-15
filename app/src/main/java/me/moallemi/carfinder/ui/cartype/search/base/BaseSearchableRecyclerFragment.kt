package me.moallemi.carfinder.ui.cartype.search.base

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.fragment_list_with_search.*
import me.moallemi.carfinder.R
import me.moallemi.carfinder.extension.observe
import me.moallemi.carfinder.model.RecyclerData
import me.moallemi.carfinder.model.Resource
import me.moallemi.carfinder.model.ResourceState
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerFragment

abstract class BaseSearchableRecyclerFragment<
    T : RecyclerData,
    Params,
    VM : BaseSearchableRecyclerViewModel<T, Params>>
    : BaseRecyclerFragment<T, Params, VM>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutId = R.layout.fragment_list_with_search
        isEndless = false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onSearchQueryChanged(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.apply {
            observe(filteredItems) { filteredPosts ->
                handleSearchResourceState(filteredPosts)
            }
        }
    }

    private fun handleSearchResourceState(resource: Resource<List<T>>?) {
        resource?.let {
            when (it.resourceState) {
                ResourceState.SUCCESS -> handleSearchData(it.data)
                ResourceState.ERROR -> it.failure?.let { failure -> handleSearchError(failure) }
                ResourceState.LOADING -> handleLoading()
                else -> {}
            }
        }
    }

    private fun handleSearchData(data: List<T>?) {
        hideLoading()
        hideErrorView()
        if (data == null || data.isEmpty()) {
            hideRecyclerView()
            showEmptyView()
        } else {
            showRecyclerView()
            hideEmptyView()
            showData(data)
        }
    }

    private fun handleSearchError(failure: Throwable) {
        hideLoading()
        hideRecyclerView()
        hideEmptyView()
        showErrorView(failure)
    }

    private fun handleLoading() {
        hideErrorView()
        hideEmptyView()
        hideRecyclerView()
        showLoading()
    }
}