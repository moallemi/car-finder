package me.moallemi.carfinder.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import me.moallemi.carfinder.R

inline fun <reified T : ViewModel> Fragment.createViewModel(
    factory: ViewModelProvider.Factory,
    body: T.() -> Unit = {}
): T {
    val viewModel = ViewModelProviders.of(this, factory)[T::class.java]
    viewModel.body()
    return viewModel
}

inline fun <reified T : ViewModel> Fragment.createSharedViewModel(
    body: T.() -> Unit = {}
): T {
    val viewModel = ViewModelProviders.of(requireActivity())[T::class.java]
    viewModel.body()
    return viewModel
}

fun Fragment.navigateTo(fragment: Fragment) {
    fragmentManager?.beginTransaction()
        ?.add(R.id.contentFrame, fragment)
        ?.addToBackStack(null)
        ?.commit()
}