package com.example.searchimageapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.searchimageapp.ui.components.HomeContent
import com.example.searchimageapp.ui.theme.SearchImageAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SearchImageAppTheme {
                MyApp {
                    HomeContent()
                }

            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    return content()
}
