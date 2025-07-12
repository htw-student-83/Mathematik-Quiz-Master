package com.example.programtointerfacetest.view.gui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programtointerfacetest.R
import com.example.programtointerfacetest.control.ControlerSingletonImpl
import com.example.model.accounts.User
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme

open class AccountActivity: ComponentActivity() {

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
                        val context = LocalContext.current
                        val controlInstance = controler.getInstance(applicationContext)
                        val userdetails = remember { controlInstance?.let { mutableStateOf<User?>(null) } }

                        LaunchedEffect(Unit) {
                            controlInstance?.let {
                                if (userdetails != null) {
                                    userdetails.value = it.userDataReload()
                                }
                            }
                        }

                        Spacer(modifier = Modifier.padding(35.dp))

                        modell.GetModelNameOfQuiz(backgroundColor = Color(0xFF80D8FF))

                        Spacer(modifier = Modifier.padding(15.dp))

                        modell.GetModelTextProcess(
                            processTopic = "Account",
                            backgroundColor = Color(0xFF80D8FF),
                            fontSize = 25.sp
                        )

                        Spacer(modifier = Modifier.height(55.dp))

                        AccountIcon()

                        Spacer(modifier = Modifier.height(85.dp))

                        userdetails?.value?.let { userData ->
                            Userdetails(userdetails = userData)
                        }

                        Spacer(modifier = Modifier.height(35.dp))

                        //var clicked by remember { mutableStateOf(false) }

                        modell.GetModelButton(
                            "zurück",
                            backgroundColor = Color(0xFFADADAD),
                            fontcolor = Color(0xFFFFFEFE),
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

    @Composable
    fun AccountIcon(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.account2), // dein Bild
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(70.dp)
                    .padding(bottom = 5.dp)
            )
        }
    }


    @Composable
    fun Userdetails(userdetails: User) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                modell.GetUserDetails(
                    "Name:", userdetails.firstname + " " + userdetails.lastname
                )
                modell.GetUserDetails(
                    "Registriert seit:", userdetails.activeSince
                )
                modell.GetUserDetails(
                    "Mein HighScore:", userdetails.highscore + " Punkte"
                )

            }
        }
    }
}