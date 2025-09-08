package com.example.whatsapp.data.repository.user_profile

import com.example.whatsapp.data.lokal.db.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {
    suspend fun saveProfile(nickName: String, avatarUrl: String?)
     fun getProfile(): Flow<UserProfile?>
}