package me.moallemi.carfinder.domain.interactor

import io.reactivex.Single
import me.moallemi.carfinder.domain.executor.PostExecutorThread
import me.moallemi.carfinder.domain.executor.UseCaseExecutorThread
import me.moallemi.carfinder.domain.interactor.base.SingleUseCase
import me.moallemi.carfinder.domain.model.StringPagedResult
import me.moallemi.carfinder.domain.repository.CarTypeRepository
import javax.inject.Inject

class GetMainTypesUseCase @Inject constructor(
    private val carTypeRepository: CarTypeRepository,
    useCaseExecutorThread: UseCaseExecutorThread,
    postExecutorThread: PostExecutorThread
) : SingleUseCase<GetMainTypesUseCase.Params, StringPagedResult>(useCaseExecutorThread, postExecutorThread) {

    override fun buildSingle(params: Params): Single<StringPagedResult> {
        return carTypeRepository.getMainTypes(params.manufacturer, params.page, params.pageSize)
    }

    data class Params(
        val manufacturer: String,
        val page: Int,
        val pageSize: Int
    )
}