package com.kenstarry.houseops_revamped.feature_authentication.presentation.sign_up.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.presentation.components.CustomTextField
import com.kenstarry.houseops_revamped.feature_authentication.presentation.model.RegistrationFormEvent
import com.kenstarry.houseops_revamped.feature_authentication.presentation.viewmodel.AuthenticationViewModel

@Composable
fun SignUpFields(
    authVM: AuthenticationViewModel = hiltViewModel(),
    primaryColor: Color,
    tertiaryColor: Color
) {
    //  email address
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomTextField(
            textFieldValue = authVM.formState.email,
            startIcon = Icons.Outlined.AlternateEmail,
            endIcon = null,
            placeholder = "Email Address",
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor,
            onInput = {
                authVM.onEvent(RegistrationFormEvent.EmailChanged(it))
            }
        )

        AnimatedVisibility(visible = authVM.formState.emailError != null) {
            ErrorMessage(
                message = authVM.formState.emailError
            )
        }
    }

    //  full name
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        CustomTextField(
            textFieldValue = authVM.formState.username,
            startIcon = Icons.Outlined.Person,
            endIcon = null,
            placeholder = "UserName",
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Text,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor,
            onInput = {
                authVM.onEvent(RegistrationFormEvent.UserNameChanged(it))
            }
        )

        AnimatedVisibility(visible = authVM.formState.usernameError != null) {
            ErrorMessage(message = authVM.formState.usernameError)
        }
    }

    //  password
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        CustomTextField(
            textFieldValue = authVM.formState.password,
            startIcon = Icons.Outlined.Key,
            endIcon = null,
            placeholder = "New Password",
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor,
            isPassword = true,
            onInput = {
                authVM.onEvent(RegistrationFormEvent.PasswordChanged(it))
            }
        )

        AnimatedVisibility(visible = authVM.formState.passwordError != null) {
            ErrorMessage(message = authVM.formState.passwordError)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        //  confirm password
        CustomTextField(
            textFieldValue = authVM.formState.repeatedPassword,
            startIcon = Icons.Outlined.Key,
            endIcon = null,
            placeholder = "Confirm Password",
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor,
            isPassword = true,
            onInput = {
                authVM.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it))
            }
        )

        AnimatedVisibility(visible = authVM.formState.repeatedPasswordError != null) {
            ErrorMessage(message = authVM.formState.repeatedPasswordError)
        }

    }
}