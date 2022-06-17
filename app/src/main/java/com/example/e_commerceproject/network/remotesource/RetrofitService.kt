package com.example.e_commerceproject.network.remotesource


import com.example.e_commerceproject.cart.model.CartListModel
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.cart.model.DraftOrder

import com.example.e_commerceproject.authentication.login.model.Customerr
import com.example.e_commerceproject.authentication.login.model.Customers
import com.example.e_commerceproject.authentication.register.model.CustomerModel
import com.example.e_commerceproject.category.model.CategoryModel
import com.example.e_commerceproject.details.model.DetailsProductModel
import com.example.e_commerceproject.payment.model.CouponsX
import com.example.e_commerceproject.payment.model.DiscountCode
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
    @POST("draft_orders.json")
    suspend fun  postCartOrder(@Body cartItem: CartModel): Response<CartModel>
    ////////// get cart/////
    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )

    ///873007775883

    @GET("draft_orders/873007775883.json")
    suspend fun getpostedOrder():Response<CartModel>

    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @PUT("draft_orders/873007775883.json")
    suspend   fun  updateCartOrder(@Body cartItem: DraftOrder): Response<CartModel>


//Coupons///

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
    @GET("2022-01/price_rules/1089622311051/discount_codes.json")
    suspend fun getAvailableCoupons(): Response<CouponsX>

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
                    .baseUrl("https://9d169ad72dd7620e70f56b28ae6146d9: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@madalex20220.myshopify.com/admin/api/")
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