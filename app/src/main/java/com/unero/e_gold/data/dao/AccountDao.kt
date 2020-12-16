package com.unero.e_gold.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.unero.e_gold.data.model.Account

@Dao
interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(account: Account)

    @Update
    suspend fun update(account: Account)

    @Query("SELECT * FROM account")
    fun accounts(): LiveData<Account>
}