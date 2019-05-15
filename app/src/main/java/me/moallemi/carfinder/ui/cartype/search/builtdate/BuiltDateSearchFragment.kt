package me.moallemi.carfinder.ui.cartype.search.builtdate

import android.os.Bundle
import me.moallemi.carfinder.domain.interactor.builtdate.GetAllBuiltDatesUseCase
import me.moallemi.carfinder.extension.createSharedViewModel
import me.moallemi.carfinder.extension.createViewModel
import me.moallemi.carfinder.model.BuiltDateItem
import me.moallemi.carfinder.ui.cartype.base.SharedViewModel
import me.moallemi.carfinder.ui.base.listener.OnRecyclerItemClickListener
import me.moallemi.carfinder.ui.cartype.browse.builtdate.BuiltDateBrowseFragmentArgs
import me.moallemi.carfinder.ui.cartype.browse.builtdate.BuiltDateListAdapter
import me.moallemi.carfinder.ui.cartype.search.base.BaseSearchableRecyclerFragment

class BuiltDateSearchFragment :
    BaseSearchableRecyclerFragment<BuiltDateItem, GetAllBuiltDatesUseCase.Params, BuiltDateSearchViewModel>(),
    OnRecyclerItemClickListener<BuiltDateItem> {

    private val fragmentArgs: BuiltDateBrowseFragmentArgs by lazy {
        BuiltDateBrowseFragmentArgs.fromBundle(arguments ?: throw IllegalStateException("argument must not be null"))
    }
    private lateinit var sharedViewModel: SharedViewModel

    override val adapter = BuiltDateListAdapter()

    init {
        recyclerItemClickListener = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = createSharedViewModel()
    }

    override fun makeViewModel(): BuiltDateSearchViewModel {
        return createViewModel(viewModelFactory)
    }

    override fun getParams(): GetAllBuiltDatesUseCase.Params {
        return GetAllBuiltDatesUseCase.Params(fragmentArgs.manufacturer, fragmentArgs.mainType)
    }

    override fun onItemClick(item: BuiltDateItem) {
        sharedViewModel.selectBuiltDateItem(item)
        activity?.onBackPressed()
    }

    companion object {
        fun newInstance(args: BuiltDateBrowseFragmentArgs): BuiltDateSearchFragment {
            return BuiltDateSearchFragment().apply {
                arguments = args.toBundle()
            }
        }
    }
}