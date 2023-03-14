package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.bottom_sheet

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.PriceChange
import androidx.compose.material.icons.outlined.PriceCheck
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.model.AgentApartmentEvents
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.utils.NumberCommaTransformation
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.viewmodel.AgentApartmentViewModel
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.presentation.components.CustomTextField
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun ApartmentHousePrice(
    primaryColor: Color,
    tertiaryColor: Color
) {

    val agentApartmentVM = hiltViewModel<AgentApartmentViewModel>()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Pricing",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .weight(2f),
                contentAlignment = Alignment.Center
            ) {
                CustomTextField(
                    textFieldValue = agentApartmentVM.selectedHousePrice.value,
                    startIcon = Icons.Outlined.PriceCheck,
                    endIcon = null,
                    placeholder = "House Price",
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    visualTransformation = NumberCommaTransformation(),
                    onInput = {
                        //  add commas to price
                        agentApartmentVM.onEvent(AgentApartmentEvents.SelectHousePrice(it))

                    }
                )
            }

            Row(
                modifier = Modifier
                    .weight(1f),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.AlternateEmail,
                    contentDescription = "price icon",
                    tint = primaryColor
                )
                
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Monthly",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
            }

        }
    }
}