package com.kenstarry.houseops_revamped.core.presentation.components.permission_handling

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermission(
    permission: String,
    deniedMessage: String = "Give HouseOps permission to proceed.",
    rationaleMessage: String = "To proceed with HouseOps, then you need to give us permission",
    deniedContent: @Composable (Boolean) -> Unit,
    content: @Composable () -> Unit
) {

    val permissionState = rememberPermissionState(permission = permission)

    when (permissionState.status) {
        is PermissionStatus.Granted -> {
            content()
        }
        is PermissionStatus.Denied -> {
            deniedContent(permissionState.status.shouldShowRationale)
        }
    }

}