package com.example.houseops_revamped.feature_admin.feature_home.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.feature_admin.feature_home.presentation.viewmodel.AdminHomeViewModel

@Composable
fun AdminViewLandlords(
    adminHomeVM: AdminHomeViewModel = hiltViewModel(),
    context: Context,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val state = rememberLazyListState()

    LazyColumn(
        content = {
            items(adminHomeVM.landlords.value) { landlord ->

                //  landlord item
                AdminLandlordItem(
                    landlord = landlord,
                    context = context,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor
                )
                
                Spacer(modifier = Modifier.height(16.dp))
            }
        },
        state = state,
        modifier = Modifier
            .fillMaxSize()
    )

}