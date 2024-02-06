package com.nobosoftware.brewies.ui.custom

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

// Helper composable for social media buttons
@Composable
fun SocialMediaButton(
    text: String,
    iconId: Int,
    onClick: () -> Unit,
    backgroundColor: Color,
    contentColor: Color
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        modifier = Modifier
            .height(50.dp)
//            .width(200.dp) // You can remove this if you want the button to fill the available width
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = "$text login",
            tint = Color.Unspecified, // This will show the icon in its original colors
            modifier = Modifier.height(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text, color = contentColor)
    }
}
