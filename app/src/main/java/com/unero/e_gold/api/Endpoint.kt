package com.unero.e_gold.api

import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {
    @GET("/emas/api/gold/price")
    fun getPrice(): Call<Outer<Price>>
}