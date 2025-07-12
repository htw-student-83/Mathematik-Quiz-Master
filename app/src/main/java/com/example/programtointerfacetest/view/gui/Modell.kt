package com.example.programtointerfacetest.view.gui

import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import kotlin.reflect.KClass

abstract class Modell {

    @Composable
    abstract fun GetModelNameOfQuiz(backgroundColor: Color)


    @Composable
    abstract fun GetModelTextProcess(processTopic: String, backgroundColor: Color, fontSize: TextUnit)


    @Composable
    abstract fun GetRankingRow(place: String, firstnameOfWinner: String, lastnameOfWinner: String, points: String)


    @Composable
    abstract fun GetModelButton(buttonText: String, backgroundColor:  Color, fontcolor: Color, buttonWidth: Dp, onClick: () -> Unit)


    @Composable
    abstract fun GetUserDetails(topic: String, topicvalue: String)


    @Composable
    abstract fun GetModelEditField(image: ImageVector, onTextChange: (String) -> Unit, placeholder: String)


    @Composable
    abstract fun GetModelEditFieldForLogin(image: ImageVector, onTextChange: (String) -> Unit,
                                           placeholder: String, visualTransformation: VisualTransformation,
                                           keyboardOptions: KeyboardOptions, modifier: Modifier)

    @Composable
    abstract fun GetModelQuestion(question: String, backgroundColor: Color)


    @Composable
    abstract fun GetModellProcessSuccessful(username: String, text: String, @DrawableRes imageResId: Int)


    @Composable
    abstract fun GetModellCreateExercise(exercise: String?, input: String,onTextChange: (String) -> Unit)


    @Composable
    abstract fun GetModellDetailsOfRound(topic: String, value: String, backgroundColor: Color,
                                         distanceLeftFromFrame: Int, distanceLeftFromTopic: Int)

    @Composable
    abstract fun StartTimer(countdown: Int, isRunning: Boolean, onToggle: () -> Unit,
                            onTimeout: () -> Unit)


    @Composable
    abstract fun ChangeActivityAutomatically(targetActivity: KClass<out Activity>)

}