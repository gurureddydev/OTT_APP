package com.guru.ott_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.guru.ott_app.api.RetrofitInstance
import com.guru.ott_app.components.NoInternetUI
import com.guru.ott_app.network.isInternetAvailable
import com.guru.ott_app.repository.MovieRepository
import com.guru.ott_app.screens.HomeScreen
import com.guru.ott_app.ui.theme.Ott_AppTheme
import com.guru.ott_app.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            Ott_AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CheckInternetConnection()
                }
            }
        }
    }
}

@Composable
fun CheckInternetConnection() {
    val context = LocalContext.current

    val isInternetAvailable by remember { mutableStateOf(isInternetAvailable(context = context)) }

    if (isInternetAvailable) {
        val viewModel = HomeViewModel(MovieRepository(RetrofitInstance.getApiService()))
        HomeScreen(viewModel)
    } else {
        NoInternetUI(message = "No Internet Connection","Please check your internet connection and try again.") {
            // Handle retry action here
        }
    }
}
