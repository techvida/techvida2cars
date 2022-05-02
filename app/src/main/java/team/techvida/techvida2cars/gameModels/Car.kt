package team.techvida.techvida2cars.gameModels

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import team.techvida.techvida2cars.R

class Car(context: Context) : AppCompatImageView(context) {

    var mainScope = MainScope()

    var color = 0

    var roadRatio = 0f
    var centerX = 0f
    var centerY = 0f

    var size = 0f

    var roadRunway: RoadRunway = RoadRunway.Left

    var offsetFirst = 0f
    var offsetSecond = 0f


    var isMoving = false


    fun init(roadRatio: Float, carSize: Float, top: Float) {
        this.roadRatio = roadRatio
        this.size = carSize
        roadRunway = RoadRunway.Left

        setImageResource(R.mipmap.car)

        offsetFirst = roadRatio * 1
        offsetSecond = roadRatio * 3

        centerY = top
        centerX = offsetFirst

        revalidate()
    }

    private fun revalidate() {

        layout(
            (centerX - size / 2).toInt(),
            (centerY - size / 2).toInt(),
            (centerX + size / 2).toInt(),
            (centerY + size / 2).toInt()

        )
    }


    fun changeSide() {

        if (roadRunway == RoadRunway.Left) moveToRight() else moveToLeft()
    }

    private fun moveToLeft() {
        if (isMoving) return

        isMoving = true
        roadRunway = RoadRunway.Left

        mainScope.launch {

            while (isMoving && roadRunway == RoadRunway.Left) {
                if (centerX <= offsetFirst) {
                    isMoving = false
                    centerX = offsetFirst
                    rotation = 0f
                } else {
                    centerX -= 10
                    rotation = -30f
                }
                revalidate()
                delay(10)
            }
        }

    }

    private fun moveToRight() {
        if (isMoving) return

        isMoving = true
        roadRunway = RoadRunway.Right

        mainScope.launch {

            while (isMoving && roadRunway == RoadRunway.Right) {
                if (centerX >= offsetSecond) {
                    isMoving = false
                    centerX = offsetSecond
                    rotation = 0f
                } else {
                    centerX += 10
                    rotation = +30f
                }
                revalidate()
                delay(10)
            }
        }

    }
}