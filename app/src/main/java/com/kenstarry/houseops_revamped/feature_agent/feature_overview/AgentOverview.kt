package com.kenstarry.houseops_revamped.feature_agent.feature_overview

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun AgentOverview(
    navHostController: NavHostController,
    primaryColor: Color,
    tertiaryColor: Color
) {
    Text(text = "Overview")
}