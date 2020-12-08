package com.unero.e_gold.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "profile")
data class Profile(
        @ColumnInfo(name = "username") private var username: String,
        @ColumnInfo(name = "email") private var email: String,
        @ColumnInfo(name = "image") private val image: String
): Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Int = 0

    var susername: String = username
    var em: String = email
    var im: String = image
}
