package com.kurotkin.netdemo.data

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.kurotkin.netdemo.data.products.Product
import com.kurotkin.netdemo.data.products.ProductCart
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface ApiService {

    // TODO: Простой способ
    @GET("/products")
    suspend fun getMyProductCart(): ProductCart

    @GET("/products/{productId}")
    suspend fun getMyProduct(@Path("productId") id: Int): Product

    @POST("/products/add")
    suspend fun addMyProduct(@Body newProduct: Product): Product

    @PUT("/products/{productId}")
    suspend fun updateMyProduct(@Path("productId") id: Int, @Body product: Product): Product

    @DELETE("/products/{productId}")
    suspend fun deleteMyProduct(@Path("productId") id: Int): Product


    // TODO: Более структурированный
    @GET("/products")
    suspend fun getProductCart(): Response<ProductCart>

    @GET("/products/{productId}")
    suspend fun getProduct(@Path("productId") id: Int): Response<Product>


    // TODO: Создание Retrofit
    companion object {
        operator fun invoke(): ApiService {
            val gson = GsonBuilder().setLenient().create()
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().setLevel( HttpLoggingInterceptor.Level.BODY )
                )
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://dummyjson.com")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiService::class.java)
        }
    }
}