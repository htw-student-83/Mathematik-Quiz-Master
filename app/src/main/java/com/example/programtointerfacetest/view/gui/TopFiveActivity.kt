package com.example.programtointerfacetest.view.gui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.model.accounts.TopFiveUser
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programtointerfacetest.control.ControlerSingletonImpl
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme

open class TopFiveActivity: ComponentActivity() {

    private val modell = ModellImpl()
    private val controler = ControlerSingletonImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBlue = Color(0xFF80D8FF)
        setContent {
            ProgramToInterfaceTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                            .background(lightBlue)
                    ) {
                        Spacer(modifier = Modifier.padding(35.dp))

                        modell.GetModelNameOfQuiz(backgroundColor = Color(0xFF80D8FF))

                        Spacer(modifier = Modifier.padding(25.dp))

                        modell.GetModelTextProcess(
                            processTopic = "Top Five",
                            backgroundColor = Color(0xFF80D8FF),
                            fontSize = 25.sp
                        )

                        Spacer(modifier = Modifier.height(55.dp))

                        Content()

                    }
                }
            }
        }
    }

    @Composable
    fun Content() {
        var topFive: List<TopFiveUser?>

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(13.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                ) {
                    Text(
                        text = "Platz",
                        modifier = Modifier.width(45.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Name",
                        modifier = Modifier.width(190.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Punkte",
                        modifier = Modifier.width(149.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                val controlInstance = controler.getInstance(applicationContext)
                if (controlInstance != null) {
                    topFive = controlInstance.getTheTopFive()
                    Log.d("Content of UserList", "$topFive")
                    topFive.filterNotNull().forEachIndexed() { index, user ->
                        modell.GetRankingRow(
                            (index + 1).toString(),
                            user.firstname,
                            user.lastname,
                            user.highscore,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(200.dp))

                val context = LocalContext.current

                modell.GetModelButton(
                    "Zurück",
                    backgroundColor = Color(0xFFD2CFCF),
                    fontcolor = Color.Black,
                    buttonWidth = 255.dp,
                    onClick = {
                        val intent = Intent(context, HauptmenuActivity::class.java)
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}