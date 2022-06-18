package com.example.e_commerceproject.profile.model

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ProfileOrderRepositoryTest{

    private lateinit var ProfileOrderRepositoryTest: ProfileRepository

    @Before
    fun setup() {
        var fakeRemoteSource= FakeDataSource()
        var context = ApplicationProvider.getApplicationContext<Context>()
        ProfileOrderRepositoryTest = ProfileRepository.getInstance(fakeRemoteSource,context)
    }

    @Test
    fun getAllOrders_getAllOrdersResponsee() = runBlockingTest{
        val orders = ProfileOrderRepositoryTest.getAllOrders() // not sure

        assertThat(orders.size, IsEqual(3))
    }
}