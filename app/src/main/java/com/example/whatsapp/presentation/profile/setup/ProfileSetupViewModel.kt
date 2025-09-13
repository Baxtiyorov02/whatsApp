package com.example.whatsapp.presentation.profile.setup

import android.app.Application
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsapp.data.repository.user_profile.UserProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class ProfileSetupViewModel @Inject constructor(
    private val repository: UserProfileRepository,
    private val app: Application
) : ViewModel() {

    var avatarUrl by mutableStateOf<String?>(null)
        private set

    var nickName by mutableStateOf("")
        private set

    fun onAvatarSelected(uri: String?) {
        if (uri == null) return
        viewModelScope.launch {
            val savedPath = saveImageToInternalStorage(uri)
            avatarUrl = savedPath
        }
    }

    fun onNickNameChanged(newName: String) {
        nickName = newName
    }

    fun saveProfile(onSaved: () -> Unit) {
        viewModelScope.launch {
            repository.saveProfile(
                    nickName = nickName,
                    avatarUrl = avatarUrl
            )
            onSaved()
        }
    }

    private suspend fun saveImageToInternalStorage(uriString: String): String? {
        val contentResolver = app.contentResolver
        val uri = Uri.parse(uriString)
        val fileName = "avatar_${System.currentTimeMillis()}.jpg"
        val file = File(app.filesDir, fileName)

        return withContext(Dispatchers.IO) {
            try {
                contentResolver.openInputStream(uri)?.use { input ->
                    FileOutputStream(file).use { output ->
                        input.copyTo(output)
                    }
                }
                file.absolutePath
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}
