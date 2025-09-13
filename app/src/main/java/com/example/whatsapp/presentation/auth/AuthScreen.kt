package com.example.whatsapp.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.whatsapp.data.lokal.auth.AuthPrefs


@Composable
fun AuthScreen(
    viewModel: AuthViewModel = hiltViewModel(),
    onAuthSuccess: () -> Unit
) {
    val contect= LocalContext.current
    val authPrefs= remember { AuthPrefs(contect) }
    val state = viewModel.uiState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
         when (state) {
                is AuthState.Idle -> {
                    Text(
                        "Telefon raqamingizni kiriting",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(Modifier.height(16.dp))

                    OutlinedTextField(
                        value = viewModel.phoneNumber,
                        onValueChange = { viewModel.onPhoneNumberChanged(it) },
                        label = { Text("+998 XX XXX XX XX") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(16.dp))

                    Button(
                        onClick = { viewModel.sendCode() },
                        enabled = viewModel.phoneNumber.length == 9,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Kodni yuborish")
                    }
                }

                AuthState.CodeSend -> {
                    Text("SMS kodni kiriting", style = MaterialTheme.typography.titleLarge)

                    Spacer(Modifier.height(16.dp))
                    OutlinedTextField(
                        value = viewModel.otpCode,
                        onValueChange = { viewModel.onOptChanged(it) }, // ✅ to‘g‘ri
                        label = { Text("SMS kod") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )


                    Spacer(Modifier.height(16.dp))

                    Button(
                        onClick = { viewModel.verifyCode(onAuthSuccess)
                                  authPrefs.setLoggedIn(true)
                                  },
                        enabled = viewModel.otpCode.length == 6,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Tasdiqlash")
                    }
                }

                is AuthState.Error -> {
                    Text("Xatolik: ${state.message}", color = Color.Red)
                }

                AuthState.Loading -> CircularProgressIndicator()

                is AuthState.Success -> {

                    // Bu joyda avtomatik onAuthSuccess chaqiriladi
                }
            }



    }
}
