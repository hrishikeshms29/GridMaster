package com.example.tictakonline19

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tictakonline19.ui.theme.TicTakOnline19Theme
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var showScreen by mutableStateOf(false)
        Handler(Looper.getMainLooper()).postDelayed({
            showScreen = true
        }, 0)
        setContent {
            TicTakOnline19Theme {
                AnimatedScreen(showScreen) {
                    TicTacToeScreen()
                }
            }
        }
    }
}

@Composable
fun AnimatedScreen(visible: Boolean, content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + expandIn(),
        exit = fadeOut() + shrinkOut()
    ) {
        content()
    }
}


@Composable
fun TicTacToeScreen() {
    var playerName by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var showSnackbar by remember { mutableStateOf(false) }
    val mContext = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),

    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = playerName,
                onValueChange = { playerName = it },
                label = { Text("Enter your name") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedButton(
                onClick = { /* TODO: Implement start offline game */
                    mContext.startActivity(Intent(mContext, GameActivity::class.java))
                },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text("Start Offline Game")
            }


            OutlinedButton(
                onClick = { /* TODO: Implement create game */ },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text("Create Game")
            }

            OutlinedButton(
                onClick = { /* TODO: Implement join game */ },
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text("Join Game")
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Game Created") },
                    text = { Text("Your game has been created successfully!") },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    }
                )
            }

            if (showSnackbar) {
                Snackbar(
                    action = {
                        TextButton(onClick = { showSnackbar = false }) {
                            Text("Dismiss")
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Game started!")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TicTakOnline19Theme {
        TicTacToeScreen()
    }
}
//@Composable
//fun PlayerNameInput(playerName: String, onPlayerNameChange: (String) -> Unit) {
//    // Text field for entering player name
//    TextField(
//        value = playerName,
//        onValueChange = { onPlayerNameChange(it) },
//        label = { Text("Enter your name") }
//    )
//}


//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            TicTakOnline19Theme {
//                TicTacToeScreen()
//            }
//        }
//    }
//}
//
//@Composable
//fun TicTacToeScreen() {
//    var playerName by remember { mutableStateOf("") }
//
//    Scaffold(
//        modifier = Modifier.fillMaxSize(),
//    ) {paddingValues ->
//        Column(
//            modifier = Modifier
//                .padding(paddingValues)
//                .padding(16.dp)
//                .fillMaxSize(),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            Button(onClick = { /* TODO: Implement start offline game */ }) {
//                Text("Start Offline Game")
//            }
//            Button(onClick = { /* TODO: Implement create game */ }) {
//                Text("Create Game")
//            }
//            Column(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Button(onClick = { /* TODO: Implement join game */ }) {
//                    Text("Join Game")
//                }
//                // Input field for player name when joining a game
//                PlayerNameInput(playerName = playerName, onPlayerNameChange = { playerName = it })
//            }
//        }
//    }
//}