package me.moallemi.carfinder.domain.interactor.builtdate

import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single
import me.moallemi.carfinder.domain.executor.PostExecutorThread
import me.moallemi.carfinder.domain.executor.UseCaseExecutorThread
import me.moallemi.carfinder.domain.repository.CarTypeRepository
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetBuiltDatesUseCaseTest {

    private val carTypeRepository: CarTypeRepository = mockk()
    private val useCaseExecutorThread: UseCaseExecutorThread = mockk(relaxed = true)
    private val postExecutorThread: PostExecutorThread = mockk(relaxed = true)
    private val useCase = GetBuiltDatesUseCase(carTypeRepository, useCaseExecutorThread, postExecutorThread)
    private val params = GetBuiltDatesUseCase.Params("Kia", "Pride")

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `getBuiltDates from repository was called`() {
        mockRepositoryGetBuiltDatesResponse()

        useCase.execute(params, {}, {})

        verify(exactly = 1) { carTypeRepository.getBuiltDates(any(), any()) }
    }

    @Test
    fun `getBuiltDates when successful`() {
        mockRepositoryGetBuiltDatesResponse()

        useCase.buildSingle(params)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValue(listOf())
    }

    @Test
    fun `getBuiltDates when fails`() {
        every { carTypeRepository.getBuiltDates(any(), any()) } returns Single.error(IOException())

        useCase.buildSingle(params)
            .test()
            .assertNotComplete()
            .assertFailure(IOException::class.java)
    }

    private fun mockRepositoryGetBuiltDatesResponse() {
        every {
            carTypeRepository.getBuiltDates(
                any(),
                any()
            )
        } returns Single.just(listOf())
    }
}