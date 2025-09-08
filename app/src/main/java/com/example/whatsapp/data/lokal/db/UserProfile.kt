package com.example.whatsapp.data.lokal.db

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val nickName: String,
    val avatarUrl: String?
)
