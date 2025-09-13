package com.example.whatsapp.presentation.home

import androidx.lifecycle.ViewModel
import com.example.whatsapp.R
import com.example.whatsapp.data.repository.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel()
{


    fun getAvatarUrl(): Flow<String?> {
        return homeRepository.getAvatarUrl()
    }

    private val _users = MutableStateFlow<List<User>>(
        listOf(
            User("1", "Ali", R.drawable.ic_launcher_background, "Salom, qalesan?"),
            User("2", "Vali", R.drawable.ic_launcher_background, "Bugun uchrashamizmi?"),
            User("3", "Gulbahor", R.drawable.ic_launcher_background, "Ok"),
            User("4", "Sardor", R.drawable.ic_launcher_background, "Yaxshi!"),
            User("5", "Mariya", R.drawable.ic_launcher_background, "Ha, boramiz")
        )
    )
    val users: StateFlow<List<User>> = _users

    // Hozirgi user (top bar uchun)
    val currentUser: User? = User("0", "Men", R.drawable.ic_launcher_background, "")

}