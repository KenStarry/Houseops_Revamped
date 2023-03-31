package com.kenstarry.houseops_revamped.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants.AUTHENTICATION_ROUTE
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants.HOME_ROUTE
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants.LOADING_ROUTE
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants.ROOT_ROUTE
import com.kenstarry.houseops_revamped.navigation.graphs.admin_graphs.adminNavGraph
import com.kenstarry.houseops_revamped.navigation.graphs.agent_graphs.agentNavGraph
import com.kenstarry.houseops_revamped.navigation.graphs.landlord_graphs.landlordNavGraph
import com.kenstarry.houseops_revamped.navigation.graphs.tenant_graphs.tenantNavGraph

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    isLoggedIn: Boolean,
    userType: String?
) {

    NavHost(
        navController = navHostController,
        startDestination = if (isLoggedIn)
            HOME_ROUTE
        else if (!isLoggedIn)
            AUTHENTICATION_ROUTE
        else
            LOADING_ROUTE,

        route = ROOT_ROUTE
    ) {

        loadingNavGraph(navHostController)
        authNavGraph(navHostController = navHostController)
        tenantNavGraph(navHostController = navHostController)
        landlordNavGraph(navHostController = navHostController)
        adminNavGraph(navHostController = navHostController)
        agentNavGraph(navHostController = navHostController)
    }

}