package com.example.netflixrandom.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Dropdown(
    placeholder: String,
    label: String,
    currentValue: String?,
    items: List<DropdownValue>,
    onChange: (e: Int) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = "$label: ",
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.width(90.dp)
        )
        Box {
            Button(onClick = { isExpanded = true }) {
                Text(text = currentValue ?: placeholder)
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Seta")
            }

            DropdownMenu(expanded = isExpanded, onDismissRequest = { isExpanded = false }) {
                items.map {
                    DropdownMenuItem(text = { Text(text = it.label) }, onClick = {
                        isExpanded = false
                        onChange(it.value)
                    })
                }

            }
        }
    }


}

data class DropdownValue(val label: String, val value: Int)