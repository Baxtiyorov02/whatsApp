package com.example.whatsapp.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.whatsapp.data.local.AuthPrefs
import kotlinx.coroutines.delay
@Composable
fun SplashScreen(
    isLoggedInApp: () -> Unit,
    isNotLoggedInApp: () -> Unit) {
    val context= LocalContext.current
    val authPrefs = remember { AuthPrefs(context) }

    // Effekt orqali navController bilan yo‘naltirish
    LaunchedEffect(Unit) {
        delay(1200) // splash ekranni biroz ko‘rsatib turish (1.2s)
        if (authPrefs.isLoggedIn()) {
          isLoggedInApp()
        } else {
           isNotLoggedInApp()
        }
    }

    // UI qismini yozamiz
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2196F3)), // masalan ko‘k fon
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "MyApp",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
