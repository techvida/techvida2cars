package team.techvida.techvida2cars

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import team.techvida.techvida2cars.databinding.ActivityMainBinding
import team.techvida.techvida2cars.gameModels.Game

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        game = Game()
        game.roads = arrayListOf(binding.roadLeft, binding.roadRight)

        lifecycleScope.launch {
            delay(1000)
            game.startOrRestartGame()
        }


    }
}