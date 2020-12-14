package com.unero.e_gold.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.unero.e_gold.data.AppDatabase
import com.unero.e_gold.data.ProfileRepo
import com.unero.e_gold.models.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val profiles: LiveData<List<Profile>>
    private val repository: ProfileRepo

    init {
        val profileDao = AppDatabase.getDatabase(application).profileDao()
        repository = ProfileRepo(profileDao)
        profiles = repository.readDataProfile
    }

    fun addProfile(profile: Profile){
        viewModelScope.launch(Dispatchers.IO){
            repository.addProfile(profile)
        }
    }
}