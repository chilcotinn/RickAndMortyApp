package com.chilcotin.rickandmortyapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.chilcotin.rickandmortyapp.R

val Typography = Typography(
    labelLarge = TextStyle(
        fontFamily = FontFamily(
            Font(
                R.font.sf_ui_text,
                style = FontStyle.Normal
            )
        ),
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 25.sp,
        color = Color.Black
    ),

    labelSmall = TextStyle(
        fontFamily = FontFamily(
            Font(
                R.font.sf_ui_text,
                style = FontStyle.Normal
            )
        ),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        color = Color.Black
    )
)