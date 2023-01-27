package com.example.houseops_revamped.core.domain.use_cases

import com.example.houseops_revamped.core.domain.model.LikedHouse
import com.example.houseops_revamped.core.domain.repository.CoreRepository

class UpdateLikedHousesField(
    private val repository: CoreRepository
) {

    suspend operator fun invoke(
        collectionName: String,
        documentName: String,
        fieldName: String,
        fieldValue: LikedHouse,
        isAddItem: Boolean
    ) = repository.updateLikedHousesField(
        collectionName,
        documentName,
        fieldName,
        fieldValue,
        isAddItem
    )
}