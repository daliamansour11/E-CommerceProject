package com.example.e_commerceproject.homesearch.viewmodel

import android.content.Context
import android.os.Looper
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.e_commerceproject.getOrAwaitValue
import com.example.e_commerceproject.home.viewmodel.HomeViewModel
import com.example.e_commerceproject.homesearch.model.FakeDataSource
import com.example.e_commerceproject.homesearch.model.HomeSearchRepository
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Shadows

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class HomeSearchViewModelTest{
    private lateinit var homeSearchRepositoryTest: HomeSearchRepository

    @Before
    fun setup() {
        var fakeRemoteSource= FakeDataSource()
        var context = ApplicationProvider.getApplicationContext<Context>()
        homeSearchRepositoryTest = HomeSearchRepository.getInstance(fakeRemoteSource,context)
    }

    @Test
    fun getAllProducts_getAllProductResponsee() = runBlockingTest{
       // val products = homeSearchRepositoryTest.getAllProducts()
        val homeSearchViewModel= HomeSearchViewModel(homeSearchRepositoryTest)
        homeSearchViewModel.getAllProducts()
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val products = homeSearchViewModel.productList.getOrAwaitValue()

        assertThat(products.size, IsEqual(3))
    }
}


