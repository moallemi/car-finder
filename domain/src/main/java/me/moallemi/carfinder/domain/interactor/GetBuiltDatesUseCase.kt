package me.moallemi.carfinder.domain.interactor

import io.reactivex.Single
import me.moallemi.carfinder.domain.executor.PostExecutorThread
import me.moallemi.carfinder.domain.executor.UseCaseExecutorThread
import me.moallemi.carfinder.domain.interactor.base.SingleUseCase
import me.moallemi.carfinder.domain.repository.CarTypeRepository
import javax.inject.Inject

class GetBuiltDatesUseCase @Inject constructor(
    private val carTypeRepository: CarTypeRepository,
    useCaseExecutorThread: UseCaseExecutorThread,
    postExecutorThread: PostExecutorThread
) : SingleUseCase<GetBuiltDatesUseCase.Params, List<String>>(useCaseExecutorThread, postExecutorThread) {

    override fun buildSingle(params: Params): Single<List<String>> {
        return carTypeRepository.getBuiltDates(params.manufacturer, params.mainType)
    }

    data class Params(
        val manufacturer: String,
        val mainType: String
    )
}