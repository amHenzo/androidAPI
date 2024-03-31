package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme
import org.json.JSONObject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var datas : Datas
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}
private var viewModel : Datas = Datas()

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(){
        ChoixIP()
        DetailsIP()
    }
}

@Composable
fun DetailsIP() {
    Column {
        val  test = viewModel.getJsondata().observeAsState()
        //var jsonf = JSONObject(test.value?:"{}")
        Text(text= test.value.toString())
    }
}

@Composable
fun ChoixIP() {
    Row(){
        var text by remember { mutableStateOf("https://dev-restandroid.users.info.unicaen.fr/api/adherents") }

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("adresse IP") }
        )
        IconButton(onClick = { Clicked(text) }) {
            Icon(Icons.Filled.Send, contentDescription = "Favorite")
        }
    }
}


fun Clicked(text: String) {
    viewModel.setadresseIP(text)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}