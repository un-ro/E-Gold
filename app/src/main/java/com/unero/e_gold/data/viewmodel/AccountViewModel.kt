package com.unero.e_gold.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.unero.e_gold.data.AppDatabase
import com.unero.e_gold.data.model.Account
import com.unero.e_gold.data.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class AccountViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AccountRepository

    init {
        val accountDao = AppDatabase.getDatabase(application).accountDao()
        repository = AccountRepository(accountDao)
    }

    // READ
    val accounts: LiveData<Account> = repository.accounts

    val anyAccount: LiveData<List<Account>> = repository.anyAccount

    // CREATE
    fun add(account: Account){
        // launch coroutine in background thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.add(account)
        }
    }

    // UPDATE
    fun update(account: Account){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(account)
        }
    }

}