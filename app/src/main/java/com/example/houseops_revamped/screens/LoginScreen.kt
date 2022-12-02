package com.example.houseops_revamped.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.houseops_revamped.R
import com.example.houseops_revamped.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navHostController: NavHostController
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
            keyboardType = KeyboardType.Email
        )

        //  password
        PasswordTextField(
            startIcon = Icons.Outlined.Lock,
            placeholder = "Password",
            imeAction = ImeAction.Done
        )

        //  forgot password text
        Text(
            text = "Forgot Password?",
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { }
        )

        //  login button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onPrimary),
            contentAlignment = Alignment.Center
        ) {

            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    disabledContainerColor = MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.3f),
                    disabledContentColor = Color.White.copy(alpha = 0.3f)
                ),
                enabled = true
            ) {
                Text(
                    text = "Login",
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    color = Color.White
                )
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

//  email input textfield
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.CustomTextField(
    startIcon: ImageVector?,
    endIcon: ImageVector?,
    placeholder: String,
    imeAction: ImeAction,
    keyboardType: KeyboardType
) {

    var emailTextFieldState by remember {
        mutableStateOf("")
    }

    TextField(
        value = emailTextFieldState,
        onValueChange = { emailTextFieldState = it },
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
fun ColumnScope.PasswordTextField(
    startIcon: ImageVector?,
    placeholder: String,
    imeAction: ImeAction
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
        onValueChange = { passValueState = it },
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

//  ----------------- previews --------------------
//  dark mode preview
@Preview(
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun LoginScreenPrevDarkMode() {
    LoginScreen(navHostController = rememberNavController())
}

//  light mode preview
@Preview(
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun LoginScreenPrevLightMode() {
    LoginScreen(navHostController = rememberNavController())
}





































