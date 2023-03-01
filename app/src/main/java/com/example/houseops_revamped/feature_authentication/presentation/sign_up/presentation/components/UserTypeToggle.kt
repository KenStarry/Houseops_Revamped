package com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.feature_authentication.presentation.model.UserType
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.domain.model.SignUpEvents
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation.viewmodel.SignUpViewModel

@Composable
fun UserTypeToggle(
    userTypes: List<UserType>,
    signUpVM: SignUpViewModel = hiltViewModel()
) {

    val listState = rememberLazyListState()

    LazyRow(
        content = {

            itemsIndexed(userTypes) { index, user ->

                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(48.dp))
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .background(
                            color = if (user == signUpVM.chosenUserType.value)
                                MaterialTheme.colorScheme.tertiary
                            else
                                MaterialTheme.colorScheme.onSecondary
                        )
                        .clickable {
                            //  change user type
                            signUpVM.onEvent(SignUpEvents.ToggleUserType(user))
                        }
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.Center
                ) {

                    Icon(
                        imageVector = user.icon,
                        contentDescription = "User type icon",
                        tint = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(text = user.userTitle)

                }
            }
        },
        state = listState,
        modifier = Modifier
            .clip(RoundedCornerShape(48.dp))
            .wrapContentWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.onSecondary),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    )

}