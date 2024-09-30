package com.example.panda.login

import android.app.Activity
import android.content.Context
import android.net.wifi.WifiManager
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.panda.MainScreen
import com.example.panda.R
import com.example.panda.viewmodels.LoginViewModel
import android.provider.Settings
import java.net.NetworkInterface
import java.util.Collections
import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit, loginViewModel: LoginViewModel = viewModel()) {



    val context: Context = LocalContext.current
    val activity = context as Activity // Necesitamos la actividad para solicitar permisos
    var mac by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var hasPermission by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        // Verificar y solicitar permisos
        Log.d("PANDA-APP", "Manifest.permission.ACCESS_FINE_LOCATION: ${Manifest.permission.ACCESS_FINE_LOCATION}")
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            Log.d("PANDA-APP", "LaunchedEffect: $mac")
        } else {
            hasPermission = true
            mac = getMacAddress(context)
            loginViewModel.updateMacAddress(mac) // Actualiza la dirección MAC en el ViewModel
        }
    }

    if (hasPermission) {
        mac = getMacAddress(context)
        Log.d("PANDA-APP", "Dirección MAC obtenida: $mac")


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
                onClick = {
                    // Llamar al método login del ViewModel pasando la MAC y el PIN
                    loginViewModel.login(mac, password)
                },
                colors = ButtonDefaults.buttonColors(
//                containerColor = Color.Blue, // Color del fondo del botón
                    contentColor = Color.White // Color del texto y el ícono
                ),
                shape = RoundedCornerShape(12.dp), // Esquinas redondeadas
                modifier = Modifier.padding(8.dp) // Espaciado alrededor del botón
            ) {
                Row {
                    Icon(
//                    painter = painterResource(id = R.drawable.ic_launcher_foreground), // Reemplaza con tu ícono
                        imageVector = Icons.Default.AccountCircle, // Usando un ícono predeterminado
                        contentDescription = "Icono de ingreso",
                        tint = Color.White, // Color del ícono
//                    modifier = Modifier.size(24.dp)
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

    } else {
        Log.d("PANDA-APP", "Permiso de ubicación no concedido")
    }

}


fun getMacAddress(context: Context): String {
    // Intenta obtener la dirección MAC desde WifiManager
    val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
    val wifiInfo = wifiManager.connectionInfo
    val macAddress = wifiInfo.macAddress

    Log.d("PANDA-APP", "Dirección MAC desde WifiManager: $macAddress")

    // Si obtenemos una dirección MAC válida, la devolvemos
    if (macAddress != null && macAddress != "02:00:00:00:00:00") {
        return macAddress
    }

    // Si WifiManager no funciona, intentamos con NetworkInterface
    return try {
        val interfaces: List<NetworkInterface> = Collections.list(NetworkInterface.getNetworkInterfaces())
        for (networkInterface in interfaces) {
            if (networkInterface.name.equals("wlan0", ignoreCase = true)) {
                val macBytes = networkInterface.hardwareAddress ?: continue
                val mac = macBytes.joinToString(":") { String.format("%02X", it) }
                Log.d("PANDA-APP", "Dirección MAC desde NetworkInterface: $mac")
                return mac
            }
        }
        // Si aún no tenemos, intentar desde Settings.Secure
        val bluetoothMac = Settings.Secure.getString(context.contentResolver, "bluetooth_address")
        if (bluetoothMac.isNotEmpty()) bluetoothMac else "Dirección MAC no disponible"
    } catch (e: Exception) {
        Log.d("PANDA-APP", "Error al obtener la dirección MAC: ${e.message}")
        "Dirección MAC no disponible"
    }
}
