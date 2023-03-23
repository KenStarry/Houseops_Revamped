package com.kenstarry.houseops_revamped.core.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.repository.CoreRepository

class DeleteDocument(
    private val repo: CoreRepository
) {
    suspend operator fun invoke(
        collectionName: String,
        documentName: String,
        subCollectionName: String?,
        subCollectionDocument: String?,
        onResponse: (response: Response<*>) -> Unit
    ) = repo.deleteDocument(
        collectionName,
        documentName,
        subCollectionName,
        subCollectionDocument,
        onResponse
    )
}