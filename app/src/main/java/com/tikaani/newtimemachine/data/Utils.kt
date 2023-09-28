package com.tikaani.newtimemachine.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight


@Composable
fun montserratFont() = FontFamily(
    font("montserrat_regular", FontWeight.Normal, FontStyle.Normal),
    font("montserrat_medium", FontWeight.Medium, FontStyle.Normal),
    font("montserrat_semibold", FontWeight.SemiBold, FontStyle.Normal),
    font("montserrat_bold", FontWeight.Bold, FontStyle.Normal)
)

@Composable
fun font(res: String, weight: FontWeight, style: FontStyle): Font {
    val context = LocalContext.current
    val id = context.resources.getIdentifier(res, "font", context.packageName)
    return Font(id, weight, style)
}