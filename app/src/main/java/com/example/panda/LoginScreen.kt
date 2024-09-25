package com.example.panda

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen() {
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id= R.drawable.logo), contentDescription = "Login Panda",
            modifier = Modifier.size(150.dp))
        Text(text = "BIENVENIDO", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = "Iniciar Sesión", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { newValue ->
                if (newValue.length <= 4 && newValue.all { it.isDigit() }) {
                    password = newValue
                }
            },
            label = { Text(text = "Ingresar PIN") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray,
                disabledTextColor = Color.LightGray,
                errorTextColor = Color.Red,
                focusedContainerColor = Color.Transparent,  // Puedes personalizar esto también
                unfocusedContainerColor = Color.Transparent,  // Personaliza según lo que necesites
                disabledContainerColor = Color.LightGray,  // También se puede controlar
                errorContainerColor = Color.Red
            ),
        )
//        OutlinedTextField(value = "", onValueChange = {}, label = {Text(text = "Contraseña")})
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
//                containerColor = Color.Blue, // Color del fondo del botón
                contentColor = Color.White // Color del texto y el ícono
            ),
            shape = RoundedCornerShape(12.dp), // Esquinas redondeadas
            modifier = Modifier.padding(8.dp) // Espaciado alrededor del botón
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground), // Reemplaza con tu ícono
                    contentDescription = "Icono de ingreso",
                    tint = Color.White, // Color del ícono
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = " Ingresar", // Añade un espacio antes del texto
                    modifier = Modifier.padding(start = 8.dp) // Espaciado entre el ícono y el texto
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = {}) {
            Text(text = "¿Recuperar contraseña?")
        }

    }
}