package me.moallemi.carfinder.ui.cartype.search.maintype

import android.os.Bundle
import me.moallemi.carfinder.domain.interactor.GetAllMainTypesUseCase
import me.moallemi.carfinder.extension.createSharedViewModel
import me.moallemi.carfinder.extension.createViewModel
import me.moallemi.carfinder.model.MainTypeItem
import me.moallemi.carfinder.ui.SharedViewModel
import me.moallemi.carfinder.ui.base.listener.OnRecyclerItemClickListener
import me.moallemi.carfinder.ui.cartype.browse.maintype.MainTypeBrowseFragmentArgs
import me.moallemi.carfinder.ui.cartype.browse.maintype.MainTypeListAdapter
import me.moallemi.carfinder.ui.cartype.search.base.BaseSearchableRecyclerFragment

class MainTypeSearchFragment :
    BaseSearchableRecyclerFragment<MainTypeItem, GetAllMainTypesUseCase.Params, MainTypeSearchViewModel>(),
    OnRecyclerItemClickListener<MainTypeItem> {

    private val fragmentArgs: MainTypeBrowseFragmentArgs by lazy {
        MainTypeBrowseFragmentArgs.fromBundle(arguments ?: throw IllegalStateException("argument must not be null"))
    }
    private lateinit var sharedViewModel: SharedViewModel

    override val adapter = MainTypeListAdapter()

    init {
        recyclerItemClickListener = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedViewModel = createSharedViewModel()
    }

    override fun makeViewModel(): MainTypeSearchViewModel {
        return createViewModel(viewModelFactory)
    }

    override fun getParams(): GetAllMainTypesUseCase.Params {
        return GetAllMainTypesUseCase.Params(fragmentArgs.manufacturer)
    }

    override fun onItemClick(item: MainTypeItem) {
        sharedViewModel.selectMainTypeItem(item)
        activity?.onBackPressed()
    }

    companion object {
        fun newInstance(args: MainTypeBrowseFragmentArgs): MainTypeSearchFragment {
            return MainTypeSearchFragment().apply {
                arguments = args.toBundle()
            }
        }
    }
}