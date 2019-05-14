package me.moallemi.carfinder.domain.interactor

import io.reactivex.Observable
import me.moallemi.carfinder.domain.executor.PostExecutorThread
import me.moallemi.carfinder.domain.executor.UseCaseExecutorThread
import me.moallemi.carfinder.domain.interactor.base.ObservableUseCase
import me.moallemi.carfinder.domain.model.Manufacturer
import me.moallemi.carfinder.domain.repository.CarTypeRepository
import javax.inject.Inject

class GetAllManufacturersUseCase @Inject constructor(
    private val carTypeRepository: CarTypeRepository,
    useCaseExecutorThread: UseCaseExecutorThread,
    postExecutorThread: PostExecutorThread
) : ObservableUseCase<GetAllManufacturersUseCase.Params, List<Manufacturer>>(
    useCaseExecutorThread,
    postExecutorThread
) {

    override fun buildObservable(params: Params): Observable<List<Manufacturer>> {
        return Observable.concatArrayDelayError(
            allFromRemote(),
            allFromLocal()
        )
    }

    private fun allFromLocal(): Observable<List<Manufacturer>> {
        return carTypeRepository.getManufacturersStream()
    }

    private fun allFromRemote(): Observable<List<Manufacturer>> {
        return carTypeRepository.getManufacturers(0, Int.MAX_VALUE)
            .map { result ->
                result.items
            }.toObservable()
            .doOnNext { manufacturers ->
                carTypeRepository.storeManufacturers(manufacturers)
            }
    }

    class Params
}