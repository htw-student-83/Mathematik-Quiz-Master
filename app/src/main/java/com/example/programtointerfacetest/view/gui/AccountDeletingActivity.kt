package com.example.programtointerfacetest.view.gui

import android.os.Bundle
import android.util.Log
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

class AccountDeletingActivity: ComponentActivity() {
    private val modell = ModellImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightBlue = Color(0xFFF1F0F0)
        setContent {
            ProgramToInterfaceTestTheme {
                val username = intent.getStringExtra("UNAME")
                Log.d("Deleting Account:", "Received user name: $username")
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp)
                            .background(lightBlue)
                    ) {
                        modell.GetModellProcessSuccessful(
                            username.toString(),
                            "dein Account wurde erfolgreich gelöscht",
                            R.drawable.account_loeschen
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