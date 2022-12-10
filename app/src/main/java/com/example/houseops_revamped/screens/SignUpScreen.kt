package com.example.houseops_revamped.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.houseops_revamped.R
import com.example.houseops_revamped.custom_components.BackPressTopAppBar
import com.example.houseops_revamped.models.Constants.AUTHENTICATION_ROUTE
import com.example.houseops_revamped.network.createAccount
import com.example.houseops_revamped.network.createUserCollection
import com.example.houseops_revamped.network.uploadImageToFirestore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        val scope = rememberCoroutineScope()

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

        //  Image Picker
        var imageUri by remember {
            mutableStateOf<Uri?>(null)
        }

        //  launcher for gallery activity
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->

            imageUri = uri
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
                        .size(100.dp)
                        .background(MaterialTheme.colorScheme.onSecondary)
                        .align(Alignment.CenterHorizontally)
                        .clickable {
                            launcher.launch("image/*")
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    if (imageUri == null) {
                        Icon(
                            imageVector = Icons.Outlined.ImageSearch,
                            contentDescription = "Image Picker Icon",
                            modifier = Modifier
                                .size(60.dp)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        //  pick image text
                        Text(
                            text = "Pick Image",
                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                            fontWeight = MaterialTheme.typography.bodySmall.fontWeight
                        )
                    } else {
                        imageUri?.let {
                            Image(
                                modifier = Modifier
                                    .fillMaxSize(),
                                painter = rememberAsyncImagePainter(it),
                                contentDescription = "User Profile Image",
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

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

                //  terms and conditions text
                Text(
                    modifier = Modifier
                        .align(Alignment.Start),
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f)
                            )
                        ) {
                            append(
                                "By signing up, you agree to our "
                            )
                        }

                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            append(
                                "Terms & Conditions "
                            )
                        }

                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.9f)
                            )
                        ) {
                            append(
                                "and "
                            )
                        }
                        withStyle(
                            style = SpanStyle(
                                color = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            append(
                                "Privacy Policy."
                            )
                        }
                    },
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontWeight = MaterialTheme.typography.bodySmall.fontWeight
                )

                //  Signup button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onPrimary),
                    contentAlignment = Alignment.Center
                ) {

                    Button(
                        onClick = {
                            validateDetails(
                                scope = scope,
                                navHostController = navHostController,
                                context = context,
                                auth = auth,
                                imageUri = if (imageUri == null) null else imageUri,
                                email = emailInputState,
                                name = fullNameInputState,
                                newPass = newPassInputState,
                                confirmPass = confirmPassInputState
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

fun validateDetails(
    scope: CoroutineScope,
    navHostController: NavHostController,
    context: Context,
    auth: FirebaseAuth,
    imageUri: Uri?,
    email: String,
    name: String,
    newPass: String,
    confirmPass: String,
) {

    //  validate all details are correct
    val uri = imageUri.toString()

    if (email.isBlank())
        Toast.makeText(context, "Email cannot be blank!", Toast.LENGTH_SHORT).show()
    else if (name.isBlank())
        Toast.makeText(context, "Input your full name", Toast.LENGTH_SHORT).show()
    else if (newPass != confirmPass)
        Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
    else {

        //  create the user account
        scope.launch(Dispatchers.IO) {
            //   create account
            createAccount(
                auth = auth,
                context = context,
                navHostController = navHostController,
                email = email,
                password = newPass
            )
            //  create user in database
            createUserCollection(
                userName = name,
                userEmail = email,
                userImageUri = uri as String,
                userPassword = newPass,
                userIsCaretaker = false,
                userExtraDetails = ArrayList(),
                userHasMadeRequest = false
            )
            // upload image to firestore
            uploadImageToFirestore(
                imageUri = imageUri!!,
                email = email,
                context = context
            )
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