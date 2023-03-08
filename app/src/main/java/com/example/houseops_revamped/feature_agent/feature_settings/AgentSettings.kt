package com.example.houseops_revamped.feature_agent.feature_settings

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun AgentSettings(
    navHostController: NavHostController,
    primaryColor: Color,
    tertiaryColor: Color
) {
    Text(text = "Settings")
}