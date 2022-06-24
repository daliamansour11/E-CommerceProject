package com.example.e_commerceproject.home.viewmodel

import android.content.Context
import android.os.Looper.getMainLooper
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.e_commerceproject.getOrAwaitValue
import com.example.e_commerceproject.home.model.FakeDataSource
import com.example.e_commerceproject.home.model.HomeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows.shadowOf


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {

    private lateinit var homeRepositoryTest: HomeRepository

    @Before
    fun setup() {
        var fakeRemoteSource = FakeDataSource()
        var context = ApplicationProvider.getApplicationContext<Context>()
        homeRepositoryTest = HomeRepository.getInstance(fakeRemoteSource, context)
    }

    @Test
    fun getAllBrands_getAllBrandResponse() = runBlockingTest {
      //  val brands = homeRepositoryTest.getAllBrands()
        val homeViewModel=HomeViewModel(homeRepositoryTest)
        homeViewModel.getAllBrands()
        shadowOf(getMainLooper()).idle()
        val brands = homeViewModel.brandList.getOrAwaitValue()

        assertThat(brands.size,IsEqual(3))
    }
}