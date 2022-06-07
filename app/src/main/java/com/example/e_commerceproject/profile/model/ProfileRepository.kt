package com.example.e_commerceproject.profile.model

import android.content.Context
import com.example.e_commerceproject.profile.client.ProfileRemoteSource

class ProfileRepository private constructor(
    var remoteSource: ProfileRemoteSource,
    var context: Context
) : ProfileRepositoryInterface {

    companion object{
        private var instance: ProfileRepository? = null
        fun getInstance(remoteSource: ProfileRemoteSource, context: Context): ProfileRepository {
            return instance?: ProfileRepository(
                remoteSource, context)
        }
    }

    override suspend fun getAllOrders(): List<OrderModel> {
        return remoteSource.getAllOrders()
    }


}