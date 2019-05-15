package me.moallemi.carfinder.domain.interactor.maintype

import io.reactivex.Observable
import me.moallemi.carfinder.domain.executor.PostExecutorThread
import me.moallemi.carfinder.domain.executor.UseCaseExecutorThread
import me.moallemi.carfinder.domain.interactor.base.ObservableUseCase
import me.moallemi.carfinder.domain.repository.CarTypeRepository
import javax.inject.Inject

class GetAllMainTypesUseCase @Inject constructor(
    private val carTypeRepository: CarTypeRepository,
    useCaseExecutorThread: UseCaseExecutorThread,
    postExecutorThread: PostExecutorThread
) : ObservableUseCase<GetAllMainTypesUseCase.Params, List<String>>(
    useCaseExecutorThread,
    postExecutorThread
) {

    override fun buildObservable(params: Params): Observable<List<String>> {
        return Observable.concatArrayDelayError(
            allFromRemote(params),
            allFromLocal(params)
        )
    }

    private fun allFromLocal(params: Params): Observable<List<String>> {
        return carTypeRepository.getMainTypesStream(params.manufacturerCode)
    }

    private fun allFromRemote(params: Params): Observable<List<String>> {
        return carTypeRepository.getMainTypes(params.manufacturerCode, 0, Int.MAX_VALUE)
            .map { result ->
                result.items
            }.toObservable()
            .doOnNext { mainTypes ->
                carTypeRepository.storeMainTypes(params.manufacturerCode, mainTypes)
            }
    }

    data class Params(val manufacturerCode: String)
}