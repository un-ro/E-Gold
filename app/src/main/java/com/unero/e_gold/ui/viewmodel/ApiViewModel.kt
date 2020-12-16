package com.unero.e_gold.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.unero.e_gold.api.Hasil
import com.unero.e_gold.data.repository.ApiRepository
import kotlinx.coroutines.launch

class ApiViewModel(private val repository: ApiRepository): ViewModel() {
    val responses: MutableLiveData<Hasil> = MutableLiveData()

    fun getPrice(){
        viewModelScope.launch {
            val response = repository.getPrice()
            responses.value = response
        }
    }
}

class ApiFactory(private val repository: ApiRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ApiViewModel(repository) as T
    }
}