package com.example.panda

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id= R.drawable.logo), contentDescription = "Login Panda",
            modifier = Modifier.size(150.dp))
        Text(text = "Bienvenido a Panda", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Iniciar Sesión", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Correo")
        })
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = "", onValueChange = {}, label = {
            Text(text = "Contraseña")
        })
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {}) {
            Text(text = "Ingresar")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = {}) {
            Text(text = "¿Recuperar contraseña?")
        }

    }
}