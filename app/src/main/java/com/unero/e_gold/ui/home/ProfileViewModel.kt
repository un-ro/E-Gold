package com.unero.e_gold.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.unero.e_gold.models.Profile
import com.unero.e_gold.repository.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ProfileRepository): ViewModel() {

    var profile: LiveData<Profile> = repository.profiles

    fun insert(profile: Profile) = viewModelScope.launch {
        repository.insert(profile)
    }

}

class ProfileViewModelFactory(private val repository: ProfileRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)){
            return ProfileViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}