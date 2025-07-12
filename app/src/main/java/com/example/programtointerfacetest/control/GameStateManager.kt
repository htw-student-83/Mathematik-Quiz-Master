package com.example.programtointerfacetest.control

class GameStateManager {
    var currentState: GameStates = GameStates.START

    fun setState(newState: GameStates){
        this.currentState = newState

        println("State has changed to " + this.currentState)
    }
}