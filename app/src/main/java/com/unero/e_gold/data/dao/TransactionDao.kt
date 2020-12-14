package com.unero.e_gold.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.unero.e_gold.models.Transaction

@Dao
interface TransactionDao {

    @Insert
    suspend fun addTransaction(transaction: Transaction)

    @Query("DELETE FROM `transaction` WHERE id=:id")
    suspend fun deleteTransaction(id: Int)

    @Query("SELECT * FROM `transaction` ORDER BY id ASC")
    fun getTransaction(): LiveData<List<Transaction>>

    @Query("SELECT sum(weight) FROM `transaction`")
    fun getTotalWeight(): LiveData<Float>
}