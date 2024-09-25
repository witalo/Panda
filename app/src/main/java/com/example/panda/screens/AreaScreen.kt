package com.example.panda.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun AreaScreen(modifier: Modifier = Modifier) {
    val items = listOf(
        Item("Piso 1", "Descripción del ítem 1"),
        Item("Piso 2", "Descripción del ítem 2"),
        Item("Piso 3", "Descripción del ítem 3"),
        Item("Piso 4", "Descripción del ítem 4"),
        Item("Piso 5", "Descripción del ítem 5"),
        Item("Piso 6", "Descripción del ítem 5"),
        Item("Piso 7", "Descripción del ítem 5"),
        Item("Piso 8", "Descripción del ítem 5"),
        Item("Piso 9", "Descripción del ítem 5"),
        Item("Piso 10", "Descripción del ítem 5"),
    )
    LazyColumn(modifier = modifier.padding(16.dp)
//        modifier = Modifier.fillMaxSize(),
//        contentPadding = PaddingValues(bottom = 120.dp, top = 120.dp)
    ) {
        items(items) { item ->
            CardItem(item = item)
        }
    }
}
@Composable
fun CardItem(item: Item) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = item.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = item.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
data class Item(val title: String, val description: String)