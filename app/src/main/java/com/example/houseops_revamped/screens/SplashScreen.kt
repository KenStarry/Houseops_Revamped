package com.example.houseops_revamped.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.R
import com.example.houseops_revamped.navigation.HOME_ROUTE
import com.example.houseops_revamped.navigation.SPLASH_ROUTE
import com.example.houseops_revamped.ui.theme.Purple40
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navHostController: NavHostController
) {
    LaunchedEffect(key1 = true) {
        delay(3000)
        //  navigate to home screen and pop
        navHostController.navigate(HOME_ROUTE) {
            popUpTo(SPLASH_ROUTE)
            launchSingleTop = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(
                id = if (isSystemInDarkTheme())
                    R.drawable.houseops_dark_final
                else
                    R.drawable.houseops_light_final
            ), contentDescription = "House Ops Logo"
        )

    }
}

@Preview(
    uiMode = UI_MODE_NIGHT_NO,
    showSystemUi = true
)
@Composable
fun SplashLightModePrev() {
    SplashScreen(rememberNavController())
}

@Preview(
    uiMode = UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun SplashDarkModePrev() {
    SplashScreen(rememberNavController())
}





















