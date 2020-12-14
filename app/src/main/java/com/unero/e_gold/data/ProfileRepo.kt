package com.unero.e_gold.data

import androidx.lifecycle.LiveData
import com.unero.e_gold.models.Profile

class ProfileRepo(private val profileDao: ProfileDao) {

    val readDataProfile: LiveData<List<Profile>> = profileDao.getProfiles()

    suspend fun addProfile(profile: Profile){
        profileDao.addProfile(profile)
    }
}