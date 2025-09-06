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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showSystemUi = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen(
        onSendCode = { println("Send code to $it") },
        onVerifyCode = { println("Verify code $it") }
    )

}

@Composable
fun AuthScreen(
    onSendCode: (String) -> Unit,
    onVerifyCode: (String) -> Unit
) {
    var phoneNumber by remember { mutableStateOf("") }
    var otpCode by remember { mutableStateOf("") }
    var isCodeSent by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = if (!isCodeSent) "Telefon raqamingizni kiriting"
            else "SMS kodni kiriting",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Blue

        )

        Spacer(modifier = Modifier.height(16.dp))

        if (!isCodeSent) {
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = {
                    if (it.length<=9 && it.all { ch -> ch.isDigit() }){
                        phoneNumber = it
                    }
                    },
                label = { Text("+998 XX XXX XX XX") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,
                    contentColor = Color.White

                ),
                onClick = {
                    if (phoneNumber.isNotBlank()) {
                        onSendCode(phoneNumber)
                        isCodeSent = true
                    }
                },
                enabled = phoneNumber.length == 9,

                ) {
                Text("Kodni yuborish")
            }
        } else {
            OutlinedTextField(
                value = otpCode,
                onValueChange = {
                    if (it.length <= 6 && it.all { ch -> ch.isDigit() }) {
                        otpCode = it
                    }
                },
                label = { Text("SMS kod") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,
                    contentColor = Color.White
                ),
                onClick = {
                    if (otpCode.isNotBlank()) {
                        onVerifyCode(otpCode)
                    }
                },
                enabled = otpCode.length==6,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Tasdiqlash")
            }
        }
    }
}
