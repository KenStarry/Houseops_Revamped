package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.bottom_sheet

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.House
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.HouseCategoriesAlertDialog
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.viewmodel.AgentApartmentViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun ApartmentHouseCategory(
    house: HouseModel?,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val agentApartmentVM = hiltViewModel<AgentApartmentViewModel>()
    val coreVM = hiltViewModel<CoreViewModel>()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center
    ) {

        AnimatedVisibility(visible = house == null) {
            HomePillBtns(
                icon = Icons.Outlined.House,
                title = agentApartmentVM.selectedHouseCategory.value,
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor,
                onClick = {
                    //  open alert dialog for house category
                    coreVM.onEvent(
                        CoreEvents.ToggleAlertDialog(
                            dialogType = Constants.APARTMENT_HOUSE_CATEGORIES_ALERT_DIALOG,
                            isDialogVisible = true
                        )
                    )
                }
            )
        }

        AnimatedVisibility(visible = house != null) {
            Text(
                text = house?.houseCategory ?: "",
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )
        }

    }

}