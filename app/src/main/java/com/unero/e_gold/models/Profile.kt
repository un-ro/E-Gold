package com.unero.e_gold.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey(autoGenerate = true) var id: Int,
    private val username: String,
    private val email: String,
    private val image: String
): Parcelable
