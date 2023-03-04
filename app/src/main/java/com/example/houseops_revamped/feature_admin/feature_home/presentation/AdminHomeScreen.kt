package com.example.houseops_revamped.feature_admin.feature_home

import android.widget.Toast
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.events.BottomSheetEvents
import com.example.houseops_revamped.core.domain.model.events.CoreEvents
import com.example.houseops_revamped.core.presentation.components.BottomSheet
import com.example.houseops_revamped.core.presentation.components.EmailVerificationMessage
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_admin.feature_home.presentation.components.AdminHomeContent
import com.example.houseops_revamped.feature_admin.feature_home.presentation.components.bottomsheets.AdminVerificationSheet
import com.example.houseops_revamped.feature_admin.feature_home.presentation.utils.AdminConstants
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdminHomeScreen(
    navHostController: NavHostController,
    modalSheetState: ModalBottomSheetState,
    scope: CoroutineScope
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val currentUser = coreVM.currentUser()
    val context = LocalContext.current

    if (currentUser?.isEmailVerified == true) {

        //  show main UI
        AdminHomeContent()

    } else {

        //  show error message
        EmailVerificationMessage(
            coreVM = coreVM,
            onSuccess = {
                //  open
                coreVM.onBottomSheetEvent(
                    BottomSheetEvents.OpenBottomSheet(
                        state = modalSheetState,
                        scope = scope,
                        bottomSheetType = AdminConstants.BOTTOM_SHEET_VERIFICATION_SUCCESS,
                        bottomSheetData = null
                    )
                )
            },
            onFailure = {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
                coreVM.onBottomSheetEvent(
                    BottomSheetEvents.OpenBottomSheet(
                        state = modalSheetState,
                        scope = scope,
                        bottomSheetType = AdminConstants.BOTTOM_SHEET_VERIFICATION_SUCCESS,
                        bottomSheetData = null
                    )
                )
            }
        )
    }

}