package com.example.houseops_revamped.core.domain.model.events

sealed class AlertDialogEvents {

    data class OpenAlertDialog(
        val dialogContent: String
    ) : AlertDialogEvents()
}
