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
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
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
fun LoginScreen() {
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
            value = "",
            onValueChange = {},
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
            value = "",
            onValueChange = {},
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
                .clickable { /* TODO: Handle forgot password */ }
                .padding(end = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Log in button
        Button(
            onClick = { /* TODO: Add logic */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp), // Set the height of the button
            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primary)
        ) {
            Text("LOG IN", color = Color.White)
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
                modifier = Modifier.clickable { /* TODO: Navigate to Sign Up */ },
                color = MaterialTheme.colors.secondary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    MaterialTheme {
        LoginScreen()
    }
}
