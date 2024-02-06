package com.nobosoftware.brewies.ui



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           Main()
        }
    }
}

@Composable
fun Main() {
}

@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    Main()
}



