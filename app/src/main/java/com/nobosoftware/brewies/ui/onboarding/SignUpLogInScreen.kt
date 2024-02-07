package com.nobosoftware.brewies.ui.onboarding

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nobosoftware.brewies.R
import com.nobosoftware.brewies.ui.custom.SocialMediaButton
import com.nobosoftware.brewies.viewmodel.OnboardingViewModel

@Composable
fun SignUpLogInScreen(
    onboardingViewModel: OnboardingViewModel,
    navController: NavController,
    onSignedIn: () -> Unit // Callback when signed in
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var agreedToTerms by remember { mutableStateOf(false) }

    // Observe UI state changes
    val uiState = onboardingViewModel.uiState.collectAsState().value

    LaunchedEffect(uiState) {
        if (uiState is OnboardingViewModel.UiState.Success) {
            onSignedIn() // Navigate to the main part of the app
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
         .padding(top = 8.dp), // Adjust this padding to move the icon closer to the top
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .size(24.dp)
                    .clickable { navController.popBackStack() })
        }
        Spacer(modifier = Modifier.height(50.dp))

        Text("WELCOME", style = MaterialTheme.typography.h5)
        Text("Sign up to get started.", style = MaterialTheme.typography.subtitle1)

        Spacer(modifier = Modifier.height(48.dp))

        // Email input field
        OutlinedTextField(value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password input field
        OutlinedTextField(value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock Icon") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = agreedToTerms,
                onCheckedChange = { agreedToTerms = it },
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
            )
            Text(
                "I agree to the Terms & Conditions", //TODO Add a link to a T&C page
                modifier = Modifier.clickable(onClick = { agreedToTerms = !agreedToTerms })
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up button
        Button(
            onClick = {
                if (agreedToTerms) {
                    onboardingViewModel.signUpWithEmail(email, password)
                    navController.navigate("mainScreen")
                } else {
                    // Handle the case where terms are not agreed
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text("Sign Up", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text("or", color = Color.Gray)

        Spacer(modifier = Modifier.height(8.dp))

        // Sign up with Google button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp), // Adjust horizontal padding as needed
            horizontalArrangement = Arrangement.Center
        ) {
            // Facebook login button
            // Handle Facebook login
            SocialMediaButton(
                text = "Facebook", iconId = R.drawable.ic_facebook, onClick = {
//TODO make this function work: onboardingViewModel.handleFacebookSignIn("")
                }, backgroundColor = Color(0xFF1877F2), contentColor = Color.White
            )

            // Handle Google login
            SocialMediaButton(
                text = "Google", iconId = R.drawable.ic_google, onClick = {
                    //TODO make this function work: onboardingViewModel.handleGoogleSignIn {}
                }, backgroundColor = Color(0xFFD61010), contentColor = Color.White
            )
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
//    Spacer(modifier = Modifier.height(75.dp))
}


@Preview(showBackground = true)
@Composable
fun SignUpLogInScreenPreview() {
    MaterialTheme {
        // Provide dummy ViewModel and NavController for preview
        val dummyNavController = rememberNavController()
        val dummyViewModel = viewModel<OnboardingViewModel>()

        SignUpLogInScreen(
            dummyViewModel, dummyNavController
        ) { /* Dummy implementation for preview */ }
    }
}
