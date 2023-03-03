package com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.feature_authentication.domain.utils.AuthConstants
import com.example.houseops_revamped.feature_authentication.presentation.model.UserType
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.domain.model.SignUpEvents
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation.viewmodel.SignUpViewModel
import com.example.houseops_revamped.feature_authentication.presentation.viewmodel.AuthenticationViewModel
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.HomePillBtns

@Composable
fun UserTypeToggle(
    userTypes: List<UserType>,
    authVM: AuthenticationViewModel = hiltViewModel(),
    signUpVM: SignUpViewModel = hiltViewModel(),
    onSelectUserType: (userType: UserType) -> Unit
) {

    val listState = rememberLazyListState()

    val userTypesFiltered = userTypes.filter { it == AuthConstants.userTypes[0] || it == AuthConstants.userTypes[1] }

    Box(
        modifier = Modifier
            .wrapContentSize()
    ) {

        AnimatedVisibility(visible = !Constants.adminEmails.any {
            it == authVM.formState.email
        } && !Constants.agentEmails.any {
            it == authVM.formState.email
        }) {

            //  toggle tenant type
//            signUpVM.onEvent(SignUpEvents.ToggleUserType(AuthConstants.userTypes[1]))

            LazyRow(
                content = {

                    itemsIndexed(userTypesFiltered) { index, user ->

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
                                    onSelectUserType(user)

                                    Log.d("userType", "type : $user")
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

        //  if user is admin, display the text
        AnimatedVisibility(visible = Constants.adminEmails.any {
            it == authVM.formState.email
        }) {

            //  toggle admin user type
            signUpVM.onEvent(SignUpEvents.ToggleUserType(AuthConstants.userTypes[2]))

            //  admin interface
            HomePillBtns(
                icon = Icons.Outlined.AdminPanelSettings,
                title = "Sign Up as Admin",
                primaryColor = MaterialTheme.colorScheme.primary,
                tertiaryColor = MaterialTheme.colorScheme.tertiary,
                onClick = {}
            )
        }

        //  if user is agent, display the text
        AnimatedVisibility(visible = Constants.agentEmails.any {
            it == authVM.formState.email
        }) {
            //  toggle agent user type
            signUpVM.onEvent(SignUpEvents.ToggleUserType(AuthConstants.userTypes[3]))

            //  admin interface
            HomePillBtns(
                icon = Icons.Outlined.SupportAgent,
                title = "Sign Up as Agent",
                primaryColor = MaterialTheme.colorScheme.primary,
                tertiaryColor = MaterialTheme.colorScheme.tertiary,
                onClick = {}
            )
        }

    }
}
























