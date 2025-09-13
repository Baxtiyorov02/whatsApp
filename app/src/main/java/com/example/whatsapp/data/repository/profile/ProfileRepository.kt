package com.example.whatsapp.data.repository.profile

import com.example.whatsapp.data.lokal.room_db.UserProfile
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
     fun getProfile(): Flow<UserProfile?>
    suspend fun updateNickName(nickName: String)
    suspend fun updateAvatarUrl(avatarUrl: String?)

}