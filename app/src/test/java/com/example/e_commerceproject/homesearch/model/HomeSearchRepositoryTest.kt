package com.example.e_commerceproject.homesearch.model

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.e_commerceproject.homesearch.client.HomeSearchRemoteSource
import junit.framework.TestCase
import com.example.e_commerceproject.homesearch.model.FakeDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class HomeSearchRepositoryTest{

    private lateinit var homeSearchRepositoryTest: HomeSearchRepository

    @Before
    fun setup() {
        var fakeRemoteSource= FakeDataSource()
        var context = ApplicationProvider.getApplicationContext<Context>()
        homeSearchRepositoryTest = HomeSearchRepository.getInstance(fakeRemoteSource,context)
    }

    @Test
    fun getAllProducts_getAllProductResponsee() = runBlockingTest{
        val products = homeSearchRepositoryTest.getAllProducts()

        assertThat(products.size, IsEqual(3))
    }
}