package com.example.panda

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.panda.login.LoginScreen
import com.example.panda.login.getMacAddress
import com.example.panda.viewmodels.LoginViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val loginViewModel: LoginViewModel = viewModel()
            MyApp(loginViewModel)
        }
    }
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//
//        if (requestCode == 1) {
//            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permiso concedido, actualiza la dirección MAC
//                val mac = getMacAddress(this)
//                // Aquí puedes actualizar el estado de tu LoginScreen o usar la dirección MAC según sea necesario
//            } else {
//                // Permiso denegado, maneja el caso
//                println("Permiso denegado")
//            }
//        }
//    }
}

@Composable
fun MyApp(loginViewModel: LoginViewModel) {
    val navController = rememberNavController()

    Surface(color = MaterialTheme.colorScheme.background) {
        NavHost(navController, startDestination = "login") {
            composable("login") {
                LoginScreen(onLoginSuccess = {
                    navController.navigate("main")
                }, loginViewModel = loginViewModel)
            }
            composable("main") { MainScreen() }
        }
    }
}


