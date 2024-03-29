package com.example.roomapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomapp.LocalTableName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = LocalTableName.TBL_USERS)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int
): Parcelable