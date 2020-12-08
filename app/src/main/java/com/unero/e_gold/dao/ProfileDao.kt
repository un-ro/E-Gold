package com.unero.e_gold.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.unero.e_gold.models.Profile

@Dao
interface ProfileDao {
    @Query("SELECT * FROM profile")
    fun getProfile(): LiveData<Profile>

    @Insert(onConflict = REPLACE)
    fun insert(profile: Profile)

    @Update
    fun update(profile: Profile)

    @Query("DELETE FROM profile")
    fun deleteAll()
}