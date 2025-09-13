package com.example.whatsapp.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    popBackStack: () -> Unit,
    navToChat: (String, String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel() // Hilt bilan ViewModel
) {
    val users by viewModel.users.collectAsState(initial = emptyList())
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            ChatsTopBar(
                onSearch = { query -> searchQuery = query },
                onProfileClick = { /* Profile screenga o‘tish */ },
                userImage = viewModel.currentUser?.avatar,
                userName = viewModel.currentUser?.userName ?: "Profile"
            )
        }
    ) { paddingValues ->

        val filteredUsers = if (searchQuery.isEmpty()) {
            users
        } else users.filter { it.userName.contains(searchQuery, ignoreCase = true) }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(filteredUsers) { user ->
                UserItem(user = user) {

                    println("AAAAAAAAAAA:: userId=${user.userId}   userName=${user.userName}")
                    // User bosilganda ChatScreenga o‘tadi
                    navToChat(user.userName, user.userId)

                }
            }
        }
    }
}

// User data class
data class User(
    val userId: String,
    val userName: String,
    val avatar: Int,
    val lastMessage: String
)

// User item composable
@Composable
fun UserItem(user: User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)), // och kulrang
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Avatar
            Icon(
                painter = painterResource(id = user.avatar),
                contentDescription = "User Avatar",
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Name va lastMessage
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = user.userName,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = user.lastMessage,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = Color.Gray,
                        fontStyle = FontStyle.Italic
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


