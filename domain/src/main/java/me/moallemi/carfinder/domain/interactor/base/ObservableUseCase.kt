package me.moallemi.carfinder.domain.interactor.base

import io.reactivex.Observable
import me.moallemi.carfinder.domain.executor.PostExecutorThread
import me.moallemi.carfinder.domain.executor.UseCaseExecutorThread

abstract class ObservableUseCase<in Params, Result>(
    private val useCaseExecutorThread: UseCaseExecutorThread,
    private val postExecutorThread: PostExecutorThread
) : UseCase() {

    abstract fun buildObservable(params: Params): Observable<Result>

    fun execute(
        params: Params,
        success: (Result) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        buildObservable(params)
            .subscribeOn(useCaseExecutorThread.scheduler)
            .observeOn(postExecutorThread.scheduler)
            .subscribe({
                success(it)
            }, {
                failure(it)
            })
            .also {
                addDisposable(it)
            }
    }
}