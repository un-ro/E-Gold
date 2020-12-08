package com.unero.e_gold

import android.app.Application
import com.unero.e_gold.database.ProfileDatabase
import com.unero.e_gold.repository.ProfileRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.SupervisorJob


class ProfileApplication: Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    @InternalCoroutinesApi
    val database by lazy { ProfileDatabase.getDatabase(this, applicationScope) }
    @InternalCoroutinesApi
    val repository by lazy { ProfileRepository(database.profileDao()) }
}