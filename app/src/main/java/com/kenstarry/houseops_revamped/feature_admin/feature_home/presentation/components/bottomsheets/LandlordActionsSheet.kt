package com.kenstarry.houseops_revamped.feature_admin.feature_home.presentation.components.bottomsheets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.outlined.ModeEdit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns
import com.kenstarry.houseops_revamped.ui.theme.LimeGreen

@Composable
fun LandlordActionsSheet(
    landlord: UsersCollection?,
    primaryColor: Color,
    tertiaryColor: Color,
    onVerifyClicked: (landlordEmail: String, isLandlordVerified: Boolean) -> Unit
) {

    landlord?.let {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {

            //  title
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(45.dp)
                        .background(tertiaryColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ModeEdit,
                        contentDescription = "Actions icon",
                        tint = primaryColor
                    )
                }

                Text(
                    text = "Landlord Actions",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            //  username
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = it.userName ?: "",
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

                Icon(
                    imageVector = Icons.Outlined.ArrowRight,
                    contentDescription = "Actions icon",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f)
                )

                HomePillBtns(
                    icon = Icons.Outlined.AlternateEmail,
                    title = it.userEmail ?: "",
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    onClick = {}
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            //  verification status
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(MaterialTheme.colorScheme.onSecondary)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Verification Status",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

                if (it.userIsVerified) {

                    //  show verification success status
                    TextButton(
                        onClick = { onVerifyClicked(it.userEmail ?: "", false) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onSecondary,
                            contentColor = LimeGreen
                        )
                    ) {

                        Icon(
                            imageVector = Icons.Outlined.Done,
                            contentDescription = "Verified landlord",
                            tint = LimeGreen
                        )
                        
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "verified!",
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = FontWeight.Bold
                        )
                    }

                } else {

                    //  show the verify button
                    Button(
                        onClick = { onVerifyClicked(it.userEmail ?: "", true) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryColor
                        )
                    ) {
                        Text(
                            text = "Verify Landlord",
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                        )
                    }
                }

            }

        }

    }
}