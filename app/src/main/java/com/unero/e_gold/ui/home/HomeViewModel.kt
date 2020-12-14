package com.unero.e_gold.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.unero.e_gold.data.AppDatabase
import com.unero.e_gold.data.repo.ProfileRepo
import com.unero.e_gold.models.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val profiles: LiveData<Profile>
    private val repo: ProfileRepo

    init {
        val profileDao = AppDatabase.getDatabase(application).profileDao()
        repo = ProfileRepo(profileDao)
        profiles = repo.readDataProfile

        // INIT ISI DB
        viewModelScope.launch(Dispatchers.IO) {
            repo.addProfile(Profile(1, "Unero", "ramadhanbhg@gmail.com", ""))
        }
    }

    fun getProfile(): LiveData<Profile>{
        return profiles
    }

    fun addProfile(profile: Profile){
        viewModelScope.launch(Dispatchers.IO){
            repo.addProfile(profile)
        }
    }

    fun updateProfile(profile: Profile){
        viewModelScope.launch(Dispatchers.IO){
            repo.updateProfile(profile)
        }
    }
}