package com.tikaani.newtimemachine.ui.screens.city

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.tikaani.newtimemachine.R
import com.tikaani.newtimemachine.data.models.CityModel
import com.tikaani.newtimemachine.data.montserratFont
import com.tikaani.newtimemachine.ui.SimpleButton
import com.tikaani.newtimemachine.ui.screens.loading.LoadingScreen
import com.tikaani.timemachine.ui.theme.TimeMachineTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CityScreen(cityViewModel: CityViewModel = viewModel(),
               cityId: String?, navController: NavController) {

    val cityUiState by cityViewModel.uiState.collectAsState()

    LaunchedEffect(true) {
        cityViewModel.getCity(cityId!!)
    }

    val uriHandler = LocalUriHandler.current

    if (!cityUiState.isLoading && cityUiState.city != CityModel()){
        TimeMachineTheme {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                color = Color(0xFF171515)
            ) {
                Column {
                    val arrPhotos = cityUiState.city.arrPhotosCity
                    val pagerState = rememberPagerState(
                        initialPage = 0,
                        initialPageOffsetFraction = 0f
                    ) {
                        arrPhotos.size
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        HorizontalPager(
                            modifier = Modifier
                                .fillMaxSize(),
                            state = pagerState,
                        ) {
                            AsyncImage(
                                model = arrPhotos[it],
                                contentDescription = "image description",
                                contentScale = ContentScale.FillBounds,
                            )
                        }

                        Row(
                            modifier = Modifier.padding(bottom = 10.dp)
                        ) {
                            repeat(arrPhotos.size){
                                Canvas(modifier = Modifier.size(10.dp)){
                                    if (pagerState.currentPage == it){
                                        drawCircle(Color.White)
                                    }else{
                                        drawCircle(Color.Gray)
                                    }
                                }
                                Spacer(modifier = Modifier.padding(end = 5.dp))
                            }
                        }
                    }



                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = cityUiState.city.cityName,
                        style = TextStyle(
                            fontSize = 34.34.sp,
                            fontFamily = montserratFont(),
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                            letterSpacing = 6.87.sp,
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Столица России, город федерального значения, административный центр Центрального федерального округа и центр Московской области, в состав которой не входит. Крупнейший по численности населения город России и её субъект - 13104177 человек, самый населённый из городов, полностью расположенных в Европе, занимает 22-е место среди городов мира по численности населения, крупнейший русскоязычный город в мире. Центр Московской городской агломерации. Самый крупный город Европы по площади.",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = montserratFont(),
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        ),
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )

                    Spacer(modifier = Modifier.height(22.dp))



                    cityUiState.propertiesCity.forEach {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 1.dp)
                                .height(50.dp)
                                .clickable {
                                    uriHandler.openUri(it.src)
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,

                            ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Image(
                                    painter = painterResource(id = it.resImg),
                                    contentDescription = "desc",
                                    contentScale = ContentScale.Fit
                                )

                                Spacer(modifier = Modifier.width(24.dp))

                                Text(
                                    text = it.text,
                                    style = TextStyle(
                                        fontSize = 18.sp,
                                        fontFamily = montserratFont(),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFFFFFFFF),
                                    )
                                )

                                Spacer(modifier = Modifier.weight(1f))


                                Image(
                                    painter = painterResource(id = R.drawable.icon_arrow),
                                    contentDescription = "image description",
                                    contentScale = ContentScale.Fit,
                                    modifier = Modifier.size(40.dp),
                                )
                            }

                            Spacer(modifier = Modifier.weight(1f))

                            Divider(color = Color.Gray)
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))

                    SimpleButton(
                        text = "Начать путешествие",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp)
                            .padding(20.dp, 0.dp),
                        onClick = {
                            navController.navigate("city_history/${cityId}")
                        }
                    )

                    Spacer(modifier = Modifier.height(30.dp))

                }
            }
        }
    }else if(!cityUiState.isLoading && cityUiState.city == CityModel()){
        // Произошла ошибка
        Toast.makeText(navController.context, "Ошибка при получении данных! Повторите попытку позже!", Toast.LENGTH_SHORT).show()
        navController.navigate("hello_screen")
    }else{
        // Просто грузится
        LoadingScreen()
    }
}