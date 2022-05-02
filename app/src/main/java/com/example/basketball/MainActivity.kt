package com.example.basketball

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.basketball.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var localScore = 0
    private var visitorScore = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        scoreBasketBall()
    }

    private fun scoreBasketBall(){
        binding.localPlusButton.setOnClickListener {
            addPointToScore( 1, true)
        }

        binding.localTwoPointsButton.setOnClickListener {
            addPointToScore(2 , true)
        }

        binding.localMinusButton.setOnClickListener {
            removePoint(true)
        }

        binding.restartButton.setOnClickListener {
            resetPoint(true)
        }

        binding.visitorPlusButton.setOnClickListener {
            addPointToScore( 1, false)
        }

        binding.visitorTwoPointsButton.setOnClickListener {
            addPointToScore(2 , false)
        }

        binding.visitorMinusButton.setOnClickListener {
            removePoint(false)
        }
        binding.restartButton.setOnClickListener {
            resetPoint(false)
        }

        binding.resultsButton.setOnClickListener{
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra(ScoreActivity.LOCAL_SCORE_KEY, localScore)
            intent.putExtra(ScoreActivity.VISITOR_SCORE_KEY, visitorScore)
            startActivity(intent)
        }
    }

    private fun resetPoint(isLocal: Boolean){
        localScore = 0
        visitorScore = 0
            binding.localScoreText.text = localScore.toString()
            binding.visitorScoreText.text = visitorScore.toString()
    }

    private fun addPointToScore( point: Int, isLocal: Boolean ) {
        if (isLocal){
            localScore += point
            binding.localScoreText.text = localScore.toString()
        }else {
            visitorScore += point
            binding.visitorScoreText.text = visitorScore.toString()
        }
    }

    private fun removePoint(  isLocal: Boolean ) {
        if (isLocal && localScore > 0){
            localScore--
            binding.localScoreText.text = localScore.toString()
        }else if ( visitorScore > 0) {
            visitorScore--
            binding.visitorScoreText.text = visitorScore.toString()
        }
    }
}
