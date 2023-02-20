package com.example.houseops_revamped.feature_authentication.login.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.R
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.presentation.components.LoadingCircle
import com.example.houseops_revamped.core.presentation.components.Lottie
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_authentication.login.domain.model.LoginEvents
import com.example.houseops_revamped.feature_authentication.login.presentation.viewmodel.LoginViewModel
import com.example.houseops_revamped.core.utils.Constants.AUTHENTICATION_ROUTE
import com.example.houseops_revamped.core.utils.Constants.HOME_ROUTE
import com.example.houseops_revamped.feature_authentication.login.presentation.components.CustomTextField
import com.example.houseops_revamped.feature_authentication.login.presentation.components.LoginButton
import com.example.houseops_revamped.feature_authentication.login.presentation.components.alert_dialogs.ForgotPasswordDialog
import com.example.houseops_revamped.feature_authentication.login.presentation.utils.LoginConstants
import com.example.houseops_revamped.navigation.Direction
import com.example.houseops_revamped.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navHostController: NavHostController
) {

    val loginVM: LoginViewModel = hiltViewModel()
    val coreVM: CoreViewModel = hiltViewModel()
    val direction = Direction(navHostController)

    val context = LocalContext.current

    val primaryColor = Color(
        coreVM.primaryAccentFlow.collectAsState(
            initial = Constants.accentColors[0].darkColor
        ).value ?: Constants.accentColors[0].darkColor
    )

    val tertiaryColor = Color(
        coreVM.tertiaryAccentFlow.collectAsState(
            initial = Constants.accentColors[0].lightColor
        ).value ?: Constants.accentColors[0].lightColor
    )

    var loginEmailState by remember {
        mutableStateOf("")
    }
    var loginPassState by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        //  alert dialogs
        AnimatedVisibility(visible = loginVM.isForgotPasswordDialogVisible.value) {
            ForgotPasswordDialog(
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor,
                onConfirm = { email ->
                    //  send password reset email
                    loginVM.onEvent(LoginEvents.PasswordResetEmail(
                        email = email,
                        onResponse = {
                            when (it) {
                                is Response.Success -> {

                                    //  dismiss dialog
                                    LoginEvents.ToggleAlertDialog(
                                        dialogType = LoginConstants.FORGOT_PASSWORD_DIALOG,
                                        isDialogVisible = false
                                    )

                                    //  show the reset email page
                                    direction.navigateToRoute(
                                        Screens.ForgotPassword.route,
                                        false
                                    )
                                }

                                is Response.Failure -> {

                                }
                                Response.Loading -> {
                                    //  show loading
                                }
                                null -> {
                                    //  give error message
                                }
                            }
                        }
                    ))
                },
                onDismiss = {
                    //  close dialog
                    loginVM.onEvent(
                        LoginEvents.ToggleAlertDialog(
                            dialogType = LoginConstants.FORGOT_PASSWORD_DIALOG,
                            isDialogVisible = false
                        )
                    )
                }
            )
        }


        //  svg icon
        Image(
            painter = painterResource(id = R.drawable.undraw_enter_uhqk),
            contentDescription = "Login SVG",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(220.dp)
        )

        //  login
        Text(
            modifier = Modifier
                .align(Alignment.Start),
            text = "Login",
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight
        )

        //  input fields
        //  email address
        CustomTextField(
            startIcon = Icons.Outlined.AlternateEmail,
            endIcon = null,
            placeholder = "Email Address",
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Email,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor
        ) {
            loginEmailState = it
        }

        //  password
        PasswordTextField(
            startIcon = Icons.Outlined.Lock,
            placeholder = "Password",
            imeAction = ImeAction.Done
        ) {
            loginPassState = it
        }

        //  forgot password text
        Text(
            text = "Forgot Password?",
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.End)
                .clickable {
                    //  forgot password screen
                    loginVM.onEvent(
                        LoginEvents.ToggleAlertDialog(
                            dialogType = LoginConstants.FORGOT_PASSWORD_DIALOG,
                            isDialogVisible = true
                        )
                    )
                }
        )

        //  login button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onPrimary),
            contentAlignment = Alignment.Center
        ) {

            var isLoading by remember {
                mutableStateOf(false)
            }

            if (isLoading) {

                LoadingCircle(
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor
                )

            } else {
                LoginButton(
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor
                ) {

                    isLoading = true

                    loginVM.onEvent(
                        LoginEvents.Login(
                            email = loginEmailState,
                            password = loginPassState,
                            onResponse = {
                                //  check login event
                                when (val response = it) {

                                    is Response.Success -> {

                                        isLoading = false

                                        navHostController.navigate(HOME_ROUTE) {
                                            popUpTo(AUTHENTICATION_ROUTE)
                                            launchSingleTop = true
                                        }

                                        Toast.makeText(
                                            context,
                                            "Login successful!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    is Response.Loading -> {
                                        isLoading = true
                                    }
                                    is Response.Failure -> {

                                        isLoading = false

                                        Toast.makeText(
                                            context,
                                            "${response.e}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Log.d("login", "$response")
                                    }
                                    else -> {
                                        isLoading = true
                                    }
                                }
                            }
                        ))
                }
            }

        }

        //  or
        Text(
            text = "OR",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight
        )

        //  Sign in with Google Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onPrimary),
            contentAlignment = Alignment.Center
        ) {

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.onSecondary,
                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.3f),
                    disabledContentColor = Color.White.copy(alpha = 0.3f)
                ),
                enabled = true
            ) {
                Text(
                    text = "Sign In With Google",
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    color = Color.White
                )
            }

        }

        //  create Account Text
        AnnotatedClickableString(
            normalText = "New to HouseOps? ",
            clickableText = "Create Account"
        ) {
            //  navigate to signup screen
            navHostController.navigate(Screens.SignUp.route)
        }

    }
}

