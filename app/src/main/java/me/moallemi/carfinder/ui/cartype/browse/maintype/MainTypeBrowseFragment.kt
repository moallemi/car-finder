package me.moallemi.carfinder.ui.cartype.browse.maintype

import android.os.Bundle
import me.moallemi.carfinder.domain.interactor.GetMainTypesUseCase
import me.moallemi.carfinder.extension.createSharedViewModel
import me.moallemi.carfinder.extension.createViewModel
import me.moallemi.carfinder.model.MainTypeItem
import me.moallemi.carfinder.ui.SharedViewModel
import me.moallemi.carfinder.ui.base.listener.OnRecyclerItemClickListener
import me.moallemi.carfinder.ui.base.recycler.BaseRecyclerFragment

class MainTypeBrowseFragment : BaseRecyclerFragment<MainTypeItem, GetMainTypesUseCase.Params, MainTypeViewModel>(),
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

        sharedViewModel = createSharedViewModel(viewModelFactory)
    }

    override fun makeViewModel(): MainTypeViewModel {
        return createViewModel(viewModelFactory)
    }

    override fun getParams(): GetMainTypesUseCase.Params {
        return GetMainTypesUseCase.Params(fragmentArgs.manufacturer, 0, 15)
    }

    override fun onItemClick(item: MainTypeItem) {
        sharedViewModel.selectMainTypeItem(item)
        activity?.onBackPressed()
    }

    companion object {
        fun newInstance(args: MainTypeBrowseFragmentArgs): MainTypeBrowseFragment {
            return MainTypeBrowseFragment().apply {
                arguments = args.toBundle()
            }
        }
    }
}