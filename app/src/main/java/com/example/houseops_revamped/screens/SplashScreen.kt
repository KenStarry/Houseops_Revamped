package com.example.houseops_revamped.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.R
import com.example.houseops_revamped.utilities.Constants.AUTHENTICATION_ROUTE
import com.example.houseops_revamped.utilities.Constants.HOME_ROUTE
import com.example.houseops_revamped.utilities.Constants.SPLASH_ROUTE
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navHostController: NavHostController
) {
    //  check if the user is logged in or not
    val auth = Firebase.auth
    val currentUser by remember {
        mutableStateOf(auth.currentUser)
    }

    LaunchedEffect(key1 = true) {

        delay(3000)

        //  the user exists
        if (currentUser != null) {

            //  query the user details and send them to MainScreen

            //  navigate to home screen and pop
            navHostController.navigate(HOME_ROUTE) {
                popUpTo(SPLASH_ROUTE)
                launchSingleTop = true
            }
        } else {

            //  navigate to login screen and pop
            navHostController.navigate(AUTHENTICATION_ROUTE) {
                popUpTo(SPLASH_ROUTE)
                launchSingleTop = true
            }
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





















