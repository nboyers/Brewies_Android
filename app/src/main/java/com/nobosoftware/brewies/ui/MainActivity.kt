package com.nobosoftware.brewies.ui



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nobosoftware.brewies.ui.onboarding.ForgotPasswordScreen
import com.nobosoftware.brewies.ui.onboarding.LoginScreen
import com.nobosoftware.brewies.ui.onboarding.SignUpLogInScreen
import com.nobosoftware.brewies.viewmodel.OnboardingViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // A surface container using the 'background' color from the theme
            Surface(color = MaterialTheme.colors.background) {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val onboardingViewModel = viewModel<OnboardingViewModel>()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController, onboardingViewModel)
        }

        composable("forgotPassword") {
            ForgotPasswordScreen(
                onboardingViewModel = viewModel(), // ViewModel instance
                navController = navController, // NavController instance
                onNavigateBackToLogin = { navController.navigate("login") } // Navigation lambda
            )
        }

        composable("signUp") {
            SignUpLogInScreen(onboardingViewModel, navController) {
                // Define what happens when signed in successfully
                // For example: navController.navigate("mainScreen")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    AppNavigation()
}



