package com.example.whatsapp.presentation.profile

import android.app.Application
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whatsapp.data.repository.profile.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: ProfileRepository,
    private val app: Application // kerak bo‘ladi
) : ViewModel() {

    val profile = repository.getProfile().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        null
    )

    fun updateAvatarUrl(uriString: String) {
        viewModelScope.launch {
            val savedPath = saveImageToInternalStorage(uriString)
            repository.updateAvatarUrl(savedPath)
        }
    }

    private suspend fun saveImageToInternalStorage(uriString: String): String? {
        val contentResolver = app.contentResolver
        val uri = uriString.toUri()

        // Fayl nomi (masalan: avatar_12345.jpg)
        val fileName = "avatar_${System.currentTimeMillis()}.jpg"
        val file = File(app.filesDir, fileName)

        return withContext(Dispatchers.IO) {
            try {
                contentResolver.openInputStream(uri)?.use { input ->
                    FileOutputStream(file).use { output ->
                        input.copyTo(output)
                    }
                }
                file.absolutePath // Shu pathni DB’da saqlaymiz
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    fun updateNickName(newName: String) {
        viewModelScope.launch {
            repository.updateNickName(newName)
        }
    }
}
