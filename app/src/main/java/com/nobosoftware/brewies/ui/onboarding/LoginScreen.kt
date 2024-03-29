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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
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
fun LoginScreen(navController: NavController, onboardingViewModel: OnboardingViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            title = { Text(text = "Error") },
            text = { Text(text = errorMessage) },
            confirmButton = {
                Button(onClick = { showErrorDialog = false }) {
                    Text("OK")
                }
            }
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp)) // Adjust this value as needed

        Text("Welcome back!", style = MaterialTheme.typography.h5)
        Text(
            "Log in to your existent account of Brewies",
            style = MaterialTheme.typography.subtitle1
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Email input field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Email Icon"
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password input field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Password Icon"
                )
            },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Forgot Password?",
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { navController.navigate("forgotPassword") }
                .padding(end = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

// Log in button
        Button(
            onClick = {
                isLoading = true
                onboardingViewModel.loginWithEmail(email, password, onSuccess = {
                    isLoading = false
//                        onNavigateToMainApp()
                }, onError = { error ->
                    isLoading = false
                    errorMessage = error
                    showErrorDialog = true
                })
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp), // Set the height of the button
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color.White)
            } else {
                Text("LOG IN", color = Color.White)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Or connect using")

        Spacer(modifier = Modifier.height(24.dp))

// Social Media Login buttons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp), // Adjust horizontal padding as needed
            horizontalArrangement = Arrangement.Center
        ) {
            // Facebook login button
            SocialMediaButton(
                text = "Facebook",
                iconId = R.drawable.ic_facebook, // Make sure you have this drawable in your resources
                onClick = { /* TODO: Handle Facebook login */ },
                backgroundColor = Color(0xFF1877F2), // Facebook Blue color
                contentColor = Color.White
            )

            Spacer(modifier = Modifier.width(16.dp)) // This Spacer adds space between the buttons

            // Google login button
            SocialMediaButton(
                text = "Google",
                iconId = R.drawable.ic_google, // Make sure you have this drawable in your resources
                onClick = { /* TODO: Handle Google login */ },
                backgroundColor = Color(0xFFD61010), // Google Red color
                contentColor = Color.White
            )
        }



        Spacer(modifier = Modifier.height(32.dp))

        // Sign up text
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text("Don't have an account? ", color = Color.Gray)
            Text(
                "Sign Up",
                modifier = Modifier
                    .clickable { navController.navigate("signUp") },
                color = MaterialTheme.colors.secondary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    MaterialTheme {
        // Since it's a preview, we are passing dummy NavController and ViewModel.
        // In real usage, these should be the actual NavController and ViewModel instances.
        val dummyNavController = rememberNavController()
        val dummyViewModel = viewModel<OnboardingViewModel>(factory = null) // Create a dummy ViewModel

        LoginScreen(dummyNavController, dummyViewModel)
    }
}
