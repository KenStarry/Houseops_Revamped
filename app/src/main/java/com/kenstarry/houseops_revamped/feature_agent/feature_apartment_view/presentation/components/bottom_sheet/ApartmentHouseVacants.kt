package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.components.bottom_sheet

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Minimize
import androidx.compose.material.icons.outlined.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.model.AgentApartmentEvents
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.presentation.viewmodel.AgentApartmentViewModel

@Composable
fun ApartmentHouseVacants(
    primaryColor: Color,
    tertiaryColor: Color
) {

    val agentApartmentVM = hiltViewModel<AgentApartmentViewModel>()
    val context = LocalContext.current

    var counter by remember {
        mutableStateOf(agentApartmentVM.selectedVacantUnits.value)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(
            text = "Vacants",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        //  add number of vacant units remaining
        Row(
            modifier = Modifier
                .wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //  minus icon
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(tertiaryColor)
                    .clickable {
                        if (counter >= 1) {

                            counter -= 1

                            agentApartmentVM.onEvent(
                                AgentApartmentEvents.SelectVacantUnits(
                                    counter
                                )
                            )

                            Toast
                                .makeText(
                                    context,
                                    agentApartmentVM.selectedVacantUnits.value.toString(),
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Remove,
                    contentDescription = "minus icon",
                    tint = primaryColor
                )
            }

            //  counter text
            Text(
                text = counter.toString(),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

            //  add icon
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(tertiaryColor)
                    .clickable {
                        counter += 1

                        agentApartmentVM.onEvent(
                            AgentApartmentEvents.SelectVacantUnits(
                                counter
                            )
                        )

                        Toast
                            .makeText(
                                context,
                                agentApartmentVM.selectedVacantUnits.value.toString(),
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = "add icon",
                    tint = primaryColor
                )
            }
        }

    }

}