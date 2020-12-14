package com.unero.e_gold.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.unero.e_gold.models.Profile

@Dao
interface ProfileDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addProfile(profile: Profile)

    @Update
    fun updateProfile(profile: Profile)

    @Query("SELECT * FROM  profile ORDER BY id ASC")
    fun getProfiles(): LiveData<Profile>
}