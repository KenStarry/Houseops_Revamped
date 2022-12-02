package com.example.houseops_revamped.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.R
import com.example.houseops_revamped.custom_components.BackPressTopAppBar
import com.example.houseops_revamped.navigation.AUTHENTICATION_ROUTE
import com.example.houseops_revamped.network.createAccount
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navHostController: NavHostController
) {

    val auth = Firebase.auth

    Scaffold(
        topBar = {
            BackPressTopAppBar(title = "") {
                //  navigate back to login screen
                navHostController.navigate(AUTHENTICATION_ROUTE) {
                    popUpTo(AUTHENTICATION_ROUTE)
                }
            }
        }
    ) { contentPadding ->

        val context = LocalContext.current

        var emailInputState by remember {
            mutableStateOf("")
        }
        var fullNameInputState by remember {
            mutableStateOf("")
        }
        var newPassInputState by remember {
            mutableStateOf("")
        }
        var confirmPassInputState by remember {
            mutableStateOf("")
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                //  svg icon
                Image(
                    painter = painterResource(id = R.drawable.undraw_writer_q06d),
                    contentDescription = "Create Account SVG",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp)
                )

                //  signup text
                Text(
                    modifier = Modifier
                        .align(Alignment.Start),
                    text = "Sign Up",
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                )

                //  pick image icon
                Column(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(150.dp)
                        .background(MaterialTheme.colorScheme.onSecondary)
                        .align(Alignment.CenterHorizontally),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Icon(
                        imageVector = Icons.Outlined.ImageSearch,
                        contentDescription = "Image Picker Icon",
                        modifier = Modifier
                            .size(80.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    //  pick image text
                    Text(
                        text = "Pick Image",
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        fontWeight = MaterialTheme.typography.bodySmall.fontWeight
                    )

                }

                //  email address
                SignUpEmailTextField(
                    startIcon = Icons.Outlined.AlternateEmail,
                    endIcon = null,
                    placeholder = "Email Address",
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ) { email ->
                    emailInputState = email
                }

                //  full name
                SignUpFullNameTextField(
                    startIcon = Icons.Outlined.Person4,
                    endIcon = null,
                    placeholder = "Full Name",
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ) { fullName ->
                    fullNameInputState = fullName
                }

                //  password
                SignUpPasswordTextField(
                    startIcon = Icons.Outlined.Key,
                    placeholder = "New Password",
                    imeAction = ImeAction.Next
                ) { pass ->
                    newPassInputState = pass
                }

                //  confirm password
                SignUpConfirmPasswordTextField(
                    startIcon = Icons.Outlined.Key,
                    placeholder = "Confirm Password",
                    imeAction = ImeAction.Done
                ) { confirmPass ->
                    confirmPassInputState = confirmPass
                }

                //  login button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onPrimary),
                    contentAlignment = Alignment.Center
                ) {

                    Button(
                        onClick = {
                            //   create account
                            createAccount(
                                auth = auth,
                                context = context,
                                navHostController = navHostController,
                                email = emailInputState,
                                password = newPassInputState
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            disabledContainerColor = MaterialTheme.colorScheme.onSecondary.copy(
                                alpha = 0.3f
                            ),
                            disabledContentColor = Color.White.copy(alpha = 0.3f)
                        ),
                        enabled = true
                    ) {
                        Text(
                            text = "Create Account",
                            fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                            color = Color.White
                        )
                    }

                }

            }

        }

    }
}

//  email input textfield
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.SignUpEmailTextField(
    startIcon: ImageVector?,
    endIcon: ImageVector?,
    placeholder: String,
    imeAction: ImeAction,
    keyboardType: KeyboardType,
    onInputFieldChanged: (input: String) -> Unit
) {

    var emailTextFieldState by remember {
        mutableStateOf("")
    }

    TextField(
        value = emailTextFieldState,
        onValueChange = {
            emailTextFieldState = it
            onInputFieldChanged(emailTextFieldState)
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
            if (endIcon != null) {
                Icon(
                    imageVector = endIcon,
                    contentDescription = "Trailing Icon",
                    tint = Color.LightGray.copy(alpha = 0.9f)
                )
            }
        },
        placeholder = {
            Text(text = placeholder)
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Start),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.LightGray.copy(alpha = 0.5f),
            focusedIndicatorColor = MaterialTheme.colorScheme.primary
        )
    )
}

//  fullname input textfield
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.SignUpFullNameTextField(
    startIcon: ImageVector?,
    endIcon: ImageVector?,
    placeholder: String,
    imeAction: ImeAction,
    keyboardType: KeyboardType,
    onInputFieldChanged: (input: String) -> Unit
) {

    var fullNameTextFieldState by remember {
        mutableStateOf("")
    }

    TextField(
        value = fullNameTextFieldState,
        onValueChange = {
            fullNameTextFieldState = it
            onInputFieldChanged(fullNameTextFieldState)
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
            if (endIcon != null) {
                Icon(
                    imageVector = endIcon,
                    contentDescription = "Trailing Icon",
                    tint = Color.LightGray.copy(alpha = 0.9f)
                )
            }
        },
        placeholder = {
            Text(text = placeholder)
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.None,
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.Start),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.onPrimary,
            cursorColor = MaterialTheme.colorScheme.primary,
            unfocusedIndicatorColor = Color.LightGray.copy(alpha = 0.5f),
            focusedIndicatorColor = MaterialTheme.colorScheme.primary
        )
    )
}

//  password input textfield
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.SignUpPasswordTextField(
    startIcon: ImageVector?,
    placeholder: String,
    imeAction: ImeAction,
    onInputFieldChanged: (input: String) -> Unit
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
            onInputFieldChanged(passValueState)
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

//  password input textfield
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.SignUpConfirmPasswordTextField(
    startIcon: ImageVector?,
    placeholder: String,
    imeAction: ImeAction,
    onInputFieldChanged: (input: String) -> Unit
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
            onInputFieldChanged(passValueState)
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

@Preview
@Composable
fun SignUpPrev() {
    SignUpScreen(navHostController = rememberNavController())
}