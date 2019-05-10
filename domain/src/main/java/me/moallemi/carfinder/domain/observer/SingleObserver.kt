package me.moallemi.carfinder.domain.observer

import io.reactivex.observers.DisposableSingleObserver

class SingleObserver<T>(
    private val success: (T) -> Unit,
    private val failure: (Throwable) -> Unit
) : DisposableSingleObserver<T>() {

    override fun onSuccess(t: T) {
        success(t)
        dispose()
    }

    override fun onError(e: Throwable) {
        failure(e)
        dispose()
    }
}