package com.dmovel.pc05_06_07_dmovel.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dmovel.pc05_06_07_dmovel.MainViewModel
import com.dmovel.pc05_06_07_dmovel.ui.theme.Pc05_06_07_dmovelTheme

@Composable
fun HomeScreen() {
    val viewModel = viewModel<MainViewModel>()
    var book by remember { mutableStateOf("") }

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text(
            text = "Open Library Search API",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 60.dp)
        )
        OutlinedTextField(
            value = book,
            onValueChange = { book = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Book name") }
        )

        Spacer(Modifier.height(10.dp))

        Button(
            onClick = { viewModel.getData(book) },
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text(text = "Get book data")
        }

        if (viewModel.loading) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        } else {
            Text(
                fontSize = 18.sp,
                text = viewModel.screenLabel,
                modifier = Modifier.padding(top = 42.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Pc05_06_07_dmovelTheme {
        HomeScreen()
    }
}