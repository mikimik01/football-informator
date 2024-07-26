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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.footballmanager.network.FixtureDataWrapper
import com.example.footballmanager.network.FootballApi
import com.example.footballmanager.ui.theme.FootballManagerTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var name = "Waiting..."

        val call = FootballApi.retrofitService.getFixtures(1)
        call.enqueue(object : Callback<FixtureDataWrapper> {
            override fun onResponse(call: Call<FixtureDataWrapper>, response: Response<FixtureDataWrapper>) {
                if(response.isSuccessful){
                    name = response.body()?.responseBody?.get(0)?.teams?.home?.name?: "null"
                    Toast.makeText(this@MainActivity, name, Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this@MainActivity, "Blad", Toast.LENGTH_SHORT).show()
                }
            }
            override fun onFailure(call: Call<FixtureDataWrapper>, t: Throwable) {
                Log.d("ErrorMessageMainActivity", "onCreate: $t")
                name = "Error: $t"
            }
        })

        enableEdgeToEdge()
        setContent {
            FootballManagerTheme {
                /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = name,
                        modifier = Modifier.padding(innerPadding)
                    )
                }*/

                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {

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