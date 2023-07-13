package com.kurotkin.netdemo.presentation

import android.util.Log
import com.kurotkin.netdemo.data.ApiService
import com.kurotkin.netdemo.data.products.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Note1() {

    // TODO: Крайне примитивный способ
    fun primitive() {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dummyjson.com")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val productAPI = retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val productCart = productAPI.getMyProductCart()
            Log.d("Tag", productCart.toString())
            methods(productAPI)
        }
    }


    // TODO: Раздичные методы
    suspend fun methods(productAPI: ApiService) {
        productAPI.apply {
            getMyProduct(1)
            addMyProduct(
                Product(
                    id = 99,
                    title = "Key Holder",
                    description = "Attractive DesignMetallic materialFour key hooksReliable & DurablePremium Quality",
                    price = 30,
                    discountPercentage = 2.92,
                    rating = 4.92,
                    stock = 54,
                    brand = "Golden",
                    category = "home-decoration",
                    thumbnail = "https://i.dummyjson.com/data/products/30/thumbnail.jpg",
                    images = arrayListOf("https://i.dummyjson.com/data/products/30/1.jpg")
                )
            )
            deleteMyProduct(1)
        }
    }
}