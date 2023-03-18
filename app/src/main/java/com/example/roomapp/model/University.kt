package com.example.roomapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.roomapp.LocalTableName

@Entity(tableName = LocalTableName.TBL_UNIVERSITY)
data class University(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val uni_id:Int,
    val uni_name:String,
    val uni_estd:String,
    val uni_isGovApproved:Boolean
)
