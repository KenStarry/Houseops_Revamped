package com.kenstarry.houseops_revamped.core.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.repository.CoreRepository

class UpdateUsersArrayField(
    private val repository: CoreRepository
) {

    suspend operator fun invoke(
        collectionName: String,
        documentName: String,
        fieldName: String,
        fieldValue: String,
        isAddItem: Boolean
    ) = repository.updateUserArrayField(
        collectionName,
        documentName,
        fieldName,
        fieldValue,
        isAddItem
    )
}