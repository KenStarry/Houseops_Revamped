package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.presentation.components.bottom_sheets

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowRight
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun BookHouseDatePicker(
    onCloseBottomSheet: () -> Unit,
    onHouseBooked: (date: String) -> Unit,
    primaryColor: Color,
    tertiaryColor: Color
) {

    var pickedDate by remember {
        mutableStateOf<LocalDate?>(null)
    }

    val context = LocalContext.current

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(pickedDate)
        }
    }

    val dateDialogState = rememberMaterialDialogState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Text(
            text = "Book Date",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = pickedDate != null
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        bottom = 24.dp
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Outlined.CalendarToday,
                    contentDescription = "calendar",
                    tint = MaterialTheme.colorScheme.primary
                )

                Text(
                    text = formattedDate,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
            }
        }

        //  current date
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Date",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )

            Icon(
                imageVector = Icons.Outlined.ArrowRight,
                contentDescription = "Date picker arrow",
                tint = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.5f)
            )

            HomePillBtns(
                icon = Icons.Outlined.CalendarMonth,
                title = "Pick a Date",
                onClick = {
                    dateDialogState.show()
                },
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor
            )

        }

        Spacer(modifier = Modifier.height(16.dp))

        //  confirm button
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextButton(onClick = {
                //  close bottomsheet
                onCloseBottomSheet()
            }) {
                Text(text = "Cancel")
            }

            TextButton(onClick = {

                if (pickedDate == null) {
                    Toast.makeText(context, "Please pick a date", Toast.LENGTH_SHORT).show()

                } else {
                    //  book house
                    pickedDate?.let { onHouseBooked(it.toString()) }
                }
            }) {
                Text(text = "Confirm")
            }

        }

    }

    MaterialDialog(
        dialogState = dateDialogState,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        ),
        shape = RoundedCornerShape(24.dp),
        onCloseRequest = {},
        buttons = {
            positiveButton(
                text = "Ok",
                textStyle = TextStyle(
                    color = primaryColor
                )
            )
            negativeButton(
                text = "Cancel",
                textStyle = TextStyle(
                    color = primaryColor
                )
            )
        },
        backgroundColor = MaterialTheme.colorScheme.onPrimary
    ) {

        datepicker(
            initialDate = LocalDate.now(),
            title = "Pick a Date",
            onDateChange = {
                pickedDate = it
            },
            colors = DatePickerDefaults.colors(
                headerBackgroundColor = tertiaryColor,
                headerTextColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                calendarHeaderTextColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                dateActiveBackgroundColor = primaryColor
            )
        )

    }

}












