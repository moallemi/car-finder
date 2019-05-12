package me.moallemi.carfinder.domain.interactor.base

import io.reactivex.Single
import me.moallemi.carfinder.domain.executor.PostExecutorThread
import me.moallemi.carfinder.domain.executor.UseCaseExecutorThread
import me.moallemi.carfinder.domain.observer.SingleObserver

abstract class SingleUseCase<in Params, Result>(
    private val useCaseExecutorThread: UseCaseExecutorThread,
    private val postExecutorThread: PostExecutorThread
) : UseCase() {

    abstract fun buildSingle(params: Params): Single<Result>

    fun execute(
        params: Params,
        success: (Result) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        buildSingle(params)
            .subscribeOn(useCaseExecutorThread.scheduler)
            .observeOn(postExecutorThread.scheduler)
            .subscribeWith(SingleObserver(success, failure))
            .also { observer ->
                addDisposable(observer)
            }
    }
}