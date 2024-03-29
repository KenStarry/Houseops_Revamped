package com.kenstarry.houseops_revamped.core.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kenstarry.houseops_revamped.core.presentation.components.LoadingCircle
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants.ADMIN_ROUTE
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants.AGENT_ROUTE
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants.AUTHENTICATION_ROUTE
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants.HOME_ROUTE
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants.LANDLORD_ROUTE
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_authentication.domain.utils.AuthConstants
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.presentation.viewmodel.LoginViewModel
import com.kenstarry.houseops_revamped.navigation.Direction

@Composable
fun LoadingScreen(
    navHostController: NavHostController,
    emailAddress: String?
) {
    val navController = rememberNavController()
    val direction = Direction(navHostController)

    val loginVM: LoginViewModel = hiltViewModel()
    val coreVM: CoreViewModel = hiltViewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary),
        contentAlignment = Alignment.Center
    ) {

        LoadingCircle(
            primaryColor = MaterialTheme.colorScheme.primary,
            tertiaryColor = MaterialTheme.colorScheme.tertiary,
            modifier = Modifier
                .wrapContentSize()
        )

    }
}




















