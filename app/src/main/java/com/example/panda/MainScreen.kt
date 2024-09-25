package com.example.panda

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
//noinspection UsingMaterialAndMaterial3Libraries
//import androidx.compose.material.BottomNavigation
////noinspection UsingMaterialAndMaterial3Libraries
//import androidx.compose.material.BottomNavigationItem
////noinspection UsingMaterialAndMaterial3Libraries
//import androidx.compose.material.ContentAlpha
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.panda.screens.AreaScreen
import com.example.panda.screens.ProfileScreen
import com.example.panda.screens.ReportScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navItemList = listOf(
        NavItem("Perfil", Icons.Default.Person,0),
        NavItem("Area", Icons.Default.Home,5),
        NavItem("Reporte", Icons.Default.Settings,0),
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Panda",
                    style = androidx.compose.ui.text.TextStyle(
                    fontSize = 20.sp, // Cambia el tamaño de la fuente a 24sp
                    fontWeight = FontWeight.Bold, // Establece el peso de la fuente
                    color = Color.White // Cambia el color del texto
                )) },
                actions = {
                    IconButton(onClick = { /* Acción del icono */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Configuración")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF0C77CE), // Color personalizado
                    titleContentColor = Color.White,   // Color del texto
                    actionIconContentColor = Color.White // Color del icono
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF0C77CE)
            ) {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected =  selectedIndex == index ,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            BadgedBox(badge = {
                                if(navItem.badgeCount>0)
                                    Badge(){
                                        Text(text = navItem.badgeCount.toString())
                                    }
                            }) {
                                Icon(imageVector = navItem.icon, contentDescription = "Icon")
                            }

                        },
                        label = {
                            Text(text = navItem.label)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black, // Color del ícono seleccionado
                            unselectedIconColor = Color.White, // Color del ícono no seleccionado
                            selectedTextColor = Color.Yellow, // Color del texto seleccionado
                            unselectedTextColor = Color.White, // Color del texto no seleccionado
                            indicatorColor = Color(0xFFFBFCFB) // Color de fondo para el ítem seleccionado
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
            selectedIndex
        )
    }
}


@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex : Int) {
    when(selectedIndex){
        0-> ProfileScreen(modifier)
        1-> AreaScreen(modifier)
        2-> ReportScreen(modifier)
    }
}

