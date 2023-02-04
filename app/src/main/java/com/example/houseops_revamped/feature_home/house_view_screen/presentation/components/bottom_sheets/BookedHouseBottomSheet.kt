package com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.bottom_sheets

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
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
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  title
        Text(
            text = "Booked Successfully!",
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

        //  lottie
        Lottie(
            rawFile = R.raw.successful,
            isPlaying = true,
            iterations = 1,
            modifier = Modifier
                .fillMaxWidth(0.9f)
        )

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(text = "View")
        }

    }


}