package com.kuzmin.animals.feature.home.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kuzmin.animals.common.extension.getOrAwaitValue
import com.kuzmin.animals.feature.home.domain.model.Result
import com.kuzmin.animals.feature.home.domain.usecases.GetAllAnimalsUseCase
import com.kuzmin.animals.feature.home.ui.model.ParentItemFactory
import com.kuzmin.animals.feature.home.util.TestUtil
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.kotlin.any
import org.mockito.Mockito.anyList
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.Optional

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var homeViewModel: HomeViewModel? = null

    @Mock
    private lateinit var getAllAnimalsUseCase: GetAllAnimalsUseCase

    @Mock
    private lateinit var parentItemFactory: ParentItemFactory

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        homeViewModel = HomeViewModel(
            getAllAnimalsUseCase = getAllAnimalsUseCase,
            parentFactory = parentItemFactory,
            idleResource = Optional.empty()
        )
    }

    @After
    fun tearDown() {
        homeViewModel = null
    }

    @Test
    fun `should send success event`() {
        runTest {
            val testFetchedData = listOf(TestUtil.createAnimal())
            whenever(getAllAnimalsUseCase()).thenReturn(testFetchedData)

            homeViewModel!!.getAllAnimals()

            verify(getAllAnimalsUseCase).invoke()

            val actualValue = homeViewModel!!.animalsByTypes.getOrAwaitValue()
            assert(actualValue is Result.Success)
        }
    }

    @Test
    fun `should send error event`() {
        val expectedException = RuntimeException("test exception")
        runTest {
            whenever(getAllAnimalsUseCase()).thenThrow(expectedException)

            homeViewModel!!.getAllAnimals()

            verify(getAllAnimalsUseCase).invoke()

            val actualValue = homeViewModel!!.animalsByTypes.getOrAwaitValue()
            assert(actualValue is Result.Error)
        }
    }

    @Test
    fun `prepare parent ui data for RecyclerView`() {
        whenever(parentItemFactory.createParentItem(any(), any()))
            .thenReturn(TestUtil.createParentItemList()[0])

        val expected = TestUtil.createParentItemList()

        val actual = homeViewModel!!.prepareUiData(TestUtil.createMapAnimals())

        assertEquals(expected, actual)
    }
}