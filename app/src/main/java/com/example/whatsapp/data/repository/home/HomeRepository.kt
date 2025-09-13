package com.example.whatsapp.data.repository.home

import kotlinx.coroutines.flow.Flow

interface HomeRepository {

      fun getAvatarUrl(): Flow<String?>
}