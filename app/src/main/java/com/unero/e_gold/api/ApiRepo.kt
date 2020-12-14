package com.unero.e_gold.api

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRepo() {
    private var apiInterface: Endpoint = Service.buildService(Endpoint::class.java)
    private val price: MutableLiveData<Outer<Price>?> = MutableLiveData()

    private var priceRepository: ApiRepo? = null

    fun getInstance(): ApiRepo? {
        if (priceRepository == null) {
            priceRepository = ApiRepo()
        }
        return priceRepository
    }

    fun getPrice(): MutableLiveData<Outer<Price>?> {
        val priceData: Call<Outer<Price>> = apiInterface.getPrice()
        priceData.enqueue(object : Callback<Outer<Price>?> {
            override fun onResponse(
                call: Call<Outer<Price>?>,
                response: Response<Outer<Price>?>
            ) {
                price.setValue(response.body())
            }
            override fun onFailure(call: Call<Outer<Price>?>, t: Throwable) {
                price.setValue(null)
            }
        })
        return price
    }
}