package com.example.whatsapp.presentation.auth

sealed class AuthState {
    object Idle: AuthState()
    object CodeSend: AuthState()
    data class Success(val userId: String): AuthState()
    object Loading: AuthState()
    data class Error(val message: String): AuthState()
}