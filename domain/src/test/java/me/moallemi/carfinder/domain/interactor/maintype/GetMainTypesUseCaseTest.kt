package me.moallemi.carfinder.domain.interactor.maintype

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import me.moallemi.carfinder.domain.executor.PostExecutorThread
import me.moallemi.carfinder.domain.executor.UseCaseExecutorThread
import me.moallemi.carfinder.domain.interactor.CarTypeFactory
import me.moallemi.carfinder.domain.repository.CarTypeRepository
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetMainTypesUseCaseTest {

    private val carTypeRepository: CarTypeRepository = mockk()
    private val useCaseExecutorThread: UseCaseExecutorThread = mockk(relaxed = true)
    private val postExecutorThread: PostExecutorThread = mockk(relaxed = true)
    private val useCase = GetMainTypesUseCase(carTypeRepository, useCaseExecutorThread, postExecutorThread)
    private val params = GetMainTypesUseCase.Params("107", 0, 10)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `getMainTypes from repository was called`() {
        mockRepositoryGetMainTypesResponse()

        useCase.execute(params, {}, {})

        verify(exactly = 1) { carTypeRepository.getMainTypes(any(), any(), any()) }
    }

    @Test
    fun `getMainTypes when successful`() {
        mockRepositoryGetMainTypesResponse()

        useCase.buildSingle(params)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(CarTypeFactory.createMainTypes())
    }

    @Test
    fun `getMainTypes when fails`() {
        every { carTypeRepository.getMainTypes(any(), any(), any()) } returns Single.error(IOException())

        useCase.buildSingle(params)
            .test()
            .assertNotComplete()
            .assertFailure(IOException::class.java)
    }

    private fun mockRepositoryGetMainTypesResponse() {
        every {
            carTypeRepository.getMainTypes(
                any(),
                any(),
                any()
            )
        } returns Single.just(CarTypeFactory.createMainTypes())
    }
}