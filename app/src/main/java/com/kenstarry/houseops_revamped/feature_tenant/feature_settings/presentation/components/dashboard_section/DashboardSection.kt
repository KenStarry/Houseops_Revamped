package com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components.dashboard_section

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants.ADMIN_ROUTE
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants.AGENT_ROUTE
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants.LANDLORD_ROUTE
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_authentication.domain.utils.AuthConstants
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components.SectionTitle
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.utils.SettingsConstants
import com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.viewmodel.SettingsViewModel
import com.kenstarry.houseops_revamped.navigation.Direction
import com.kenstarry.houseops_revamped.navigation.screens.Screens

@Composable
fun DashboardSection(
    context: Context,
    userDetails: UsersCollection?,
    directionInner: Direction,
    direction: Direction,
    modifier: Modifier = Modifier,
    settingsViewModel: SettingsViewModel,
    coreViewModel: CoreViewModel,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val listState = rememberLazyListState()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {

        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            SectionTitle(
                title = SettingsConstants.settingsSections[0].sectionTitle,
                icon = SettingsConstants.settingsSections[0].sectionIcon,
                iconColor = primaryColor,
                iconBackground = tertiaryColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                settingsViewModel = settingsViewModel
            )

            AnimatedVisibility(
                visible = settingsViewModel.isDashboardSectionVisible.value,
                enter = expandIn(),
                exit = shrinkOut()
            ) {

                LazyColumn(
                    content = {
                        items(
                            items = SettingsConstants.dashboardOptions
                        ) {
                            userDetails?.userType?.forEach { type ->
                                //  dashboard item
                                DashboardItem(
                                    userType = type,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                        .clickable {
                                            when (type) {
                                                //  landlord
                                                AuthConstants.userTypes[0].userTitle -> {
                                                    direction.navigateToRoute(
                                                        LANDLORD_ROUTE,
                                                        null
                                                    )
                                                }
                                                //  tenant
                                                AuthConstants.userTypes[1].userTitle -> {
                                                    //  open normal dashboard
                                                    directionInner.navigateToRoute(
                                                        Screens.Dashboard.route,
                                                        null
                                                    )
                                                }
                                                //  admin
                                                AuthConstants.userTypes[2].userTitle -> {
                                                    direction.navigateToRoute(
                                                        ADMIN_ROUTE,
                                                        null
                                                    )
                                                }
                                                //  agent
                                                AuthConstants.userTypes[3].userTitle -> {
                                                    direction.navigateToRoute(
                                                        AGENT_ROUTE,
                                                        null
                                                    )
                                                }
                                            }
                                        }
                                        .padding(
                                            end = 16.dp
                                        )
                                )

                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    },
                    state = listState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(((50.dp + 16.dp) * userDetails?.userType?.size!!) * SettingsConstants.dashboardOptions.size),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                )

            }
        }

    }
}