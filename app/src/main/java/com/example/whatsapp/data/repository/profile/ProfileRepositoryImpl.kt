package com.example.whatsapp.data.repository.profile

import com.example.whatsapp.data.lokal.room_db.UserProfile
import com.example.whatsapp.data.lokal.room_db.UserProfileDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dao: UserProfileDao
): ProfileRepository  {
    override  fun getProfile(): Flow<UserProfile?> {
        return dao.getProfile()
    }

    override suspend fun updateNickName(nickName: String) {
       dao.updateNickName(nickName)
    }

    override suspend fun updateAvatarUrl(avatarUrl: String?) {
        dao.updateAvatarUrl(avatarUrl)
    }
}