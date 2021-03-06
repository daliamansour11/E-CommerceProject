package com.example.e_commerceproject.network.remotesource


import com.example.e_commerceproject.cart.model.CartListModel
import com.example.e_commerceproject.cart.model.CartModel
import com.example.e_commerceproject.cart.model.DraftOrder

import com.example.e_commerceproject.authentication.login.model.Customers
import com.example.e_commerceproject.authentication.register.model.CustomerAddress
import com.example.e_commerceproject.authentication.register.model.CustomerModel
import com.example.e_commerceproject.authentication.register.model.GetAddress
import com.example.e_commerceproject.authentication.register.model.PostAddress
import com.example.e_commerceproject.category.model.CategoryModel
import com.example.e_commerceproject.details.model.DetailsProductModel
import com.example.e_commerceproject.home.model.PriceRules
import com.example.e_commerceproject.payment.model.CouponsX
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

    @GET("draft_orders.json?limit=250")
    suspend fun getpostedOrder():Response<CartListModel>
    //https://shopify.dev/api/admin-rest/2022-04/resources/draftorder#get-draft-orders-draft-order-id
    // 873007513739
    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @PUT("draft_orders/{draft-order-id}.json?limit=250")
    suspend fun  updateCartOrder(@Path("draft-order-id")id :String , @Body cartItem: CartModel) : Response<CartModel>

//    @Headers(
//        "Accept: application/json",
//        "X-Shopify-Access-Token: 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",
//    )
//    @DELETE("draft_orders/{draft-order-id}.json")
//    suspend fun  deleteDraftOrder(@Path("draft-order-id")id: String):Response<CartModel>

    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )

    @DELETE("draft_orders/{draft_order_id}.json")
    suspend fun deleteCartItem(@Path("draft_order_id") draft_order_id: String):Response<CartModel>


    ///Coupons///

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
   @GET("price_rules/1089622311051/discount_codes.json")
    suspend fun getAvailableCoupons(): Response<CouponsX>

    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )

    @GET("discount_codes/lookup.json?")

    suspend fun validateCoupons(@Query("code") code: String): Response<CouponsX>


    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )

    @GET("price_rules/{price_rule_id}.json")

    suspend fun getPriceRuleDiscountValue(@Path("price_rule_id") price_rule_id: String): Response<PriceRules>



//    @GET("price_rules/507328175/discount_codes.json")
//    suspend fun getAllCoupons(@Query("count") dicount :Long
//    ): Response<Coupons>
    //Get all Coupons

    @GET("customers.json")
    suspend fun getCustomers():Response<Customers>

    // get customer for login

    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )

    @GET("customers.json?")
    suspend fun getCustomerById(@Query("email") email:String):Response<Customers>

//address//

    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @POST("customers/{customer_id}/addresses.json")
    suspend fun addAddress(@Path ("customer_id") customer_id : String , @Body address: PostAddress): Response<PostAddress>


    //get Address
    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @GET("customers/{customer_id}/addresses.json")
    suspend fun getAddress(@Path("customer_id") customer_id: String, ): Response<GetAddress>

    // favorite

    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @POST("draft_orders.json")
    suspend fun  postFavorieItem(@Body cartItem: CartModel): Response<CartModel>


    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )

    @GET("draft_orders.json?limit=250")
    suspend fun getFavoriteProducts():Response<CartListModel>


    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )

    @DELETE("draft_orders/{draft_order_id}.json")
    suspend fun deleteFavoriteItem(@Path("draft_order_id") draft_order_id: String):Response<CartModel>




    companion object {
        var retrofitService: RetrofitService? = null
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://madalex20220.myshopify.com/admin/api/2022-01/")
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