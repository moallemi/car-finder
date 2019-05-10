package me.moallemi.carfinder.domain.interactor

import io.reactivex.Single
import me.moallemi.carfinder.domain.executor.PostExecutorThread
import me.moallemi.carfinder.domain.executor.UseCaseExecutorThread
import me.moallemi.carfinder.domain.interactor.base.SingleUseCase
import me.moallemi.carfinder.domain.model.ManufacturerPagedResult
import me.moallemi.carfinder.domain.repository.CarTypeRepository
import javax.inject.Inject

class GetManufacturersUseCase @Inject constructor(
    private val carTypeRepository: CarTypeRepository,
    useCaseExecutorThread: UseCaseExecutorThread,
    postExecutorThread: PostExecutorThread
) : SingleUseCase<GetManufacturersUseCase.Params, ManufacturerPagedResult>(useCaseExecutorThread, postExecutorThread) {

    override fun buildSingle(params: Params): Single<ManufacturerPagedResult> {
        return carTypeRepository.getManufacturers(params.page, params.pageSize)
    }

    data class Params(
        val page: Int,
        val pageSize: Int
    )
}