package com.example.houseops_revamped.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginScreen(
    navHostController: NavHostController
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {
        
        Text(text = "Login Screen")

    }
}

//  ----------------- previews --------------------
//  dark mode preview
@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun LoginScreenPrev() {
    LoginScreen(navHostController = rememberNavController())
}





































