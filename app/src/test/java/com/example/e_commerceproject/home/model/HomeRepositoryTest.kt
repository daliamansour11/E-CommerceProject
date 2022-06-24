package com.example.e_commerceproject.home.model

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class HomeRepositoryTest {

    private lateinit var homeRepositoryTest: HomeRepository

    @Before
    fun setup() {
        var fakeRemoteSource = FakeDataSource()
        var context = ApplicationProvider.getApplicationContext<Context>()
        homeRepositoryTest = HomeRepository.getInstance(fakeRemoteSource, context)
    }

    @Test
     fun getAllBrands_getAllBrandResponse() = runBlockingTest{
     val brands = homeRepositoryTest.getAllBrands()

        assertThat(brands.size, IsEqual(4))
    }
}