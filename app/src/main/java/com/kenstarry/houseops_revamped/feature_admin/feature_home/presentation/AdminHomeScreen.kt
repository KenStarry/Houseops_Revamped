package com.kenstarry.houseops_revamped.feature_admin.feature_home.presentation

import android.widget.Toast
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.houseops_revamped.core.domain.model.events.BottomSheetEvents
import com.kenstarry.houseops_revamped.core.presentation.components.EmailVerificationMessage
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_admin.feature_home.presentation.components.AdminHomeContent
import com.kenstarry.houseops_revamped.feature_admin.feature_home.presentation.utils.AdminConstants
import com.kenstarry.houseops_revamped.navigation.Direction
import com.kenstarry.houseops_revamped.navigation.screens.AdminScreens
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdminHomeScreen(
    navHostController: NavHostController,
    coreViewModel: CoreViewModel,
    modalSheetState: ModalBottomSheetState,
    scope: CoroutineScope
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val direction = Direction(navHostController)

    val currentUser = coreVM.currentUser()
    val context = LocalContext.current

    val primaryColor = Color(
        coreVM.primaryAccentFlow.collectAsState(
            initial = Constants.accentColors[0].darkColor
        ).value ?: Constants.accentColors[0].darkColor
    )

    val tertiaryColor = Color(
        coreVM.tertiaryAccentFlow.collectAsState(
            initial = Constants.accentColors[0].lightColor
        ).value ?: Constants.accentColors[0].lightColor
    )

    //  show main UI
    AdminHomeContent(
        onCardClicked = {
            //  navigate to landlord view
            direction.navigateToRoute(
                AdminScreens.LandlordView.passLandlordEmail(it.userEmail ?: "no email"),
                null
            )
        },
        onActionsClicked = { landlord ->
            coreViewModel.onBottomSheetEvent(
                BottomSheetEvents.OpenBottomSheet(
                    state = modalSheetState,
                    scope = scope,
                    bottomSheetType = AdminConstants.BOTTOM_SHEET_LANDLORD_ACTIONS,
                    bottomSheetData = landlord
                )
            )
        }
    )

}