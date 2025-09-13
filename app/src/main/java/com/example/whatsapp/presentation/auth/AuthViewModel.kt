package com.example.whatsapp.presentation.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.whatsapp.data.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

   private  val _uiState=mutableStateOf<AuthState>(AuthState.Idle)
    val uiState:State<AuthState> = _uiState
    var phoneNumber by mutableStateOf("")
        private set
    var otpCode by mutableStateOf("")
        private set



    fun onPhoneNumberChanged(newPhoneNumber: String) {
        if (newPhoneNumber.length <= 9 && newPhoneNumber.all { it.isDigit() }) {
            phoneNumber = newPhoneNumber
        }
    }

    fun onOptChanged(newCode: String)
    {
        if (newCode.length<=6 && newCode.all { it.isDigit() }){
            otpCode=newCode
        }

    }
    fun sendCode(){
        if (phoneNumber.isNotBlank()){
            _uiState.value== AuthState.Loading
            // bu yerda real SMS yuborish logikasi boladi

            _uiState.value=AuthState.CodeSend

        }
    }
    fun verifyCode(onSuccess: () -> Unit){
        if (otpCode.length==6){
            // Bu yerda real kod tekshirish logikasi bo‘ladi
           _uiState.value= AuthState.Success("user_id")
            onSuccess()
        }
    }

}