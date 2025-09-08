package com.example.whatsapp.presentation.profile.setup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsapp.data.repository.user_profile.UserProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileSetupViewModel @Inject constructor(
    private val repository: UserProfileRepository
) : ViewModel() {

    var nickName by mutableStateOf("")
        private set
    var avatarUrl by mutableStateOf<String?>(null)


    fun onNickNameChanged(newNickName: String) {
        nickName = newNickName
    }

    fun onAvatarSelected(newAvatarUri: String?) {
        avatarUrl = newAvatarUri
    }


    fun saveProfile(onSuccess: () -> Unit) {

        if (nickName.isNotBlank()) {
            viewModelScope.launch {
                repository.saveProfile(
                    nickName = nickName,
                    avatarUrl = avatarUrl
                )
                onSuccess()
            }
        }
    }
}