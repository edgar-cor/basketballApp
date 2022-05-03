package com.example.basketball

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.basketball.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.localScore.observe(this, Observer {
            localScoreValue  -> binding.localScoreText.text = localScoreValue.toString()
        })

        viewModel.localScore.observe(this, Observer {
            vistorScoreValue -> binding.visitorScoreText.text = vistorScoreValue.toString()
        })

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
            viewModel.removePointLocal()
            binding.localScoreText.text = viewModel.localScore.toString()
        }

        binding.visitorPlusButton.setOnClickListener {
            addPointToScore( 1, false)
        }

        binding.visitorTwoPointsButton.setOnClickListener {
            addPointToScore(2 , false)
        }

        binding.visitorMinusButton.setOnClickListener {
            viewModel.removePointVisitor()
            binding.visitorScoreText.text = viewModel.visitorScore.toString()
        }
        binding.restartButton.setOnClickListener {
            viewModel.resetPoint()
        }

        binding.resultsButton.setOnClickListener{
            val intent = Intent(this, ScoreActivity::class.java)
            intent.putExtra(ScoreActivity.LOCAL_SCORE_KEY, viewModel.localScore.value)
            intent.putExtra(ScoreActivity.VISITOR_SCORE_KEY, viewModel.visitorScore.value)
            startActivity(intent)
        }
    }



    private fun addPointToScore( point: Int, isLocal: Boolean ) {
       viewModel.addPointToScore(point, isLocal)
    }
}
