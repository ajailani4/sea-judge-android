package com.example.seajudge.ui.feature.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.seajudge.ui.theme.Grey
import com.example.seajudge.ui.theme.Primary
import com.example.seajudge.ui.theme.poppinsFamily
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.Lock
import compose.icons.evaicons.fill.Person
import compose.icons.evaicons.outline.Eye
import compose.icons.evaicons.outline.EyeOff

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val username = loginViewModel.username
    val onUsernameChanged = loginViewModel::onUsernameChanged
    val password = loginViewModel.password
    val onPasswordChanged = loginViewModel::onPasswordChanged
    val passwordVisibility = loginViewModel.passwordVisibility
    val onPasswordVisibilityChanged = loginViewModel::onPasswordVisibilityChanged

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(top = 70.dp),
            text = "Masuk",
            color = Primary,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = username,
            onValueChange = onUsernameChanged,
            leadingIcon = {
                Icon(
                    imageVector = EvaIcons.Fill.Person,
                    tint = Primary,
                    contentDescription = "Username icon"
                )
            },
            placeholder = {
                Text(
                    text = "Username",
                    color = Grey,
                    style = MaterialTheme.typography.subtitle1
                )
            },
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontFamily = poppinsFamily,
                fontSize = 15.sp
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = onPasswordChanged,
            leadingIcon = {
                Icon(
                    imageVector = EvaIcons.Fill.Lock,
                    tint = Primary,
                    contentDescription = "Password icon"
                )
            },
            placeholder = {
                Text(
                    text = "Password",
                    color = Grey,
                    style = MaterialTheme.typography.subtitle1
                )
            },
            trailingIcon = {
                IconButton(onClick = onPasswordVisibilityChanged) {
                    Icon(
                        imageVector = if (passwordVisibility) {
                            EvaIcons.Outline.Eye
                        } else {
                            EvaIcons.Outline.EyeOff
                        },
                        contentDescription = "Password visibility icon"
                    )
                }
            },
            singleLine = true,
            textStyle = TextStyle(
                color = Color.Black,
                fontFamily = poppinsFamily,
                fontSize = 15.sp
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = Primary),
            onClick = { /*TODO*/ }
        ) {
            Text(
                modifier = Modifier.padding(5.dp),
                text = "Masuk",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}