package com.example.seajudge.ui.feature.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.seajudge.R
import com.example.seajudge.ui.Screen
import com.example.seajudge.ui.common.component.FullSizeProgressBar
import com.example.seajudge.ui.theme.Grey
import com.example.seajudge.ui.theme.Primary
import com.example.seajudge.ui.theme.poppinsFamily
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.*
import compose.icons.evaicons.fill.Person
import compose.icons.evaicons.outline.Eye
import compose.icons.evaicons.outline.EyeOff
import compose.icons.evaicons.outline.Person
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = hiltViewModel()
) {
    val onEvent = registerViewModel::onEvent
    val registerState = registerViewModel.registerState
    val username = registerViewModel.username
    val onUsernameChanged = registerViewModel::onUsernameChanged
    val name = registerViewModel.name
    val onNameChanged = registerViewModel::onNameChanged
    val state = registerViewModel.state
    val onStateChanged = registerViewModel::onStateChanged
    val job = registerViewModel.job
    val onJobChanged = registerViewModel::onJobChanged
    val phoneNumber = registerViewModel.phoneNumber
    val onPhoneNumberChanged = registerViewModel::onPhoneNumberChanged
    val password = registerViewModel.password
    val onPasswordChanged = registerViewModel::onPasswordChanged
    val passwordVisibility = registerViewModel.passwordVisibility
    val onPasswordVisibilityChanged = registerViewModel::onPasswordVisibilityChanged

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.padding(top = 50.dp),
                    text = "Daftar Akun",
                    color = Primary,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h1
                )
                Spacer(modifier = Modifier.height(30.dp))
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
                    value = name,
                    onValueChange = onNameChanged,
                    leadingIcon = {
                        Icon(
                            imageVector = EvaIcons.Outline.Person,
                            tint = Primary,
                            contentDescription = "Name icon"
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Nama Lengkap",
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
                    value = state,
                    onValueChange = onStateChanged,
                    leadingIcon = {
                        Icon(
                            imageVector = EvaIcons.Fill.Pin,
                            tint = Primary,
                            contentDescription = "State icon"
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Asal Daerah",
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
                    value = job,
                    onValueChange = onJobChanged,
                    leadingIcon = {
                        Icon(
                            imageVector = EvaIcons.Fill.Briefcase,
                            tint = Primary,
                            contentDescription = "Job icon"
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Pekerjaan",
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
                    value = phoneNumber,
                    onValueChange = {
                        if (it.length <= 15) {
                            onPhoneNumberChanged(it)
                        }
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = EvaIcons.Fill.Phone,
                            tint = Primary,
                            contentDescription = "Phone number icon"
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Nomor Telepon",
                            color = Grey,
                            style = MaterialTheme.typography.subtitle1
                        )
                    },
                    singleLine = true,
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontFamily = poppinsFamily,
                        fontSize = 15.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
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
                    modifier = Modifier.fillMaxWidth(),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(backgroundColor = Primary),
                    enabled = registerState != RegisterState.Registering,
                    onClick = {
                        if (
                            username.isNotEmpty() && name.isNotEmpty() && state.isNotEmpty() &&
                            job.isNotEmpty() && phoneNumber.isNotEmpty() && password.isNotEmpty()
                        ) {
                            keyboardController?.hide()
                            onEvent(RegisterEvent.Submit)
                        } else {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar("Isi form dengan lengkap!")
                            }
                        }
                    }
                ) {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "Daftar",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        style = MaterialTheme.typography.subtitle1
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                ClickableText(
                    text = buildAnnotatedString {
                        append("Sudah punya akun? ")

                        withStyle(
                            style = SpanStyle(
                                color = Primary,
                                fontFamily = poppinsFamily,
                                fontSize = 15.sp
                            )
                        ) {
                            append("Masuk sini")
                        }
                    },
                    style = MaterialTheme.typography.subtitle1,
                    onClick = { navController.navigate(Screen.LoginScreen.route) }
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.img_wave),
                contentDescription = "Wave illustration"
            )
        }

        // Observe register state
        when (registerState) {
            is RegisterState.Idle -> {}

            is RegisterState.Registering -> {
                FullSizeProgressBar()
            }

            is RegisterState.Success -> {
                navController.navigate(Screen.DashboardScreen.route) {
                    launchSingleTop = true

                    popUpTo(Screen.OnboardingScreen.route) {
                        inclusive = true
                    }
                }
            }

            is RegisterState.Fail -> {
                LaunchedEffect(Unit) {
                    scope.launch {
                        registerState.message?.let { message ->
                            scaffoldState.snackbarHostState.showSnackbar(message)
                        }
                    }
                }

                onEvent(RegisterEvent.Idle)
            }

            is RegisterState.Error -> {
                LaunchedEffect(Unit) {
                    scope.launch {
                        registerState.message?.let { message ->
                            scaffoldState.snackbarHostState.showSnackbar(message)
                        }
                    }
                }

                onEvent(RegisterEvent.Idle)
            }
        }
    }
}