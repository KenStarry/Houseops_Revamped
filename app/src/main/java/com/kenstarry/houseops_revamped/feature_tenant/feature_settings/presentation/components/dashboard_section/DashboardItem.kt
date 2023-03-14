package com.kenstarry.houseops_revamped.feature_tenant.feature_settings.presentation.components.dashboard_section

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.OpenInFull
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.feature_authentication.domain.utils.AuthConstants

@Composable
fun DashboardItem(
    modifier: Modifier = Modifier,
    userType: String,
) {

    val dashIcon = when (userType) {
        //  landlord
        AuthConstants.userTypes[0].userTitle -> AuthConstants.userTypes[0].icon
        AuthConstants.userTypes[1].userTitle -> AuthConstants.userTypes[1].icon
        AuthConstants.userTypes[2].userTitle -> AuthConstants.userTypes[2].icon
        AuthConstants.userTypes[3].userTitle -> AuthConstants.userTypes[3].icon
        else -> AuthConstants.userTypes[1].icon
    }

    val dashText = when (userType) {
        //  landlord
        AuthConstants.userTypes[0].userTitle -> AuthConstants.userTypes[0].userTitle
        AuthConstants.userTypes[1].userTitle -> AuthConstants.userTypes[1].userTitle
        AuthConstants.userTypes[2].userTitle -> AuthConstants.userTypes[2].userTitle
        AuthConstants.userTypes[3].userTitle -> AuthConstants.userTypes[3].userTitle
        else -> AuthConstants.userTypes[1].userTitle
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .wrapContentSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(MaterialTheme.colorScheme.onPrimary),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = dashIcon,
                    contentDescription = "icon",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
                )
            }

            Text(
                text = "$dashText Dashboard",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
            )
        }

    }
}