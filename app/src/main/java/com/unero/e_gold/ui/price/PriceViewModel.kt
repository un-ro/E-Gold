package com.unero.e_gold.ui.price

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unero.e_gold.api.ApiRepo
import com.unero.e_gold.api.*

class PriceViewModel: ViewModel() {
    private val repository: ApiRepo
    private var priceMLD: MutableLiveData<Outer<Price>?> = MutableLiveData()

    init {
        this.repository = ApiRepo().getInstance()!!
        priceMLD = repository.getPrice()
    }

    fun getPrice(): LiveData<Outer<Price>?>{
        return priceMLD
    }
}