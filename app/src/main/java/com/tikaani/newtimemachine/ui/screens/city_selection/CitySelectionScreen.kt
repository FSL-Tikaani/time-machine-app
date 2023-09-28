package com.tikaani.newtimemachine.ui.screens.city_selection

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.tikaani.newtimemachine.R
import com.tikaani.newtimemachine.data.models.MiniCardModel
import com.tikaani.newtimemachine.data.montserratFont
import com.tikaani.newtimemachine.ui.screens.loading.LoadingScreen
import com.tikaani.timemachine.ui.theme.TimeMachineTheme


@Composable
fun CitySelectionScreen(
    citySelectionViewModel: CitySelectionViewModel = viewModel(),
    navController: NavController
){
    val citySelectionUiState by citySelectionViewModel.uiState.collectAsState()

    if(citySelectionUiState.isLoading){
        LoadingScreen()
    }else if (!citySelectionUiState.isLoading && citySelectionUiState.miniCards == listOf<MiniCardModel>()) {
        Toast.makeText(navController.context, "Ошибка при получении данных! Повторите попытку позже!", Toast.LENGTH_SHORT).show()
        navController.navigate("hello_screen")
        
    }else{

        TimeMachineTheme {

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color(0xFF171515)
            ) {
                Column {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Выберите город \n" +
                                "для путешествия:",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontFamily = montserratFont(),
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFFFFF),
                        ),
                        lineHeight = 45.sp,
                        modifier = Modifier.padding(24.dp, 0.dp),
                    )

                    Spacer(modifier = Modifier.weight(1f))


                    citySelectionUiState.miniCards.forEach {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(115.dp)
                                .padding(24.dp, 0.dp, 24.dp, 32.dp)
                                .clip(RoundedCornerShape(18.dp))
                                .clickable {
                                    navController.navigate("city_screen/${it.id}")
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = it.srcImg,
                                contentDescription = it.id.toString(),
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )

                            Text(
                                text = it.nameCity,
                                style = TextStyle(
                                    fontSize = 34.34.sp,
                                    fontFamily = montserratFont(),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xF2FFFFFF),
                                    letterSpacing = 6.87.sp,
                                )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}