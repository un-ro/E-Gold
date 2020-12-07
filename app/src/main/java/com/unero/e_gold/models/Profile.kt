package com.unero.e_gold.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Profile(
    private val username: String,
    private val email: String,
    private val image: String
): Parcelable
