package com.example.whatsapp.unknown

import kotlinx.serialization.Serializable

@Serializable
data class NavUserDetail (
    val userName: String= "",
    val userId: String=""
)