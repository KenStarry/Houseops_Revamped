package com.example.houseops_revamped.feature_bookmark.domain.model

sealed class BookmarkEvents {

    data class ToggleCategoryVisibility(
        val isVisible: Boolean
    ) : BookmarkEvents()
}
