package me.moallemi.carfinder.domain.interactor.builtdate

import io.reactivex.Observable
import me.moallemi.carfinder.domain.executor.PostExecutorThread
import me.moallemi.carfinder.domain.executor.UseCaseExecutorThread
import me.moallemi.carfinder.domain.interactor.base.ObservableUseCase
import me.moallemi.carfinder.domain.repository.CarTypeRepository
import javax.inject.Inject

class GetAllBuiltDatesUseCase @Inject constructor(
    private val carTypeRepository: CarTypeRepository,
    useCaseExecutorThread: UseCaseExecutorThread,
    postExecutorThread: PostExecutorThread
) : ObservableUseCase<GetAllBuiltDatesUseCase.Params, List<String>>(
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
        return carTypeRepository.getBuiltDatesStream(params.manufacturerCode, params.mainType)
    }

    private fun allFromRemote(params: Params): Observable<List<String>> {
        return carTypeRepository.getBuiltDates(params.manufacturerCode, params.mainType)
            .toObservable()
            .doOnNext { buildDates ->
                carTypeRepository.storeBuiltDates(params.manufacturerCode, params.mainType, buildDates)
            }
    }

    data class Params(val manufacturerCode: String, val mainType: String)
}