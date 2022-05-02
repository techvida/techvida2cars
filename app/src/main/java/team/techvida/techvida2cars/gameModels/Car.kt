package team.techvida.techvida2cars.gameModels

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import team.techvida.techvida2cars.R

class Car(context: Context) : AppCompatImageView(context) {


    var color = 0

    var roadRatio = 0f
    var centerX = 0f
    var centerY = 0f

    var size = 0f

    var roadRunway: RoadRunway = RoadRunway.Left

    var offsetFirst = 0f
    var offsetSecond = 0f

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
}