package com.kenstarry.houseops_revamped.feature_admin.feature_landlord_view.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection

@Composable
fun AssignDialogItem(
    agent: UsersCollection,
    primaryColor: Color,
    tertiaryColor: Color,
    isSelected: Boolean,
    onRadioButtonClicked: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = 16.dp)
            .selectable(
                selected = isSelected,
                onClick = onRadioButtonClicked
            ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        RadioButton(
            selected = isSelected,
            onClick = onRadioButtonClicked,
            colors = RadioButtonDefaults.colors(
                selectedColor = primaryColor,
                unselectedColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f)
            )
        )

        Column(
            modifier = Modifier
                .wrapContentSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = agent.userName ?: "",
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                    alpha = 0.8f
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.AlternateEmail,
                    contentDescription = "Email",
                    tint = primaryColor,
                    modifier = Modifier
                        .size(16.dp)
                )

                Text(
                    text = agent.userEmail ?: "",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                        alpha = 0.6f
                    )
                )
            }

        }

    }
}