//  password input textfield
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.PasswordTextField(
    startIcon: ImageVector?,
    placeholder: String,
    imeAction: ImeAction,
    onPasswordInput: (input: String) -> Unit
) {

    var passValueState by remember {
        mutableStateOf("")
    }
    var passVisibilityState by remember {
        mutableStateOf(false)
    }

    val icon = if (passVisibilityState)
        Icons.Outlined.Visibility
    else
        Icons.Outlined.VisibilityOff

    TextField(
        value = passValueState,
        onValueChange = {
            passValueState = it
            onPasswordInput(passValueState)
        },
        leadingIcon = {
            if (startIcon != null) {
                Icon(
                    imageVector = startIcon,
                    contentDescription = "Leading Icon",
                    tint = Color.LightGray.copy(alpha = 0.9f)
                )
            }
        },
        trailingIcon = {
            IconButton(onClick = {
                passVisibilityState = !passVisibilityState
            }) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Trailing Icon"
                )
            }
        },
        placeholder = {
            Text(text = placeholder)
        },
        maxLines = 1,
        singleLine = true,

        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            imeAction = imeAction,
            keyboardType = KeyboardType.Password
        ),

        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Start),

        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.LightGray.copy(alpha = 0.3f),
            focusedIndicatorColor = MaterialTheme.colorScheme.primary
        ),

        visualTransformation = if (passVisibilityState)
            VisualTransformation.None
        else
            PasswordVisualTransformation()
    )
}

//  clickable text parts
@Composable
fun AnnotatedClickableString(
    normalText: String,
    clickableText: String,
    onTextClicked: () -> Unit
) {

    val tag = "TAG"

    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f)
            )
        ) {
            append(normalText)
        }

        //  making the text clickable
        pushStringAnnotation(
            //  tag which will be provided when you click the text
            tag = tag,
            annotation = tag
        )

        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.ExtraBold
            )
        ) {
            append(clickableText)
        }

        //  end of annotation with the current tag
        pop()
    }

    //  clickableText to enable clicking
    ClickableText(
        text = annotatedText,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = tag,
                start = offset,
                end = offset
            )[0].let {
                onTextClicked()
            }
        }
    )

}





































