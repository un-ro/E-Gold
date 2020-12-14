package com.unero.e_gold.data.repo

import androidx.lifecycle.LiveData
import com.unero.e_gold.data.dao.ProfileDao
import com.unero.e_gold.models.Profile

class ProfileRepo(private val profileDao: ProfileDao) {

    val readDataProfile: LiveData<Profile>
        get() = profileDao.getProfiles()

    suspend fun addProfile(profile: Profile){
        profileDao.addProfile(profile)
    }

    fun updateProfile(profile: Profile) {
        profileDao.updateProfile(profile)
    }
}