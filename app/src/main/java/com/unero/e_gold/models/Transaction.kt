package com.unero.e_gold.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "transaction")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val weight: Float,
    val price: Int,
    val date: String
): Parcelable
