package com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PersonRemove
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.presentation.components.CustomAlertDialog
import com.kenstarry.houseops_revamped.feature_admin.feature_agents.domain.model.AdminAgentsEvents
import com.kenstarry.houseops_revamped.feature_admin.feature_agents.presentation.viewmodel.AdminAgentsViewModel
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.domain.model.AdminLandlordViewEvents
import com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.presentation.viewmodel.AdminLandlordViewVM
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun AssignDialog(
    apartment: Apartment?,
    primaryColor: Color,
    tertiaryColor: Color,
    onConfirm: (selectedAgent: UsersCollection?) -> Unit,
    onAssignNoAgent: () -> Unit,
    onDismiss: () -> Unit,
) {

    val adminAgentsVM: AdminAgentsViewModel = hiltViewModel()
    val landlordViewVM: AdminLandlordViewVM = hiltViewModel()
    val listState = rememberLazyListState()

    adminAgentsVM.onEvent(
        AdminAgentsEvents.GetAgents(
        response = {}
    ))

    var selectedAgent by remember {
        mutableStateOf<UsersCollection?>(null)
    }

    CustomAlertDialog(
        icon = Icons.Outlined.SupportAgent,
        primaryColor = primaryColor,
        tertiaryColor = tertiaryColor,
        title = "Assign Agent",
        content = {

            Spacer(modifier = Modifier.height(16.dp))

            if (!apartment?.apartmentAgentAssigned.isNullOrBlank()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    HomePillBtns(
                        icon = Icons.Outlined.PersonRemove,
                        title = "Unassign Agent",
                        primaryColor = MaterialTheme.colorScheme.error,
                        tertiaryColor = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
                        onClick = onAssignNoAgent
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            LazyColumn(
                content = {
                    items(adminAgentsVM.agents.value) { agent ->
                        AssignDialogItem(
                            agent = agent,
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor,

                            isSelected = if (apartment?.apartmentAgentAssigned.isNullOrBlank())
                                selectedAgent == agent
                            else
                                landlordViewVM.selectedAgent.value?.userEmail == apartment?.apartmentAgentAssigned,

                            onRadioButtonClicked = {

                                selectedAgent = agent

                                landlordViewVM.onEvent(
                                    AdminLandlordViewEvents.ToggleAgentSelected(
                                        agent
                                    )
                                )
                            }
                        )
                    }
                },
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            )

        },
        onConfirm = {
            onConfirm(landlordViewVM.selectedAgent.value)
        },
        onDismiss = onDismiss
    )

}