package com.unero.e_gold.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beust.klaxon.Json
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "transact")
data class Transact(
    @PrimaryKey(autoGenerate = true)
    @Json(ignored = true)
    var id: Int,
    @Json(name = "weight")
    var weight: Float,
    @Json(name = "price")
    var price: Int,
    @Json(name = "date")
    var date: String
){
    constructor(date: String, weight: Float, price: Int) : this(id = 0, weight, price, date)
}
