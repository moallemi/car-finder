package me.moallemi.carfinder.domain.interactor.manufacturer

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

class GetManufacturersUseCaseTest {

    private val carTypeRepository: CarTypeRepository = mockk()
    private val useCaseExecutorThread: UseCaseExecutorThread = mockk(relaxed = true)
    private val postExecutorThread: PostExecutorThread = mockk(relaxed = true)
    private val useCase = GetManufacturersUseCase(carTypeRepository, useCaseExecutorThread, postExecutorThread)
    private val params = GetManufacturersUseCase.Params(0, 10)

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `getManufacturers from repository was called`() {
        mockRepositoryGetManufacturers()

        useCase.execute(params, {}, {})

        verify(exactly = 1) { carTypeRepository.getManufacturers(any(), any()) }
    }

    @Test
    fun `getManufacturers when successful`() {
        mockRepositoryGetManufacturers()

        useCase.buildSingle(params)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(CarTypeFactory.createManufacturerPagedResult())
    }

    @Test
    fun `getManufacturers when fails`() {
        every { carTypeRepository.getManufacturers(any(), any()) } returns Single.error(IOException())

        useCase.buildSingle(params)
            .test()
            .assertNotComplete()
            .assertFailure(IOException::class.java)
    }

    private fun mockRepositoryGetManufacturers() {
        every {
            carTypeRepository.getManufacturers(
                any(),
                any()
            )
        } returns Single.just(CarTypeFactory.createManufacturerPagedResult())
    }
}