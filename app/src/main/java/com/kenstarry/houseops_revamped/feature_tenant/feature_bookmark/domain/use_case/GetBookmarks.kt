package com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.domain.use_case

import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.domain.repository.BookmarksRepository

class GetBookmarks(
    private val repo: BookmarksRepository
) {

    suspend operator fun invoke(
        email: String,
        bookmarks: (List<String>) -> Unit
    ) = repo.getBookmarks(
        userEmail = email,
        bookmarks = { bookmarks(it) }
    )

}