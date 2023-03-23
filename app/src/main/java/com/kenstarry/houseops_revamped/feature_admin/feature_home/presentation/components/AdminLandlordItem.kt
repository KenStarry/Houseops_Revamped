package com.kenstarry.houseops_revamped.feature_admin.feature_home.presentation.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.ModeEdit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.google.firebase.auth.FirebaseUser
import com.kenstarry.houseops_revamped.R
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.presentation.components.CoilImage
import com.kenstarry.houseops_revamped.core.presentation.components.Lottie
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun AdminLandlordItem(
    landlord: UsersCollection?,
    currentUser: FirebaseUser?,
    context: Context,
    primaryColor: Color,
    tertiaryColor: Color,
    onCardClicked: () -> Unit,
    onActionsClicked: () -> Unit
) {

    landlord?.let {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 0.dp
                )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .clickable { onCardClicked() },
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    //  landlord image
                    Row(
                        modifier = Modifier
                            .weight(1f),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        val uri = if (it.userImageUri?.uri?.isBlank() == true)
                            currentUser?.photoUrl ?: "".toUri()
                        else
                            it.userImageUri?.uri?.toUri()

                        CoilImage(
                            context = context,
                            imageUri = uri,
                            placeholder = R.drawable.profile,
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(50.dp)
                        )

                    }

                    //  landlord details
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .weight(4f)
                            .wrapContentHeight()
                            .background(MaterialTheme.colorScheme.onSecondary)
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(8.dp)
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                //  name
                                Text(
                                    text = if (it.userName == "no name")
                                        currentUser?.displayName ?: ""
                                    else
                                        it.userName ?: "",
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                        alpha = 0.8f
                                    )
                                )

                                if (it.userIsVerified)
                                    Lottie(
                                        rawFile = R.raw.blue_tick,
                                        isPlaying = true,
                                        iterations = 1,
                                        modifier = Modifier
                                            .size(30.dp)
                                    )

                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            //  location
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Icon(
                                    imageVector = Icons.Outlined.LocationOn,
                                    contentDescription = "Location",
                                    tint = primaryColor,
                                    modifier = Modifier
                                        .size(16.dp)
                                )

                                Text(
                                    text = "Pangani, Nairobi, Kenya",
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                        alpha = 0.5f
                                    )
                                )

                            }

                        }

                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                HomePillBtns(
                    icon = Icons.Outlined.ModeEdit,
                    title = "Actions",
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    onClick = {
                        onActionsClicked()
                    }
                )
            }

        }

    }

}