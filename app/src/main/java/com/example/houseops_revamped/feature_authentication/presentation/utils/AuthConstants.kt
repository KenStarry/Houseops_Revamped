package com.example.houseops_revamped.feature_authentication.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.VerifiedUser
import com.example.houseops_revamped.feature_authentication.presentation.model.UserType

object AuthConstants {

    val userTypes = listOf(
        UserType(Icons.Outlined.AdminPanelSettings, "Landlord"),
        UserType(Icons.Outlined.VerifiedUser, "Tenant")
    )
}