package com.example.footballmanager

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.footballmanager.network.FootballApi
import com.example.footballmanager.ui.theme.FootballManagerTheme
import kotlinx.coroutines.runBlocking
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var name = "czekaj..."

        runBlocking {
            try {
                val listResult =
                    FootballApi.retrofitService.getFixtures(1)
                name = listResult
                Log.d("odpowiedzz", listResult)
            }catch (e:IOException){
                Log.d("ErrorMessageMainActivity", "onCreate: $e")
                name = "Error: $e"
            }
        }

        enableEdgeToEdge()
        setContent {
            FootballManagerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = name,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FootballManagerTheme {
        Greeting("Android")
    }
}