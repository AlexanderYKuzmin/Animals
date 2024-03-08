package com.kuzmin.animals.feature.home.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.kuzmin.animals.feature.home.TestUtil
import com.kuzmin.animals.feature.home.domain.model.Result
import com.kuzmin.animals.feature.home.domain.usecases.GetAllAnimalsUseCase
import com.kuzmin.animals.feature.home.ui.model.ParentItemFactory
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever

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
        homeViewModel = HomeViewModel(getAllAnimalsUseCase, parentItemFactory)
    }

    @After
    fun tearDown() {
        homeViewModel = null
    }

    @Test
    fun `should send success event`() {
        runTest {
            whenever(getAllAnimalsUseCase()).thenReturn(listOf(TestUtil.createAnimal()))
            homeViewModel!!.getAllAnimals()
        }
        val expected = TestUtil.createMapAnimals()

        val result = homeViewModel!!.animalsByTypes.value
        val actual = if (result is Result.Success) result.animals else null

        assertEquals(expected, actual)
    }

    @Test
    fun `should send error event`() {
        val expectedException = RuntimeException("test exception")
        runTest {
            whenever(getAllAnimalsUseCase()).thenThrow(expectedException)
            homeViewModel!!.getAllAnimals()
        }

        val result = homeViewModel!!.animalsByTypes.value
        val actual = if (result is Result.Error) result.throwable else null

        assertEquals(expectedException, actual)
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