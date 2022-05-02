package com.example.basketball

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_score.*

class ScoreActivity : AppCompatActivity() {
    companion object {
        const val LOCAL_SCORE_KEY = "local_score"
        const val VISITOR_SCORE_KEY = "visitor_score"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)
        val localScore = intent.extras!!.getInt(LOCAL_SCORE_KEY)
        val visitorScore = intent.extras!!.getInt(VISITOR_SCORE_KEY)

        score_text.text = getString(R.string.local_visitor_score_format, localScore, visitorScore)

        when {
            localScore > visitorScore ->  who_won_text.text = getString(R.string.local_won)
            visitorScore > localScore ->  who_won_text.text = getString(R.string.visitor)
            else -> who_won_text.text = getString(R.string.it_was_a_tie)
        }
    }
}