package com.example.houseops_revamped.feature_admin.feature_agents.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_admin.feature_agents.domain.model.AdminAgentsEvents
import com.example.houseops_revamped.feature_admin.feature_agents.presentation.components.AdminAgentsAppBar
import com.example.houseops_revamped.feature_admin.feature_agents.presentation.components.AdminAgentsItem
import com.example.houseops_revamped.feature_admin.feature_agents.presentation.viewmodel.AdminAgentsViewModel
import com.example.houseops_revamped.navigation.Direction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminAgentsScreen(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val adminAgentsVM: AdminAgentsViewModel = hiltViewModel()
    val direction = Direction(navHostController)
    val context = LocalContext.current

    val listState = rememberLazyListState()

    adminAgentsVM.onEvent(AdminAgentsEvents.GetAgents(
        response = {}
    ))

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

    Scaffold(

        topBar = {
            AdminAgentsAppBar(
                title = "Agents",
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor,
                onBackPressed = { /*TODO*/ },
                onSort = {}
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
            ) {

                LazyColumn(
                    content = {
                        itemsIndexed(adminAgentsVM.agents.value) { index, agent ->
                            AdminAgentsItem(
                                agent = agent,
                                context = context,
                                primaryColor = primaryColor,
                                tertiaryColor = tertiaryColor,
                                onCardClicked = { /*TODO*/ },
                                onActionsClicked = {}
                            )
                        }
                    },
                    state = listState,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                )

            }

        }

    }

}





















