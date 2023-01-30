package com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.house_view_details

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.core.presentation.components.CoilImage
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel

@Composable
fun DetailsCaretaker(
    context: Context,
    apartment: String,
    coreVM: CoreViewModel = hiltViewModel()
) {

    val caretaker = coreVM.getCaretakerDetails(apartment)

    caretaker?.let {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSecondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                //  caretaker icon
                Box(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    CoilImage(
                        context = context,
                        imageUri = it.caretakerImage?.toUri(),
                        placeholder = com.example.houseops_revamped.R.drawable.houseops_dark_final,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(50.dp)
                    )
                }

                //  caretaker name and role
                Column(
                    modifier = Modifier
                        .weight(3f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    //  name
                    Text(
                        text = it.caretakerName ?: "",
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )

                    //  role
                    Text(
                        text = "caretaker",
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    )

                }

            }
        }

    }
}