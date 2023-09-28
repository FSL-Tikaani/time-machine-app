package com.tikaani.newtimemachine

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tikaani.newtimemachine.data.models.MiniCardModel
import com.tikaani.newtimemachine.ui.screens.city.CityScreen
import com.tikaani.newtimemachine.ui.screens.city_history.CityHistoryScreen
import com.tikaani.newtimemachine.ui.screens.city_selection.CitySelectionScreen
import com.tikaani.newtimemachine.ui.screens.hello.HelloScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val mainNavController = rememberNavController()
            val systemUiController = rememberSystemUiController()

            NavHost(navController = mainNavController, startDestination = "hello_screen"){

                composable("hello_screen"){
                    HelloScreen(
                        navController = mainNavController
                    )
                }

                composable("city_selection_screen"){
                    CitySelectionScreen(
                        navController = mainNavController
                    )
                }

                composable("city_screen/{cityId}"){ backStackEntry ->
                    CityScreen(
                        cityId = backStackEntry.arguments?.getString("cityId"),
                        navController = mainNavController
                    )
                }

                composable("city_history/{cityId}"){ backStackEntry ->
                    CityHistoryScreen(
                        id = backStackEntry.arguments?.getString("cityId")!!,
                        navController = mainNavController,
                    )
                }


                systemUiController.setStatusBarColor(
                    color = Color.Black,
                    darkIcons = false
                )
            }
        }
    }
}
