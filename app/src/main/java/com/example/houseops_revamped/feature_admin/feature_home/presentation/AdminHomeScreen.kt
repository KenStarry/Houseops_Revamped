package com.example.houseops_revamped.feature_admin.feature_home

import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.events.CoreEvents
import com.example.houseops_revamped.core.presentation.components.EmailVerificationMessage
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel

@Composable
fun AdminHomeScreen(
    navHostController: NavHostController
) {
    
    val coreVM: CoreViewModel = hiltViewModel()
    val currentUser = coreVM.currentUser()
    val context = LocalContext.current
    
    if (currentUser?.isEmailVerified == true) {

        //  show main UI
        
    } else {
        
        //  show error message
        EmailVerificationMessage(
            coreVM = coreVM,
            onSuccess = {},
            onFailure = {}
        )
        
    }

}