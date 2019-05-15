package me.moallemi.carfinder.ui.cartype.browse.manufacturer

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.invoke
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import me.moallemi.carfinder.domain.interactor.manufacturer.GetManufacturersUseCase
import me.moallemi.carfinder.domain.model.Manufacturer
import me.moallemi.carfinder.domain.model.ManufacturerPagedResult
import me.moallemi.carfinder.model.ResourceState
import me.moallemi.carfinder.model.toManufactureItem
import me.moallemi.carfinder.ui.cartype.CarTypeFactory
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class ManufacturerBrowseViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val useCase: GetManufacturersUseCase = mockk(relaxed = true)
    private val viewModel = ManufacturerBrowseViewModel(useCase)
    private val params = GetManufacturersUseCase.Params(0, 15)

    @Before
    fun setup() {
        clearAllMocks()
    }

    @Test
    fun `useCase executes`() {
        viewModel.load(params)
        verify(exactly = 1) { useCase.execute(any(), any(), any()) }
    }

    @Test
    fun `useCase when successful`() {
        val success = slot<(ManufacturerPagedResult) -> Unit>()

        every {
            useCase.execute(any(), capture(success), any())
        } answers {
            success.invoke(CarTypeFactory.createManufacturerPagedResult())
        }

        viewModel.load(params)
        assert(viewModel.items.value?.resourceState == ResourceState.SUCCESS)
    }

    @Test
    fun `useCase when successful and contains data`() {
        val success = slot<(ManufacturerPagedResult) -> Unit>()

        every {
            useCase.execute(any(), capture(success), any())
        } answers {
            success.invoke(CarTypeFactory.createManufacturerPagedResult())
        }

        viewModel.load(params)
        assert(viewModel.items.value?.resourceState == ResourceState.SUCCESS)
        assertEquals(
            viewModel.items.value?.data,
            CarTypeFactory.createManufacturerList().mapIndexed { index: Int, manufacturer: Manufacturer ->
                manufacturer.toManufactureItem(index % 2 == 0)
            }
        )
    }

    @Test
    fun `getMarketPriceChartUseCase success and contains no error`() {
        val success = slot<(ManufacturerPagedResult) -> Unit>()

        every {
            useCase.execute(any(), capture(success), any())
        } answers {
            success.invoke(CarTypeFactory.createManufacturerPagedResult())
        }

        viewModel.load(params)
        assert(viewModel.items.value?.resourceState == ResourceState.SUCCESS)
        assert(viewModel.items.value?.failure == null)
    }

    @Test
    fun `useCase when fails`() {
        val error = slot<(throwable: Throwable) -> Unit>()

        every {
            useCase.execute(any(), any(), capture(error))
        } answers {
            error.invoke(IOException())
        }

        viewModel.load(params)
        assert(viewModel.items.value?.resourceState == ResourceState.ERROR)
        assert(viewModel.items.value?.failure is IOException)
    }

    @Test
    fun `useCase returns loading`() {
        viewModel.load(params)
        assert(viewModel.items.value?.resourceState == ResourceState.LOADING)
    }

    @Test
    fun `useCase contains no data when loading`() {
        viewModel.load(params)

        assert(viewModel.items.value?.resourceState == ResourceState.LOADING)
        assert(viewModel.items.value?.data == null)
    }

    @Test
    fun `useCase contains no error when loading`() {
        viewModel.load(params)

        assert(viewModel.items.value?.resourceState == ResourceState.LOADING)
        assert(viewModel.items.value?.failure == null)
    }
}