package com.example.houseops_revamped.core.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DoneCancelButtons(
    onDone: () -> Unit,
    onCancel: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        //  cancel button
        TextButton(
            onClick = {
                onCancel()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.error
            )
        ) {

            Icon(
                imageVector = Icons.Outlined.Cancel,
                contentDescription = "cancel button"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = "Cancel")
        }

        Spacer(modifier = Modifier.width(16.dp))

        //  save button
        TextButton(onClick = {
            onDone()
        }) {

            Icon(
                imageVector = Icons.Outlined.Done,
                contentDescription = "Save button"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(text = "Done")
        }
    }
}