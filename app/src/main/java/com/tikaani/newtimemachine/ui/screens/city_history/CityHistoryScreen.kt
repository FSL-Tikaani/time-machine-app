package com.tikaani.newtimemachine.ui.screens.city_history

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tikaani.newtimemachine.data.models.HistoryOfCenturyModel
import com.tikaani.newtimemachine.data.montserratFont
import com.tikaani.newtimemachine.ui.screens.loading.LoadingScreen
import com.tikaani.timemachine.ui.theme.TimeMachineTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CityHistoryScreen(
    cityHistoryViewModel: CityHistoryViewModel = viewModel(),
    navController: NavController,
    id: String,
){

    val cityHistoryUiState by cityHistoryViewModel.uiState.collectAsState()

    SideEffect {
        cityHistoryViewModel.getCentury(id)
    }

    if(cityHistoryUiState.isLoading){
        LoadingScreen()
    }else if(!cityHistoryUiState.isLoading && cityHistoryUiState.arrCentury == listOf<HistoryOfCenturyModel>()){
        Toast.makeText(navController.context, "Ошибка при получении данных! Повторите попытку позже!", Toast.LENGTH_SHORT).show()
        navController.navigate("hello_screen")
    }else{

        val pagerState = rememberPagerState(
            initialPage = 0,
            initialPageOffsetFraction = 0f,
            pageCount = {cityHistoryUiState.arrCentury.size},
        )

        var scope = rememberCoroutineScope()



        TimeMachineTheme {

            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = Color.Black,
                    darkIcons = false
                )
            }

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                color = Color(0xFF171515),
            ) {
                if (!cityHistoryUiState.isLoading) {
                    Log.d("cityHistoryUiState", cityHistoryUiState.arrCentury.toString())
                    Column(modifier = Modifier
                        .fillMaxSize()) {
                        // TabLayout
                        Column(modifier = Modifier.fillMaxWidth()) {
                            ScrollableTabRow(
                                selectedTabIndex = pagerState.currentPage,
                                containerColor = Color(0xFF171515),
                                contentColor = Color.White,
                            ) {
                                cityHistoryUiState.arrCentury.forEachIndexed { index, century ->
                                    Tab(
                                        text = {
                                            Text(text = century.title,
                                                fontWeight = FontWeight.ExtraBold,
                                                fontSize = 16.sp)
                                               },
                                        selected = pagerState.currentPage == index,
                                        onClick = {
                                            scope.launch {
                                                pagerState.animateScrollToPage(index)
                                            }
                                        }
                                    )
                                }
                            }
                        }
                        // Main
                        Spacer(modifier = Modifier.height(16.dp))

                        Column(modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .verticalScroll(rememberScrollState())) {
                            HorizontalPager(
                                state = pagerState,
                            ) { index ->
                                Column(modifier = Modifier
                                    .fillMaxSize()
                                    .weight(1f)) {
                                    Column {
                                        val arrPhotos = cityHistoryUiState.arrCentury[pagerState.currentPage].arrSrcImg.toMutableList()
                                        Log.d("arrPhotos", arrPhotos.size.toString())
                                        val galleryPagerState = rememberPagerState(
                                            initialPage = 0,
                                            initialPageOffsetFraction = 0f,
                                            pageCount = {arrPhotos.size}
                                        )
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .height(170.dp)
                                                .clip(RoundedCornerShape(16.dp)),
                                            contentAlignment = Alignment.BottomCenter
                                        ) {
                                            HorizontalPager(
                                                modifier = Modifier
                                                    .fillMaxSize(),
                                                state = galleryPagerState,
                                            ) {
                                                AsyncImage(
                                                    model = arrPhotos[it],
                                                    contentDescription = "image description",
                                                    contentScale = ContentScale.Crop,
                                                    modifier = Modifier.fillMaxSize(),
                                                )
                                            }

                                            Row(
                                                modifier = Modifier.padding(bottom = 10.dp)
                                            ) {
                                                repeat(arrPhotos.size){
                                                    Canvas(modifier = Modifier.size(10.dp)){
                                                        if (galleryPagerState.currentPage == it){
                                                            drawCircle(Color.White)
                                                        }else{
                                                            drawCircle(Color.Gray)
                                                        }
                                                    }
                                                    Spacer(modifier = Modifier.padding(end = 5.dp))
                                                }
                                            }
                                        }


                                    Spacer(modifier = Modifier.height(20.dp))

                                    Text(
                                        text = cityHistoryUiState.arrCentury[pagerState.currentPage].textDescription,
                                        style = TextStyle(
                                            fontSize = 14.sp,
                                            fontFamily = montserratFont()
                                        ),
                                        fontWeight = FontWeight(700),
                                        color = Color(0xFFFFFFFF),
                                        modifier = Modifier.padding(horizontal = 15.dp)
                                    )
                                }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}