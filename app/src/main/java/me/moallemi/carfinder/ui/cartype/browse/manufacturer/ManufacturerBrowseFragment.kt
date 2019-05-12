package me.moallemi.carfinder.ui.cartype.browse.manufacturer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import me.moallemi.carfinder.R
import me.moallemi.carfinder.domain.model.Manufacturer
import me.moallemi.carfinder.extension.createViewModel
import me.moallemi.carfinder.extension.observe
import me.moallemi.carfinder.model.Resource
import me.moallemi.carfinder.model.ResourceState
import me.moallemi.carfinder.ui.base.BaseFragment
import java.io.IOException
import java.net.UnknownHostException

class ManufacturerBrowseFragment : BaseFragment() {

    private lateinit var viewModel: ManufacturerViewModel
    private val adapter = ManufacturerListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = createViewModel(viewModelFactory) {
            observe(items, ::handleStates)
        }
        viewModel.load()
    }

    private fun handleStates(resource: Resource<List<Manufacturer>>?) {
        resource?.let {
            when (resource.resourceState) {
                ResourceState.LOADING -> handleLoading()
                ResourceState.SUCCESS -> handleSuccess(resource.data!!)
                ResourceState.ERROR -> handleError(resource.failure!!)
            }
        }
    }

    private fun handleLoading() {
        showLoading()
        hideErrorView()
        hideEmptyView()
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    private fun hideErrorView() {
        errorView.visibility = View.GONE
    }

    private fun hideEmptyView() {
        emptyView.visibility = View.GONE
    }

    private fun handleSuccess(data: List<Manufacturer>) {
        hideLoading()
        hideErrorView()
        hideEmptyView()
        showData(data)
    }

    private fun hideLoading() {
        loading.visibility = View.GONE
    }

    private fun showData(data: List<Manufacturer>) {
        if (data.isEmpty()) {
            hideErrorView()
            showEmptyView()
        } else {
            adapter.items.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    private fun handleError(failure: Throwable) {
        hideLoading()
        hideEmptyView()
        showErrorView(failure)
        // TODO showSnackBar(failure.message)
    }

    private fun showEmptyView() {
        emptyView.visibility = View.VISIBLE
    }

    private fun showErrorView(failure: Throwable) {
        if (failure is UnknownHostException || failure is IOException) {
            errorView.setImageResource(R.drawable.ic_cloud_off_black_24dp)
        } else {
            errorView.setImageResource(R.drawable.ic_error_black_24dp)
        }
        errorView.visibility = View.VISIBLE
    }

    companion object {
        fun newInstance() = ManufacturerBrowseFragment()
    }
}