package team.techvida.techvida2cars.gameModels

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import team.techvida.techvida2cars.R
import java.util.*

class Hurdle(context: Context) : AppCompatImageView(context) {

    var color = 0

    var isScore = false

    var roadRatio = 0f
    var centerX = 0f
    var centerY = 0f

    var size = 0f

    var roadRunway: RoadRunway = RoadRunway.Left


    fun refreshPosition() {
        val position = if (roadRunway == RoadRunway.Left) 1 else 3

        centerX = position * roadRatio

        layout(
            (centerX - size / 2).toInt(),
            (centerY - size / 2).toInt(),
            (centerX + size / 2).toInt(),
            (centerY + size / 2).toInt()

        )

    }

    fun refreshHurdle() {

        isScore = Random().nextBoolean()
        setImageResource(if (isScore) R.mipmap.circle else R.mipmap.rect)

        roadRunway = if (Random().nextBoolean()) RoadRunway.Left else RoadRunway.Right


    }
}