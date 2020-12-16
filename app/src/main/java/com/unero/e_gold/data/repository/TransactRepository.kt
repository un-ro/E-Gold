package com.unero.e_gold.data.repository

import androidx.lifecycle.LiveData
import com.unero.e_gold.data.dao.TransactDao
import com.unero.e_gold.data.model.Transact

class TransactRepository(private val transactDao: TransactDao) {
    val listTransact: LiveData<List<Transact>> = transactDao.listTransact()

    val totalWeight: LiveData<Float> = transactDao.getTotalWeight()

    val totalBuy: LiveData<Int> = transactDao.getTotalBuy()

    suspend fun add(transact: Transact){
        transactDao.add(transact)
    }
}