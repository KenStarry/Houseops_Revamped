package com.example.houseops_revamped.feature_agent.feature_home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Hotel
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.domain.model.Apartment
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns
import com.google.android.gms.maps.model.Circle

@Composable
fun AgentHomeApartmentItem(
    modifier: Modifier = Modifier,
    apartment: Apartment,
    primaryColor: Color,
    tertiaryColor: Color
) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onSecondary)
                .clickable { }
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.onSecondary,
                        contentColor = primaryColor
                    )
                ) {
                    Text(
                        text = "0 Houses",
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.SemiBold,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            //  apartment name
            Text(
                text = apartment.apartmentName,
                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                fontWeight = FontWeight.ExtraBold,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            //  action icons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                //  notifications
                HomePillBtns(
                    icon = Icons.Outlined.Notifications,
                    iconSize = 16.dp,
                    spacing = 4.dp,
                    title = "0",
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor,
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    paddingHorizontal = 8.dp,
                    paddingVertical = 4.dp
                ) {

                }

            }

            Spacer(modifier = Modifier.height(8.dp))

        }

    }

}

























