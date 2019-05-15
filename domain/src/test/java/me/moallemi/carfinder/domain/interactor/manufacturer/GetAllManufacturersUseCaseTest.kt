package me.moallemi.carfinder.domain.interactor.manufacturer

import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.Single
import me.moallemi.carfinder.domain.executor.PostExecutorThread
import me.moallemi.carfinder.domain.executor.UseCaseExecutorThread
import me.moallemi.carfinder.domain.interactor.CarTypeFactory
import me.moallemi.carfinder.domain.interactor.CarTypeFactory.createManufacturerList
import me.moallemi.carfinder.domain.repository.CarTypeRepository
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetAllManufacturersUseCaseTest {
    private val carTypeRepository: CarTypeRepository = mockk()
    private val useCaseExecutorThread: UseCaseExecutorThread = mockk(relaxed = true)
    private val postExecutorThread: PostExecutorThread = mockk(relaxed = true)
    private val useCase = GetAllManufacturersUseCase(carTypeRepository, useCaseExecutorThread, postExecutorThread)
    private val params = GetAllManufacturersUseCase.Params()

    @Before
    fun setUp() {
        clearAllMocks()
    }

    @Test
    fun `getManufacturersStream from repository was called`() {
        mockRepositoryGetAllManufacturers()

        useCase.execute(params, {}, {})

        verify(exactly = 1) { carTypeRepository.getManufacturersStream() }
    }

    @Test
    fun `getManufacturers when successful`() {
        mockRepositoryGetAllManufacturers()

        useCase.buildObservable(params)
            .test()
            .assertComplete()
            .assertNoErrors()
            .assertValueCount(2)
            .assertValues(createManufacturerList(), createManufacturerList())
    }

    @Test
    fun `getManufacturers when local data source fails`() {
        every {
            carTypeRepository.getManufacturersStream()
        } returns Observable.error(Exception())

        every {
            carTypeRepository.getManufacturers(any(), any())
        } returns Single.just(CarTypeFactory.createManufacturerPagedResult())
        every {
            carTypeRepository.storeManufacturers(any())
        } just Runs

        useCase.buildObservable(params)
            .test()
            .assertValues(createManufacturerList())
    }

    @Test
    fun `getManufacturers when remote data source fails`() {
        every {
            carTypeRepository.getManufacturersStream()
        } returns Observable.just(createManufacturerList())

        every {
            carTypeRepository.getManufacturers(any(), any())
        } returns Single.error(IOException())
        every {
            carTypeRepository.storeManufacturers(any())
        } just Runs

        useCase.buildObservable(params)
            .test()
            .assertValues(createManufacturerList())
    }

    @Test
    fun `getManufacturers when fails`() {
        every {
            carTypeRepository.getManufacturersStream()
        } returns Observable.error(Exception())

        every {
            carTypeRepository.getManufacturers(any(), any())
        } returns Single.error(IOException())
        every {
            carTypeRepository.storeManufacturers(any())
        } just Runs

        useCase.buildObservable(params)
            .test()
            .assertNoValues()
    }

    private fun mockRepositoryGetAllManufacturers() {
        every {
            carTypeRepository.getManufacturersStream()
        } returns Observable.just(createManufacturerList())

        every {
            carTypeRepository.getManufacturers(any(), any())
        } returns Single.just(CarTypeFactory.createManufacturerPagedResult())
        every {
            carTypeRepository.storeManufacturers(any())
        } just Runs
    }
}