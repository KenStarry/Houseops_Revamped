package com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.bottom_sheets

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Minimize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.R
import com.example.houseops_revamped.core.presentation.components.Lottie
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

@Composable
fun BookedHouseBottomSheet(
    house: HouseModel?
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //  title
        Text(
            text = "Booked Successfully!",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Glad to see you found something you like!",
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )
        Spacer(modifier = Modifier.height(16.dp))

        //  lottie
        Lottie(
            rawFile = R.raw.successful,
            isPlaying = true,
            iterations = 1,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(250.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "View Status",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                fontWeight = FontWeight.Bold
            )
        }

    }


}