package com.example.programtointerfacetest.view.gui

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.reflect.KClass


class ModellImpl : Modell() {

    @Composable
    override fun GetModelNameOfQuiz(backgroundColor: Color) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
        ) {
            Text(
                text = "Mathemtik-Quiz-Master",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    @Composable
    override fun GetModelTextProcess(processTopic: String, backgroundColor: Color, fontSize: TextUnit) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
        ) {
            Text(
                text = processTopic,
                fontSize = fontSize,
                fontWeight = FontWeight.Light,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    @Composable
    override fun GetRankingRow(place: String, firstnameOfWinner: String,lastnameOfWinner: String, points: String) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(13.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        ) {
            Text(
                text = place,
                modifier = Modifier.width(45.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light
            )

            Text(
                text = firstnameOfWinner,
                modifier = Modifier
                    .width(100.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light
            )

            Text(
                text = lastnameOfWinner,
                modifier = Modifier
                    .width(100.dp)
                    .offset(x = (-55).dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light
            )

            Text(
                text = points,
                modifier = Modifier.width(149.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }

    @Composable
    override fun GetModelButton(buttonText: String,
                                backgroundColor: Color,
                                fontcolor: Color,
                                buttonWidth: Dp,
                                onClick: () -> Unit) {
        val context = LocalContext.current
        Box(modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = onClick,
                modifier = Modifier
                    .align(Alignment.Center).testTag("testButton"),
                colors = ButtonDefaults.buttonColors(
                    containerColor = backgroundColor,
                    contentColor = fontcolor
                )
            ) {
                Text(
                    text = buttonText,
                    modifier = Modifier.width(buttonWidth),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    @Composable
    override fun GetUserDetails(topic: String, topicvalue: String) {
        Column {
            Row(
                horizontalArrangement = Arrangement.spacedBy(13.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = topic,
                    modifier = Modifier
                        .width(200.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = topicvalue,
                    modifier = Modifier
                        .width(200.dp)
                        .offset(x = -(50).dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Light
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

    @Composable
    override fun GetModelEditField(image: ImageVector, onTextChange: (String) -> Unit, placeholder: String) {
        Box(
            modifier = Modifier
                .fillMaxWidth()  // Box nimmt die gesamte Breite des Containers ein
                .background(Color(0xFF80D8FF)) // Hintergrundfarbe setzen
        ) {
            var text by remember { mutableStateOf("") }
            TextField(
                value = text,
                shape = RoundedCornerShape(20.dp), // 👈 hier werden die Ecken abgerundet
                onValueChange = {
                    text = it
                    onTextChange(it)
                },
                leadingIcon = {
                    Icon(
                        //Icons.Filled.Lock
                        imageVector = image, // Hier kommt das Icon
                        contentDescription = "password icon", // Beschreibung des Icons für Barrierefreiheit
                        tint = Color.Gray // Iconfarbe
                    )
                },
                colors = TextFieldDefaults.colors(
                    // In Material3 wird `colors()` verwendet
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                ),
                placeholder = {
                    Text(
                        placeholder,
                        color = Color.Gray
                    )
                },
                modifier = Modifier
                    .width(250.dp)
                    .align(Alignment.Center)
                    .padding(bottom = 3.dp, top = 3.dp)
                    .testTag("EditFieldTest")
            )
        }
    }

    @Composable
    override fun GetModelEditFieldForLogin(
        image: ImageVector,
        onTextChange: (String) -> Unit,
        placeholder: String,
        visualTransformation: VisualTransformation,
        keyboardOptions: KeyboardOptions,
        modifier: Modifier
    ) {
        var text by remember { mutableStateOf("") }

        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                onTextChange(it)
            },
            shape = RoundedCornerShape(20.dp), // 👈 hier werden die Ecken abgerundet
            colors = TextFieldDefaults.colors(
                // In Material3 wird `colors()` verwendet
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
            ),
            modifier = modifier,
            placeholder = {
                Text(
                    placeholder,
                    color = Color.Gray
                )
            },
            leadingIcon = {
                Icon(
                    image,
                    contentDescription = null ,
                    tint = Color.Gray // Iconfarbe
                )
            },
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions

        )

    }

    @Composable
    override fun GetModelQuestion(question: String, backgroundColor: Color) {
        Box(
            modifier = Modifier
                .fillMaxWidth()  // Box nimmt die gesamte Breite des Containers ein
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = question,
                fontWeight = FontWeight.Light,
                fontFamily = FontFamily.Serif,
                color = Color.Black,
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 20.dp)
                    .padding(end = 20.dp),
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    override fun GetModellProcessSuccessful(username: String, text: String, imageResId: Int) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center

        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    //Das Bild ist nicht fix
                    //R.drawable.logout_successful
                    painter = painterResource(id = imageResId), // dein Bild
                    contentDescription = "Background Image",
                    contentScale = ContentScale.FillBounds,
                    // ⬅️ passt das Bild an die Fläche an
                    modifier = Modifier
                        .clip(RoundedCornerShape(200.dp))
                        .shadow(elevation = 90.dp, shape = CircleShape)
                        .size(180.dp)// 👈 Ecken abrunden
                )
                Spacer(modifier = Modifier.height(35.dp))
                Text(
                    text = username,
                    modifier = Modifier.width(255.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = text,
                    modifier = Modifier.width(255.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }

    @Composable
    override fun GetModellCreateExercise(exercise: String?, input: String, onTextChange: (String) -> Unit) {
        Box(
            modifier = Modifier
                .width(400.dp)
        ) {
            if (exercise != null) {
                Text(
                    text = exercise,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.Black,
                    modifier = Modifier
                        .offset(x = 70.dp)
                )
            }
            TextField(
                value = input,
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .offset(x = 200.dp, y = (-16).dp)
                    .width(100.dp),
                textStyle = TextStyle(fontSize = 15.sp),
                onValueChange = {
                    if(it.all { char -> char.isDigit() })
                        onTextChange(it)
                },
                placeholder = {Text("Lösung")},
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFE3ECE5)
                )
            )
        }
    }

    @Composable
    override fun GetModellDetailsOfRound(topic: String, value: String, backgroundColor: Color,
                                         distanceLeftFromFrame: Int, distanceLeftFromTopic: Int) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(0.5.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(start = 40.dp, end = 50.dp)
                .background(backgroundColor)
        ) {
            Text(
                text = topic,
                modifier = Modifier
                    .width(145.dp)
                    .offset(x = distanceLeftFromFrame.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = value,
                fontSize = 14.sp,
                modifier = Modifier
                    .width(400.dp)
                    .offset(x = distanceLeftFromTopic.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light
            )
        }
    }

    @Composable
    override fun StartTimer(
        countdown: Int,
        isRunning: Boolean,
        onToggle: () -> Unit,
        onTimeout: () -> Unit
    ) {
        val context = LocalContext.current
        var timeLeft by remember { mutableStateOf(countdown) }

        // Effekt startet NUR, wenn isRunning sich ändert:
        LaunchedEffect(isRunning) {
            if (isRunning) {
                while (timeLeft > 0) {
                    delay(1000L)
                    timeLeft--
                }
                Toast.makeText(context, "Zeit abgelaufen", Toast.LENGTH_SHORT).show()
                onToggle()
            }

            if (timeLeft == 0) {
                onTimeout() // ⏰ Timer abgelaufen
            }
        }

        val minutes = timeLeft / 60
        val seconds = timeLeft % 60
        val formattedTime = String.format("%02d:%02d", minutes, seconds)

        Text(
            text = formattedTime,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
    }




    @Composable
    override fun ChangeActivityAutomatically(targetActivity: KClass<out Activity>) {
        val context = LocalContext.current
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(context, targetActivity.java)
            context.startActivity(intent)
            (context as? Activity)?.finish()
        }, 4000)
    }

}
