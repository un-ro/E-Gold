package com.unero.e_gold.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.unero.e_gold.data.model.Transact

@Dao
interface TransactDao {
    @Insert
    suspend fun add(transact: Transact)

    @Query("SELECT * FROM transact ORDER BY id ASC")
    fun listTransact(): LiveData<List<Transact>>

    @Query("SELECT sum(weight) FROM transact")
    fun getTotalWeight(): LiveData<Float>

    @Query("SELECT sum(price) FROM transact")
    fun getTotalBuy(): LiveData<Int>
}