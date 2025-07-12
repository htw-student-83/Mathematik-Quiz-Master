package com.example.programtointerfacetest.view.gui

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.programtointerfacetest.control.GameStateManager
import com.example.programtointerfacetest.network.bluetooth.BluetoothClientImpl
import com.example.programtointerfacetest.network.bluetooth.BluetoothServer
import com.example.programtointerfacetest.control.GameStates
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import kotlinx.coroutines.delay

class QuestionConnectiontyp: ComponentActivity() {
    private val modell = ModellImpl()

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBlue = Color(0xFF80D8FF)
        setContent {
            ProgramToInterfaceTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val context = LocalContext.current
                    var isWaitingForPlayer by remember { mutableStateOf(false) }
                    val bluetoothPlayer1 = BluetoothServer(context)
                    val bluetoothPlayer2 = BluetoothClientImpl("Your Match", context)
                    val permission = android.Manifest.permission.BLUETOOTH_CONNECT

                    //Für die Berechtigung, zur Laufzeit über Bluetooth die Verbindung mit einem anderem Gerät aufzunehmen
                    val hasPermission = ContextCompat.checkSelfPermission(
                        context,
                        permission
                    ) == PackageManager.PERMISSION_GRANTED

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                            .background(lightBlue)
                    ) {

                        Spacer(modifier = Modifier.padding(35.dp))

                        modell.GetModelNameOfQuiz(backgroundColor = Color(0xFF80D8FF))

                        Spacer(modifier = Modifier.padding(15.dp))

                        modell.GetModelTextProcess(
                            processTopic = "Multiplayermode",
                            backgroundColor = Color(0xFF80D8FF),
                            fontSize = 25.sp
                        )
                        Spacer(modifier = Modifier.height(165.dp))

                        modell.GetModelQuestion(
                            "Willst du Spieler1 oder Spieler2 sein?",
                            backgroundColor = Color(0xFF80D8FF),
                        )

                        Spacer(modifier = Modifier.height(15.dp))

                        modell.GetModelButton(
                            "Spieler1",
                            backgroundColor = Color(0xFF5E77EF),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                if(hasPermission){
                                    isWaitingForPlayer = true
                                    CoroutineScope(Dispatchers.IO).launch {
                                        delay(100) // kleiner Delay, damit UI gerendert wird
                                        val uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
                                        bluetoothPlayer1.startServer(uuid, "QuizHost")

                                        withContext(Dispatchers.Main) {

                                        }
                                    }
                                }
                            }
                        )
                        if (isWaitingForPlayer) {
                            Text("Warte auf Spieler 2")
                            CircularProgressIndicator()
                        }

                        Spacer(modifier = Modifier.height(5.dp))

                        modell.GetModelButton(
                            "Spieler2",
                            backgroundColor = Color(0x7E888787),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = @androidx.annotation.RequiresPermission(android.Manifest.permission.BLUETOOTH_CONNECT) {
                                bluetoothPlayer2.connect()
                            }
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        modell.GetModelButton(
                            "Abbrechen",
                            backgroundColor = Color(0xDA888787),
                            fontcolor = Color.Black,
                            buttonWidth = 200.dp,
                            onClick = {
                                val intent = Intent(context, HauptmenuActivity::class.java)
                                context.startActivity(intent)
                            }
                        )
                    }
                }
            }
        }
    }
}