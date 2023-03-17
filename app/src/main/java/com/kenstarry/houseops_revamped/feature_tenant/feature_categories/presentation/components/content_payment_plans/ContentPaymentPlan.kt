package com.kenstarry.houseops_revamped.feature_tenant.feature_categories.presentation.components.content_payment_plans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kenstarry.houseops_revamped.core.presentation.components.ComingSoon

@Composable
fun ContentPaymentPlan() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
    ) {

        ComingSoon(
            title = "",
            modifier = Modifier
                .fillMaxSize()
        )

    }
}