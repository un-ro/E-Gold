package com.unero.e_gold.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "account")
data class Account(
    @PrimaryKey
    var email: String,
    var username: String,
    var image: String = ""
): Parcelable