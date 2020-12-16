package com.unero.e_gold.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transact")
data class Transact(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var weight: Float,
    var price: Int,
    var date: String
){
    // Secondary Constructor
    constructor(date: String, weight: Float, price: Int) : this(id = 0, weight, price, date)
}
