package com.unero.e_gold.data.repository

import androidx.lifecycle.LiveData
import com.unero.e_gold.data.dao.AccountDao
import com.unero.e_gold.data.model.Account

class AccountRepository(private val accountDao: AccountDao) {
    val accounts: LiveData<Account> = accountDao.accounts()

    val anyAccount: LiveData<List<Account>> = accountDao.anyAccount()

    suspend fun add(account: Account){
        accountDao.add(account)
    }

    suspend fun update(account: Account){
        accountDao.update(account)
    }
}