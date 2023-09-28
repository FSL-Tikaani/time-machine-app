package com.tikaani.newtimemachine.ui.screens.loading

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tikaani.newtimemachine.data.montserratFont
import com.tikaani.timemachine.ui.theme.TimeMachineTheme


@Composable
fun LoadingScreen(){
    TimeMachineTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF171515),
        ) {
            Row(modifier = Modifier.fillMaxWidth().height(50.dp),
                verticalAlignment = Alignment.CenterVertically){
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Загрузка",
                    style = TextStyle(
                        fontSize = 34.34.sp,
                        fontFamily = montserratFont(),
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        letterSpacing = 6.87.sp,
                    ),
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.weight(1f))
                CircularProgressIndicator(
                    modifier = Modifier.width(34.dp),
                    color = Color.White,
                )
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
