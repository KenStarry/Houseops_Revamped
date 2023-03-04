package com.example.houseops_revamped.feature_admin.feature_home.presentation.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
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
import com.example.houseops_revamped.R
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.presentation.components.CoilImage
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns
import com.google.android.gms.maps.model.Circle

@Composable
fun AdminLandlordItem(
    landlord: UsersCollection?,
    context: Context
) {

    landlord?.let {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 0.dp
            )
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                //  landlord image
                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {

                    CoilImage(
                        context = context,
                        imageUri = it.userImageUri?.toUri(),
                        placeholder = R.drawable.profile,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(45.dp)
                    )

                }

                //  landlord details
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .weight(4f)
                        .wrapContentHeight()
                        .padding(16.dp)
                        .background(MaterialTheme.colorScheme.onSecondary),
                    horizontalAlignment = Alignment.Start
                ) {

                    //  name
                    Text(
                        text = it.userName ?: "",
                        fontSize = MaterialTheme.typography.titleSmall.fontSize,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))

                    //  location
                    HomePillBtns(
                        icon = Icons.Outlined.LocationOn,
                        title = "Pangani, Nairobi, Kenya",
                        primaryColor = MaterialTheme.colorScheme.primary,
                        tertiaryColor = MaterialTheme.colorScheme.tertiary
                    ) {

                    }

                }


            }

        }

    }

}