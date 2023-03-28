package com.kenstarry.houseops_revamped.core.presentation.components.permission_handling

import androidx.compose.runtime.Composable
import com.google.accompanist.permissions.*

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermission(
    permissionState: PermissionState,
    deniedMessage: String = "Give HouseOps permission to proceed.",
    rationaleMessage: String = "To proceed with HouseOps, then you need to give us permission",
    deniedContent: @Composable (Boolean) -> Unit,
    content: @Composable () -> Unit
) {

    when (permissionState.status) {
        is PermissionStatus.Granted -> {
            content()
        }
        is PermissionStatus.Denied -> {
            deniedContent(permissionState.status.shouldShowRationale)
        }
    }

}