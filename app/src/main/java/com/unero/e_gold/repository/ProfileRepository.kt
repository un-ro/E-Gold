package com.unero.e_gold.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.unero.e_gold.dao.ProfileDao
import com.unero.e_gold.models.Profile

class ProfileRepository(private val profileDao: ProfileDao) {
    var profiles: LiveData<Profile> = profileDao.getProfile()

    var s = profiles.value

    @WorkerThread
    suspend fun insert(profile: Profile){
        profileDao.insert(profile)
    }
}