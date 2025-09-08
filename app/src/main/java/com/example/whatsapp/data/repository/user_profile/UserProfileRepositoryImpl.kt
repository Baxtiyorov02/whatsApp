package com.example.whatsapp.data.repository.user_profile

import com.example.whatsapp.data.lokal.db.UserProfile
import com.example.whatsapp.data.lokal.db.UserProfileDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfileRepositoryImpl @Inject constructor(
    private val dao: UserProfileDao
): UserProfileRepository {
    override suspend fun saveProfile(nickName: String, avatarUrl: String?) {
        dao.clearProfile() // eski ma’lumotni o‘chirib tashlash
        dao.insertProfile(UserProfile(nickName=nickName, avatarUrl = avatarUrl))

    }


    override  fun getProfile(): Flow<UserProfile?> =dao.getProfile()
}
