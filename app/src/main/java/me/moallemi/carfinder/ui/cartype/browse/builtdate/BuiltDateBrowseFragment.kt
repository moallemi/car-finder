package me.moallemi.carfinder.ui.cartype.browse.builtdate

import android.os.Bundle
import me.moallemi.carfinder.domain.interactor.GetBuiltDatesUseCase
import me.moallemi.carfinder.extension.createSharedViewModel
import me.moallemi.carfinder.extension.createViewModel
import me.moallemi.carfinder.model.BuiltDateItem
import me.moallemi.carfinder.ui.cartype.base.SharedViewModel
import me.moallemi.carfinder.ui.base.listener.OnRecyclerItemClickListener
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerFragment

class BuiltDateBrowseFragment : BaseRecyclerFragment<BuiltDateItem, GetBuiltDatesUseCase.Params, BuiltDateViewModel>(),
    OnRecyclerItemClickListener<BuiltDateItem> {

    private val fragmentArgs: BuiltDateBrowseFragmentArgs by lazy {
        BuiltDateBrowseFragmentArgs.fromBundle(arguments ?: throw IllegalStateException("argument must not be null"))
    }
    private lateinit var sharedViewModel: SharedViewModel

    override val adapter = BuiltDateListAdapter()

    init {
        recyclerItemClickListener = this
        isEndless = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = createSharedViewModel()
    }

    override fun makeViewModel(): BuiltDateViewModel {
        return createViewModel(viewModelFactory)
    }

    override fun getParams(): GetBuiltDatesUseCase.Params {
        return GetBuiltDatesUseCase.Params(fragmentArgs.manufacturer, fragmentArgs.mainType)
    }

    override fun onItemClick(item: BuiltDateItem) {
        sharedViewModel.selectBuiltDateItem(item)
        activity?.onBackPressed()
    }

    companion object {
        fun newInstance(args: BuiltDateBrowseFragmentArgs): BuiltDateBrowseFragment {
            return BuiltDateBrowseFragment().apply {
                arguments = args.toBundle()
            }
        }
    }
}