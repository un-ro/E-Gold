package com.unero.e_gold.api

import retrofit2.http.GET

interface Api {
    @GET("/emas/api/gold/price")
    suspend fun getPrice(): Hasil
}