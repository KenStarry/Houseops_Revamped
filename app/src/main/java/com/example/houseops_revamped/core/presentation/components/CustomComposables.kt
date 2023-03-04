package com.example.houseops_revamped.custom_components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.example.houseops_revamped.core.domain.model.TopbarDropdown

//  dropdown menus
@Composable
fun CustomDropdownMenuItem(
    dropdownItems: List<TopbarDropdown>
) {

    dropdownItems.forEach { item ->

        DropdownMenuItem(
            text = {
                Text(text = item.title)
            },
            leadingIcon = {
                Icon(
                    imageVector = item.icon!!,
                    contentDescription = "Dropdown icon"
                )
            },
            onClick = { item.onItemClicked() }
        )
    }

}


























