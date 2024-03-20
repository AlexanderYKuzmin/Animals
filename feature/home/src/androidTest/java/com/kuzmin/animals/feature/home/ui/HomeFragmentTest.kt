package com.kuzmin.animals.feature.home.ui

import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kuzmin.animals.feature.home.api.MediaService
import com.kuzmin.animals.feature.home.di.IdlingResourceModule
import com.kuzmin.animals.feature.home.di.launchFragmentInHiltContainer
import com.kuzmin.animals.feature.home.test_util.TestUtil
import com.kuzmin.animals.feature.home.test_util.MockitoHelper
import com.kuzmin.animals.feature.home.ui.viewmodels.AnimalViewModel
import com.kuzmin.animals.feature.home.ui.viewmodels.HomeViewModel
import com.kuzmin.animals.feature.home.domain.model.Result
import com.kuzmin.animals.feature.home.domain.usecases.GetAllAnimalsUseCase
import com.kuzmin.animals.feature.home.test_util.MockitoHelper.anyObject
import com.kuzmin.animals.feature.home.ui.adapters.ParentAdapter
import com.kuzmin.animals.feature.home.ui.model.ParentItemFactory
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.util.Optional
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HomeFragmentTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @BindValue
    @Mock
    lateinit var homeViewModel: HomeViewModel

    @Mock
    lateinit var drawable: AnimationDrawable

    @Mock
    lateinit var parentAdapter: ParentAdapter

    @Mock
    lateinit var mediaService: MediaService

    @BindValue
    lateinit var animalViewModel: AnimalViewModel

    /*@BindValue
    var animalViewModel: AnimalViewModel? = mock(AnimalViewModel::class.java)*/

    private val animalsByTypes = MutableLiveData<Result>()


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun prepareParentsByAnimalData() {
        //`when`(drawable.start()).thenReturn(Unit)
        `when`(homeViewModel.prepareUiData(anyMap())).thenReturn(TestUtil.createParentItemList())
        `when`(homeViewModel.animalsByTypes).thenReturn(animalsByTypes)

        val fragmentScenario = launchFragmentInHiltContainer<HomeFragment>(

        ) {
            val viewLifecycleOwner = this.viewLifecycleOwner

        }

        //animalsByTypes.value = Result.Success(mapOf())

        //verify(homeViewModel).prepareUiData(anyMap())
    }
}