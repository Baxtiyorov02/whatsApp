package com.example.whatsapp.data.repository.home

import com.example.whatsapp.data.lokal.room_db.UserProfileDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dao: UserProfileDao

): HomeRepository {

    override  fun getAvatarUrl(): Flow<String?> {

        println("HomeRepositoryImpl::getAvatarUrl  ${dao.getAvatarUrl()}")
        return dao.getAvatarUrl()
    }
}