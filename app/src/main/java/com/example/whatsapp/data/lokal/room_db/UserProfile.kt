package com.example.whatsapp.data.lokal.room_db

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "user_profile")
data class UserProfile(
    @PrimaryKey val id: Int=1,
    val nickName: String?,
    val avatarUrl: String?
)
