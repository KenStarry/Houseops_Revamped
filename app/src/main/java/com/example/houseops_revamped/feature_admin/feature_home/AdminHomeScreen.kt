package com.example.houseops_revamped.feature_admin.feature_home

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.events.CoreEvents
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel

@Composable
fun AdminHomeScreen(
    navHostController: NavHostController
) {
    
    val coreVM: CoreViewModel = hiltViewModel()
    val currentUser = coreVM.currentUser()
    
    if (currentUser?.isEmailVerified == true) {

        //  show main UI
        
    } else {
        
        //  show error message
        Button(onClick = {
            //  send verification email
            coreVM.onEvent(CoreEvents.SendVerificationEmail(
                response = {
                    when (it) {
                        is Response.Success -> {}
                        is Response.Failure -> {}
                    }
                }
            ))
        }) {
            Text(text = "Send Verification email")
        }
        
    }

}