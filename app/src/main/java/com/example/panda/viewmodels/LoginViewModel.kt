package com.example.panda.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import com.example.panda.apollo.apolloClient
import kotlinx.coroutines.launch
import com.example.panda.LoginPinMutation

class LoginViewModel : ViewModel() {
    var loginSuccess by mutableStateOf(false)
        private set

    var macAddress by mutableStateOf("") // Propiedad para almacenar la dirección MAC
        private set

    fun updateMacAddress(mac: String) {
        macAddress = mac // Método para actualizar la dirección MAC
    }

    fun login(mac: String, password: String) {
        Log.d("PANDA-APP", "Mac: $mac Password:$password")
        viewModelScope.launch {
            try {
                Log.d("PANDA-APP", "mac $mac; password $password")
                val response = apolloClient.mutation(LoginPinMutation(mac, password)).execute()

                if (response.hasErrors()) {
                    // Manejar errores de GraphQL
                    Log.d("PANDA-APP", "Error en la respuesta: ${response.errors}")
                } else {
                    // Login exitoso
                    loginSuccess = true
                }
            } catch (e: ApolloException) {
                // Manejar error de red o de Apollo
                Log.d("PANDA-APP", "Error de Apollo: ${e.message}")
            }
        }
    }
}