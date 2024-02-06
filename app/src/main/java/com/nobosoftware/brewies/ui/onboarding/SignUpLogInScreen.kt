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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
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
import com.nobosoftware.brewies.R
import com.nobosoftware.brewies.ui.custom.SocialMediaButton

@Composable
fun SignUpLogInScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var agreedToTerms by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp), // Matched padding with the login view
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(60.dp)) // Adjust this value as needed

        Text("WELCOME", style = MaterialTheme.typography.h5)
        Text("Sign up to get started.", style = MaterialTheme.typography.subtitle1)

        Spacer(modifier = Modifier.height(32.dp))

        // Email input field
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email Icon") },
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
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock Icon") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = agreedToTerms,
                onCheckedChange = { agreedToTerms = it },
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
            )
            Text(
                "I agree to the Terms & Conditions",
                modifier = Modifier.clickable(onClick = { agreedToTerms = !agreedToTerms })
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Sign Up button
        Button(
            onClick = { /* TODO: Handle sign up */ },
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
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpLogInScreenPreview() {
    MaterialTheme {
        SignUpLogInScreen()
    }
}
