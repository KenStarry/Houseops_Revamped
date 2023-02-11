package com.example.houseops_revamped.feature_booked.presentation.components.booked_houses

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun BookedItem(
    modifier: Modifier = Modifier,
    date: String
) {

    val formattedDate by remember {
        mutableStateOf(
            DateTimeFormatter
                .ofPattern("MMM dd yyyy")
                .format(LocalDate.parse(date))
        )
    }

    Row(
        modifier = modifier
    ) {

        //  headerit a
        Column(
            modifier = Modifier
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //  ring
            Ring()
            
            Spacer(modifier = Modifier.height(8.dp))

            Divider(
                modifier = Modifier
                    .width(2.dp)
                    .height(50.dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        //  content
        Column(
            modifier = Modifier
                .weight(10f),
            horizontalAlignment = Alignment.Start
        ) {

            //  date
            Text(
                text = formattedDate,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
            )
            

        }

    }
}























