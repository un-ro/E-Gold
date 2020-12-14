package com.unero.e_gold.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.unero.e_gold.data.AppDatabase
import com.unero.e_gold.data.repo.ProfileRepo
import com.unero.e_gold.models.Profile
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class ProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val repo: ProfileRepo
    private val profileMLD: MutableLiveData<Profile> = MutableLiveData()
    private val profiles: LiveData<Profile>

    init {
        val profileDao = AppDatabase.getDatabase(application).profileDao()
        repo = ProfileRepo(profileDao)
        profiles = repo.readDataProfile
    }

    fun getProfile(): LiveData<Profile>{
        return profiles
    }

    fun btnUpdate(username: String, email:String){
        val profile: Profile? = profiles.value
        if (profile != null){
            profile.username = username
            profile.email = email
            repo.updateProfile(profile)
            profileMLD.value = profile
        }
    }

    fun profileLiveData(): LiveData<Profile>{
        return profileMLD
    }
}