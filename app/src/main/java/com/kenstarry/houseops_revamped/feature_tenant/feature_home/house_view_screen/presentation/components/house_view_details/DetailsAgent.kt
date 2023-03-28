package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.components.house_view_details

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.kenstarry.houseops_revamped.R
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.components.CoilImage
import com.kenstarry.houseops_revamped.core.presentation.components.permission_handling.RequestPermission
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.ui.theme.LimeGreen
import com.kenstarry.houseops_revamped.ui.theme.LimeGreenDull

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DetailsAgent(
    context: Context,
    house: HouseModel,
    agentEmail: String,
    onCardClicked: (agent: UsersCollection) -> Unit,
    onPhoneClicked: (phone: String) -> Unit
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val phonePermissionState = rememberPermissionState(
        permission = android.Manifest.permission.CALL_PHONE
    )
    var agent by remember {
        mutableStateOf<UsersCollection?>(null)
    }
    var apartmentDetails by remember {
        mutableStateOf<Apartment?>(null)
    }

    //  get apartment details of the house
    coreVM.onEvent(
        CoreEvents.GetApartmentDetails(
            apartmentName = house.houseApartmentName,
            apartmentDetails = {
                apartmentDetails = it
            },
            response = {}
        ))

    apartmentDetails?.apartmentAgentAssigned?.let {
        agent = coreVM.getUserDetails(it)
    }

    agent?.let { user ->

        RequestPermission(
            permissionState = phonePermissionState,
            deniedContent = {

                Button(onClick = {
                    phonePermissionState.launchPermissionRequest()
                }) {
                    Text(text = "Give call permission")
                }

            },
            content = {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable {
                            onCardClicked(user)
                        },
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.onSecondary,
                        contentColor = MaterialTheme.colorScheme.onSecondary
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        //  caretaker icon
                        Box(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            CoilImage(
                                context = context,
                                imageUri = user.userImageUri?.uri?.toUri(),
                                placeholder = R.drawable.houseops_dark_final,
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(50.dp)
                            )
                        }

                        //  caretaker name and role
                        Column(
                            modifier = Modifier
                                .weight(3f),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            //  name
                            Text(
                                text = user.userName ?: "",
                                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )

                            //  role
                            Text(
                                text = "HouseOps Agent",
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                            )

                        }

                        //  phone call
                        Row(
                            modifier = Modifier
                                .weight(1f),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            IconButton(
                                onClick = {
                                    //    add agent number
                                    onPhoneClicked("0700692069")
                                },
                                colors = IconButtonDefaults.iconButtonColors(
                                    contentColor = LimeGreen,
                                    containerColor = LimeGreenDull
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Phone,
                                    contentDescription = "Phone call"
                                )
                            }

                        }
                    }
                }
            }
        )

    }
}


















