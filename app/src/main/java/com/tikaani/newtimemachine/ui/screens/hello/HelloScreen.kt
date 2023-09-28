package com.tikaani.newtimemachine.ui.screens.hello

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tikaani.newtimemachine.R
import com.tikaani.newtimemachine.data.montserratFont
import com.tikaani.newtimemachine.ui.SimpleButton
import com.tikaani.timemachine.ui.theme.TimeMachineTheme

@Composable
fun HelloScreen(navController: NavController){
    TimeMachineTheme {

        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setStatusBarColor(
                color = Color.Black,
                darkIcons = false
            )
        }

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFF171515)
        ) {
            Column {
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Добро пожаловать в\n" +
                            "приложение",
                    style = TextStyle(
                        fontSize = 32.sp,
                        fontFamily = montserratFont(),
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFFFFFFFF),
                        ),
                    lineHeight = 45.sp,
                    modifier = Modifier.padding(10.dp, 0.dp),
                )
                Text(
                    text = "Машина Времени!",
                    style = TextStyle(
                        fontSize = 36.sp,
                        fontFamily = montserratFont(),
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFFFFFFFF),
                    ),
                    modifier = Modifier.padding(10.dp, 0.dp)
                )
                Spacer(modifier = Modifier.weight(1f))

                Image(
                    painter = painterResource(id = R.drawable.time_machine),
                    contentDescription = "TimeMachine",
                )

                Spacer(modifier = Modifier.weight(1f))

                SimpleButton(
                    text = "Продолжить",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(45.dp)
                        .padding(20.dp, 0.dp)
                ) {
                    navController.navigate("city_selection_screen")
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}