package team.techvida.techvida2cars.gameModels

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout
import kotlin.math.abs

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
    var verticalMoveStep = 0f

    var leftSide = 0f
    var rightSide = 0f


    var hurdleSize = 0f
    var carSize = 0f

    init {
        addView(car)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (event.action == MotionEvent.ACTION_DOWN)
            car.changeSide()

        return super.onTouchEvent(event)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        hurdleSize = width / 5f
        carSize = width / 3f

        verticalMoveStep = height / 250f

        roadRatio = width / 4f

        leftSide = roadRatio * 1
        rightSide = roadRatio * 3

        car.init(roadRatio, carSize, height - carSize * 2)

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
            val centerY = ((i * hurdleDistance - height / 2).toFloat())

            hurdle.refreshHurdle()

            hurdle.refreshTop(centerY)

            hurdles.add(hurdle)
            addView(hurdle)
        }

    }

    fun startOrRestartGame() {
        initHurdles()

    }

    fun setNewState() {
        for (hurdle in hurdles) {
            hurdle.refreshTop(hurdle.centerY + verticalMoveStep)

            if (hurdle.centerY > height) {
                hurdle.refreshTop(hurdleSize * -1)
                hurdle.refreshHurdle()
            }

            val verticalDistance = car.centerY - hurdle.centerY
            val horizontalDistance = car.centerX - hurdle.centerX

            val carTouchSeverity = carSize * 0.7
            val hurdleTouchSeverity = carSize * 0.7


            if (verticalDistance < carTouchSeverity * -1) {
                if (hurdle.isScore) {
                    /// lose
                    Log.i("TAG", "state:LOSE : UnUsed score ")
                }
            } else if (abs(verticalDistance) < carTouchSeverity && abs(horizontalDistance) < hurdleTouchSeverity) {

                if (hurdle.isScore && !hurdle.isUsed) {
                    /// score
                    hurdle.isUsed = true
                    Log.i("TAG", "state: SCORE")
                } else if (!hurdle.isScore) {
                    /// lose
                    Log.i("TAG", "state: LOSE")
                }
            }


        }

    }

}