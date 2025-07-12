package com.example.programtointerfacetest.view.gui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.programtointerfacetest.R
import com.example.programtointerfacetest.ui.theme.ProgramToInterfaceTestTheme

class RegistrationCompletedAcitvity: ComponentActivity() {
    private val modell = ModellImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBlue = Color(0xFFD4E5FF)
        setContent {
            ProgramToInterfaceTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val username = intent.getStringExtra("USERNAME")
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                            .background(lightBlue)
                    ) {
                        modell.GetModellProcessSuccessful(
                            username.toString(),
                            "die Registrierung war erfolgreich",
                            R.drawable.registration_successful
                        )

                        modell.ChangeActivityAutomatically(
                            targetActivity = LoginActivity::class
                        )
                    }
                }
            }
        }
    }
}