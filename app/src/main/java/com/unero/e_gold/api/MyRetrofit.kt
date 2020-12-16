package com.unero.e_gold.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Singleton Retrofit
object MyRetrofit {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.tokopedia.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: Api by lazy {
        retrofit.create(Api::class.java)
    }
}