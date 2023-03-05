package com.example.houseops_revamped.feature_admin.feature_landlord_view.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.presentation.components.CustomAlertDialog
import com.example.houseops_revamped.feature_admin.feature_agents.domain.model.AdminAgentsEvents
import com.example.houseops_revamped.feature_admin.feature_agents.presentation.viewmodel.AdminAgentsViewModel
import com.example.houseops_revamped.feature_admin.feature_landlord_view.domain.model.AdminLandlordViewEvents
import com.example.houseops_revamped.feature_admin.feature_landlord_view.presentation.viewmodel.AdminLandlordViewVM
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns
import com.google.firebase.firestore.auth.User

@Composable
fun AssignDialog(
    primaryColor: Color,
    tertiaryColor: Color,
    onConfirm: (selectedAgent: UsersCollection?) -> Unit,
    onDismiss: () -> Unit,
) {

    val adminAgentsVM: AdminAgentsViewModel = hiltViewModel()
    val landlordViewVM: AdminLandlordViewVM = hiltViewModel()
    val listState = rememberLazyListState()

    adminAgentsVM.onEvent(AdminAgentsEvents.GetAgents(
        response = {}
    ))

    CustomAlertDialog(
        icon = Icons.Outlined.SupportAgent,
        primaryColor = primaryColor,
        tertiaryColor = tertiaryColor,
        title = "Assign Agent",
        content = {

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                content = {
                    items(adminAgentsVM.agents.value) { agent ->
                        AssignDialogItem(
                            agent = agent,
                            primaryColor = primaryColor,
                            tertiaryColor = tertiaryColor,
                            isSelected = agent == landlordViewVM.selectedAgent.value,
                            onRadioButtonClicked = {
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