package com.nobosoftware.brewies.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class OnboardingViewModel : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // StateFlows to observe the state in the UI
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState = _uiState.asStateFlow()

    // Function to handle email/password sign up
    fun signUpWithEmail(email: String, password: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                auth.createUserWithEmailAndPassword(email, password).await()
                _uiState.value = UiState.Success
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "An unknown error occurred")
            }
        }
    }

    // Function to handle email/password login
    fun loginWithEmail(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).await()
                onSuccess()
            } catch (e: Exception) {
                onError(e.localizedMessage ?: "Error occurred")
            }
        }
    }

    // Function to handle forgot password
    fun sendPasswordResetEmail(email: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).await()
                onSuccess()
            } catch (e: Exception) {
                onError(e.localizedMessage ?: "Error occurred")
            }
        }
    }

    // Function to handle sign in with Google
    fun handleGoogleSignIn(idToken: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val credential = GoogleAuthProvider.getCredential(idToken, null)
                auth.signInWithCredential(credential).await()
                onSuccess()
            } catch (e: Exception) {
                onError(e.localizedMessage ?: "Error occurred")
            }
        }
    }

    fun handleFacebookSignIn(token: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val credential = FacebookAuthProvider.getCredential(token)
                auth.signInWithCredential(credential).await()
                onSuccess()
            } catch (e: Exception) {
                onError(e.localizedMessage ?: "Error occurred")
            }
        }
    }


    // UI state representation
    sealed class UiState {
        data object Idle : UiState()
        data object Loading : UiState()
        data object Success : UiState()
        data class Error(val message: String) : UiState()
    }
}
