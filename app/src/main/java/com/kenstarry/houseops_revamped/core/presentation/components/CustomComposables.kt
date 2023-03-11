package com.kenstarry.houseops_revamped.core.presentation.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.kenstarry.houseops_revamped.core.domain.model.TopbarDropdown

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


























