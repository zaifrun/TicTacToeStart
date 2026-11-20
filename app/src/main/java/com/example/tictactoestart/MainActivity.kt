package com.example.tictactoestart

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.component1
import androidx.core.graphics.component2
import androidx.core.graphics.component3
import androidx.core.graphics.component4
import com.example.tictactoestart.ui.theme.TicTacToeStartTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            TicTacToeStartTheme {

                val gameState = remember {
                    GameState().apply {
                        fields[5] = FELT.BOLLE
                        fields[6] = FELT.TOMT
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TableLayout(modifier = Modifier.padding(innerPadding),gameState,
                        clickFun = {
                            Log.d("felt clicked","felt nr "+it)
                            gameState.fields[it] = FELT.BOLLE
                        })

                }
            }
        }
    }
}

@Composable
fun TableLayout(modifier: Modifier = Modifier,state: GameState, clickFun : (Int) -> (Unit)) {
    Column(modifier = modifier.fillMaxSize().padding(8.dp), verticalArrangement = Arrangement.SpaceAround) {
        for (row in 0..2) {
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Row(modifier = Modifier.fillMaxWidth().weight(1f), horizontalArrangement = Arrangement.SpaceEvenly) {
                for (col in 0..2) {
                    val index = row * 3 + col
                    val felt = state.fields[index]

                    val drawableRes = when (felt) {
                        FELT.KRYDS -> R.drawable.kryds
                        FELT.BOLLE -> R.drawable.bolle
                        FELT.TOMT -> R.drawable.blank
                    }

                    Image(
                        painter = painterResource(id = drawableRes),
                        contentDescription = "Field $index",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.fillMaxSize()
                            .weight(1.0f,true)
                            .padding(end=4.dp)
                            .clickable {
                            clickFun(index)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TicTacToeStartTheme {
        Greeting("Android")
    }
}