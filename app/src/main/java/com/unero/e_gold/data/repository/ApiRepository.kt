package com.unero.e_gold.data.repository

import com.unero.e_gold.api.Hasil
import com.unero.e_gold.api.MyRetrofit

class ApiRepository {
    suspend fun getPrice(): Hasil{
        return MyRetrofit.api.getPrice()
    }
}