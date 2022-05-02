package team.techvida.techvida2cars.gameModels

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class GameRoad : FrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )


    var hurdles = arrayListOf<Hurdle>()

    val car = Car(context)

    var roadRatio = 0f

    var leftSide = 0f
    var rightSide = 0f


    var hurdleSize = 0f
    var carSize = 0f

    init {
        addView(car)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        hurdleSize = width / 5f
        carSize = width / 3f


        roadRatio = width / 4f

        leftSide = roadRatio * 1
        rightSide = roadRatio * 3

        car.init(roadRatio, carSize, height - carSize * 2)

        initHurdles()
    }

    fun initHurdles() {

        if (hurdles.size > 0)
            for (hurdle in hurdles) {
                removeView(hurdle)
            }

        hurdles = arrayListOf()

        val maxHurdles = 4
        val hurdleDistance = height / maxHurdles

        for (i in 0 until maxHurdles) {

            val hurdle = Hurdle(context)

            hurdle.size = hurdleSize
            hurdle.roadRatio = roadRatio
            hurdle.centerY = ((i * hurdleDistance - height / 2).toFloat())

            hurdle.refreshHurdle()

            hurdle.refreshPosition()

            addView(hurdle)
        }

    }

}