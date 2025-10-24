package com.unsoed.responsi1mobile42

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.unsoed.responsi1mobile42.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
        initListener()
    }
    private fun initLayout(){
        binding.layoutTeamhistory.let {
            it.imgIcon.setImageResource(R.drawable.logo_mainz)
            it.tvLayout.setText("Club History")
        }

        binding.layoutHeadcoach.let {
            it.imgIcon.setImageResource(R.drawable.head_coach)
            it.tvLayout.setText("Head Coach")
        }

        binding.layoutTeamsquad.let {
            it.imgIcon.setImageResource(R.drawable.team_squad)
            it.tvLayout.setText("Team Squad")
        }
    }

    private fun initListener() {

    binding.layoutTeamhistory.root.setOnClickListener {
        startActivity(Intent(this, TeamHistory::class.java))
        }
    binding.layoutHeadcoach.root.setOnClickListener {
        startActivity(Intent(this, HeadCoach::class.java))
        }
    binding.layoutTeamsquad.root.setOnClickListener {
        startActivity(Intent(this, DaftarPemain::class.java))
    }
    }
}
