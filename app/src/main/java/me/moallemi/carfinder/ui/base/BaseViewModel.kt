package me.moallemi.carfinder.ui.base

import android.os.Bundle
import androidx.lifecycle.ViewModel
import me.moallemi.carfinder.domain.interactor.base.UseCase

abstract class BaseViewModel : ViewModel() {

    protected val useCases: MutableList<UseCase> = mutableListOf()

    private fun dispose() {
        for (useCase in useCases) {
            useCase.dispose()
        }
    }

    override fun onCleared() {
        super.onCleared()
        dispose()
    }

    open fun onSaveState(bundle: Bundle) {}

    open fun onRestoreState(bundle: Bundle) {}
}