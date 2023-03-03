package com.example.houseops_revamped.feature_authentication.presentation.login.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.houseops_revamped.core.domain.model.events.CoreEvents
import com.example.houseops_revamped.core.presentation.components.LoadingCircle
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.feature_authentication.presentation.login.domain.model.LoginEvents
import com.example.houseops_revamped.feature_authentication.presentation.login.presentation.viewmodel.LoginViewModel
import com.example.houseops_revamped.core.presentation.utils.Constants.AUTHENTICATION_ROUTE
import com.example.houseops_revamped.feature_authentication.domain.model.ValidationEvent
import com.example.houseops_revamped.feature_authentication.presentation.login.domain.model.LoginFormEvent
import com.example.houseops_revamped.feature_authentication.presentation.login.presentation.components.CustomTextField
import com.example.houseops_revamped.feature_authentication.presentation.login.presentation.components.LoginButton
import com.example.houseops_revamped.feature_authentication.presentation.login.presentation.components.alert_dialogs.ForgotPasswordDialog
import com.example.houseops_revamped.feature_authentication.presentation.login.presentation.utils.LoginConstants
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.presentation.components.ErrorMessage
import com.example.houseops_revamped.feature_authentication.presentation.viewmodel.AuthenticationViewModel
import com.example.houseops_revamped.navigation.Direction
import com.example.houseops_revamped.navigation.screens.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navHostController: NavHostController
) {

    val loginVM: LoginViewModel = hiltViewModel()
    val coreVM: CoreViewModel = hiltViewModel()
    val authVM: AuthenticationViewModel = hiltViewModel()
    val direction = Direction(navHostController)

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

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

    val userType = coreVM.userTypeFlow.collectAsState(initial = null).value
    val userDetails = coreVM.getUserDetails(loginVM.formState.email)

//    userDetails?.let { user ->
//
//        Log.d("login", "userType -> ${user.userType}")
//
//        if (user.userType == AuthConstants.userTypes[0].userTitle) {
//            //  landlord
//            navHostController.navigate(LANDLORD_ROUTE) {
//                popUpTo(AUTHENTICATION_ROUTE)
//                launchSingleTop = true
//            }
//        } else if (user.userType == AuthConstants.userTypes[1].userTitle) {
//            //  tenant
//            navHostController.navigate(HOME_ROUTE) {
//                popUpTo(AUTHENTICATION_ROUTE)
//                launchSingleTop = true
//            }
//        }
//    }

    var loginPassState by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    var isLoggedIn by remember {
        mutableStateOf(false)
    }

    if (isLoggedIn) {
        //  save user to datastore
        LaunchedEffect(key1 = Unit) {
            coreVM.onEvent(
                CoreEvents.DatastoreSaveUserType(
                    userDetails?.userType ?: "none"
                ))
        }
    }


    LaunchedEffect(key1 = context) {
        loginVM.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {

                    isLoading = true

                    loginVM.onEvent(
                        LoginEvents.Login(
                            email = loginVM.formState.email,
                            password = loginPassState,
                            onResponse = {
                                //  check login event
                                when (val response = it) {

                                    is Response.Success -> {

                                        isLoading = false
                                        isLoggedIn = true

                                        //  take user to loading screen then determine the type of user
                                        navHostController.navigate(Screens.Loading.passEmail(loginVM.formState.email)) {
                                            popUpTo(AUTHENTICATION_ROUTE)
                                            launchSingleTop = true
                                        }

                                        Log.d("login", "Logged in successfully as $userType")
                                    }
                                    is Response.Failure -> {

                                        isLoading = false

                                        Toast.makeText(
                                            context,
                                            "${response.error}",
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

                is ValidationEvent.Failure -> {
                    Toast.makeText(context, "Check details!", Toast.LENGTH_SHORT).show()
                }
            }
        }
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
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

        //  email address
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CustomTextField(
                textFieldValue = loginVM.formState.email,
                startIcon = Icons.Outlined.AlternateEmail,
                endIcon = null,
                placeholder = "Email Address",
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor
            ) {
                loginVM.onFormEvent(LoginFormEvent.EmailChanged(it))
            }

            AnimatedVisibility(visible = loginVM.formState.emailError != null) {
                isLoading = false
                ErrorMessage(message = loginVM.formState.emailError)
            }
        }

        CustomTextField(
            startIcon = Icons.Outlined.Key,
            endIcon = null,
            placeholder = "Password",
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password,
            primaryColor = primaryColor,
            tertiaryColor = tertiaryColor,
            isPassword = true
        ) {
            loginPassState = it
        }

        //  forgot password text
        Text(
            text = "Forgot Password?",
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontWeight = FontWeight.Bold,
            color = primaryColor,
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
                .height(50.dp)
                .background(MaterialTheme.colorScheme.onPrimary),
            contentAlignment = Alignment.Center
        ) {

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
                    loginVM.onFormEvent(LoginFormEvent.Submit)
                }
            }

        }

        //  or
        Text(
            text = "OR",
            fontSize = MaterialTheme.typography.bodyMedium.fontSize,
            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
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
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )
            }

        }

        //  create Account Text
        AnnotatedClickableString(
            normalText = "New to HouseOps? ",
            clickableText = "Create Account",
            primaryColor = primaryColor
        ) {
            //  navigate to signup screen
            navHostController.navigate(Screens.SignUp.route)
        }

    }
}

//  clickable text parts
@Composable
fun AnnotatedClickableString(
    normalText: String,
    clickableText: String,
    primaryColor: Color,
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
                color = primaryColor,
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





































