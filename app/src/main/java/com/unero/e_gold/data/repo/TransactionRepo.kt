package com.unero.e_gold.data.repo

import androidx.lifecycle.LiveData
import com.unero.e_gold.data.dao.TransactionDao
import com.unero.e_gold.models.Transaction

class TransactionRepo(private val transactionDao: TransactionDao) {
    val readAll: LiveData<List<Transaction>>
        get() = transactionDao.getTransaction()

    val totalWeight: LiveData<Float>
        get() = transactionDao.getTotalWeight()

    suspend fun addTransaction(transaction: Transaction){
        transactionDao.addTransaction(transaction)
    }

    suspend fun delete(id: Int){
        transactionDao.deleteTransaction(id)
    }
}