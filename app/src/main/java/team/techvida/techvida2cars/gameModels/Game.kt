package team.techvida.techvida2cars.gameModels

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Game {

    var roads: ArrayList<GameRoad> = arrayListOf()

    var gameSpeed = 0L
    var gameMaxSpeed = 50L

    private var isGameRunning: Boolean = false

    suspend fun startOrRestartGame() {
        isGameRunning = true

        for ((index, road) in roads.withIndex()) {
            road.startOrRestartGame(index)
        }

        gameEngine().start()
        speedChanger().start()
    }

    private suspend fun gameEngine() = MainScope().launch {
        while (isGameRunning) {
            for (road in roads) {
                road.setNewState()
            }
            delay(gameMaxSpeed - gameSpeed)
        }
    }

    private suspend fun speedChanger() = MainScope().launch {
        while (isGameRunning) {
            gameSpeed++
            delay(10 * 1000L)
        }
    }


}