package com.unsoed.responsi1mobile42

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.unsoed.responsi1mobile42.databinding.ActivityTeamHistoryBinding

class TeamHistory : AppCompatActivity() {
    private lateinit var binding: ActivityTeamHistoryBinding
    private val latitude = "50.0822"
    private val longitude = "8.2439"
    private val gMapsUrl = "http://maps.google.com/maps?q=loc:"
    private val packageMaps = "com.google.android.apps.maps"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
        initListener()
    }
    private fun initLayout() {
        binding.layoutCoach.let {
            it.imgIconhistory.setImageResource(R.drawable.face)
            it.tvLayouthistory.setText("Head Coach")

        }

        binding.layoutIg.let {
            it.imgIconhistory.setImageResource(R.drawable.ic_ig)
            it.tvLayouthistory.setText("Instagram")

        }

        binding.layoutX.let {
            it.imgIconhistory.setImageResource(R.drawable.twitter)
            it.tvLayouthistory.setText("Twitter")
        }

        binding.layoutStadium.let {
            it.imgIconhistory.setImageResource(R.drawable.stadion)
            it.tvLayouthistory.setText("Stadium Location")
        }
    }
    private fun initListener() {

        binding.layoutCoach.root.setOnClickListener {
            startActivity(Intent(this, HeadCoach::class.java))
        }

        binding.layoutX.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = "https://x.com/Mainz05en".toUri()
            startActivity(intent)
        }

        binding.layoutIg.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = "https://www.instagram.com/mainz05/".toUri()
            startActivity(intent)
        }

        binding.layoutStadium.root.setOnClickListener {
            val gMapsIntentUri = "https://www.google.com/maps?q=MEWA+ARENA+Mainz&ll=$latitude,$longitude".toUri()
            val mapIntent = Intent(Intent.ACTION_VIEW, gMapsIntentUri)
            mapIntent.setPackage(packageMaps)
            startActivity(mapIntent)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

}