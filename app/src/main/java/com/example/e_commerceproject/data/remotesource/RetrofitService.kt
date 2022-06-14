package com.example.e_commerceproject.data.remotesource

import com.example.e_commerceproject.authentication.login.model.Customerr
import com.example.e_commerceproject.authentication.login.model.Customers
import com.example.e_commerceproject.authentication.register.model.CustomerModel
import com.example.e_commerceproject.category.model.CategoryModel
import com.example.e_commerceproject.details.model.DetailsProductModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitService {

    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @GET("products/{product_id}.json")
    suspend fun getProductInfo(@Path("product_id") product_id: String) : Response<DetailsProductModel>

    @GET("collections/{{collection_id}}/products.json")
    suspend fun getProductOfCategory(@Path("{collection_id}") categoryId: Long) : Response<CategoryModel>

    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )

    @GET("products.json")
    suspend fun getAllProducts(
        @Query("collection_id") collection_id : String,
        @Query("product_type") product_type : String,
        @Query("vendor") vendor : String,
    ): Response<CategoryModel>
    //https://madalex20220.myshopify.com/admin/api/2022-04/products.json?collection_id=273053745291&product_type=""&vendor=""
    // products.json?product_type=SHOES&collection_id=273053745291
    @GET("products.json?")
    suspend fun getSubCategory(@Query("product_type") product_type:String, @Query("collection_id") collection_id : Long ) : Response<CategoryModel>

    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )

    @POST("customers.json")
    suspend fun postCustomer(@Body customerModel: CustomerModel):Response<CustomerModel>


    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )

    @GET("customers.json")
    suspend fun getCustomers():Response<Customers>

    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )

    @GET("customers.json?")
    suspend fun getCustomerById(@Query("email") email:String):Response<Customers>



    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://madalex20220.myshopify.com/admin/api/2022-04/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}

interface  Retrofit2{

//    @POST("posts")
//    suspend fun postCustomer(@Body post: Post):Response<Post>
//
//
//
//    companion object {
//        var retrofitService: Retrofit2? = null
//        fun getInstance() : Retrofit2 {
//            if (retrofitService == null) {
//                val retrofit = Retrofit.Builder()
//                    .baseUrl("https://jsonplaceholder.typicode.com/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//                retrofitService = retrofit.create(Retrofit2::class.java)
//            }
//            return retrofitService!!
//        }
//
//    }

}