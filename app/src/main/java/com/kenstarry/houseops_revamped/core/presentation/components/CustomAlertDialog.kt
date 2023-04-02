package com.kenstarry.houseops_revamped.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.kenstarry.houseops_revamped.ui.custom.spacing

@Composable
fun CustomAlertDialog(
    icon: ImageVector,
    primaryColor: Color,
    tertiaryColor: Color,
    title: String,
    content: @Composable ColumnScope.() -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.onPrimary,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {

    AlertDialog(
        onDismissRequest = { onDismiss() },
        shape = RoundedCornerShape(MaterialTheme.spacing.medium),
        containerColor = containerColor,
        text = {
            Column(
                modifier = Modifier
                    .wrapContentSize(),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
            ) {

                //  title
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(35.dp)
                            .background(tertiaryColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = "Alert icon",
                            tint = primaryColor,
                            modifier = Modifier
                                .size(16.dp)
                        )
                    }

                    Icon(
                        imageVector = Icons.Outlined.ArrowRight,
                        contentDescription = "Alert icon",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.6f),
                        modifier = Modifier
                            .size(16.dp)
                    )

                    //  alert title
                    Text(
                        text = title,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 1f)
                    )
                }
                content()
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = primaryColor
                )
            ) {
                Text(
                    text = "Confirm",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = primaryColor
                )
            ) {
                Text(
                    text = "Cancel",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        properties = DialogProperties(
            dismissOnBackPress = true
        )
    )
}













