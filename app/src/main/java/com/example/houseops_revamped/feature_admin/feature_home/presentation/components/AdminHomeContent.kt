package com.example.houseops_revamped.feature_admin.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminHomeContent() {

    val coreVM: CoreViewModel = hiltViewModel()
    val context = LocalContext.current

    val currentUser = coreVM.currentUser()
    val userDetails: UsersCollection? = coreVM.getUserDetails(
        currentUser?.email ?: "no email"
    )

    Scaffold(
        topBar = {
            AdminHomeAppBar(
                context = context,
                userName = userDetails?.userName ?: " "
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp)
            ) {

                //  show all landlords registered by the app


            }

        }

    }
}