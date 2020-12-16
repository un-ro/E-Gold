package com.unero.e_gold.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.unero.e_gold.data.AppDatabase
import com.unero.e_gold.data.model.Transact
import com.unero.e_gold.data.repository.TransactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class TransactViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TransactRepository

    init {
        val transactDao = AppDatabase.getDatabase(application).transactDao()
        repository = TransactRepository(transactDao)
    }

    // READ ALL ORDER BY id
    val listTransact: LiveData<List<Transact>> = repository.listTransact

    val totalBuy: LiveData<Int> = repository.totalBuy

    val totalWeight: LiveData<Float> = repository.totalWeight

    // CREATE
    fun add(transact: Transact){
        viewModelScope.launch(Dispatchers.IO){
            repository.add(transact)
        }
    }
}