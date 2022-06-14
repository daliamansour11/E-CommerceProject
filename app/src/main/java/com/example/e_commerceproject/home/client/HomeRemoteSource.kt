package com.example.e_commerceproject.home.client

import com.example.e_commerceproject.home.model.BrandModel

interface HomeRemoteSource {

   suspend fun getAllBrandResponse(): List<BrandModel>
}