package com.unsoed.responsi1mobile42

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.unsoed.responsi1mobile42.data.viewmodel.TeamViewModel
import com.unsoed.responsi1mobile42.databinding.ActivityHeadCoachBinding

class HeadCoach : AppCompatActivity() {

    private lateinit var binding: ActivityHeadCoachBinding
    private val teamViewModel: TeamViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeadCoachBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initLayout()
        initListener()
        fetchCoachData()
    }

    private fun initLayout() {

        binding.layoutInstagram.let {
            it.imgIcon.setImageResource(R.drawable.ic_ig)
            it.tvMenuName.text = "Instagram"
        }

        binding.layoutTiktok.let {
            it.imgIcon.setImageResource(R.drawable.tiktok)
            it.tvMenuName.text = "TikTok"
        }
    }

    private fun initListener() {
        binding.btnBack.setOnClickListener { finish() }


        binding.layoutInstagram.root.setOnClickListener { openUrl("https://www.instagram.com/bohenriks/") }
        binding.layoutTiktok.root.setOnClickListener { openUrl("https://www.tiktok.com/@bo.henriksen90?is_from_webapp=1&sender_device=pc") }
    }

    private fun fetchCoachData() {
        teamViewModel.fetchTeamData()

        teamViewModel.teamData.observe(this) { team ->
            val coach = team.coach ?: return@observe


            binding.tvCoachBiodata.text ="""
                ${coach.name}
                ${coach.dateOfBirth} 
                ${coach.nationality}
            """.trimIndent()


            binding.tvCoachStory.text = """
                ${coach.name}, lahir pada ${coach.dateOfBirth} di Denmark, adalah pelatih sepak bola Denmark yang dikenal dengan gaya melatihnya yang penuh semangat dan taktik defensif yang solid. 
                Karier kepelatihannya dimulai di klub Denmark FC Fredericia sebelum pindah ke FC Midtjylland sebagai asisten pelatih. 
                Henriksen kemudian menjadi kepala pelatih di Hobro IK dan membawa klub tersebut promosi ke Liga Super Denmark. 
                Pada tahun 2021, ia ditunjuk sebagai pelatih kepala FSV Mainz 05 di Bundesliga Jerman, di mana ia berhasil membawa angin segar dengan pendekatan taktis yang disiplin dan kemampuan membangkitkan performa tim.
            """.trimIndent()


            val achievementsHtml = """
                <h3>🏆 <b>Prestasi ${coach.name}</b></h3>
                <ul>
                    <li><b>Promosi ke Superliga Denmark</b> — bersama Hobro IK (2014)</li>
                    <li><b>Penampilan Terbaik Hobro IK</b> — Posisi 7 di Superliga Denmark (2014-2015)</li>
                    <li><b>Pelatih Terbaik Bulanan Bundesliga</b> — FSV Mainz 05 (2022)</li>
                    <li><b>Penyelamatan dari Degradasi</b> — Membawa Mainz 05 tetap di Bundesliga (2021-2022)</li>
                    <li><b>Performa Impresif melawan Tim Top</b> — Kemenangan atas Bayern Munich dan Borussia Dortmund</li>
                    <li><b>Pengembangan Pemain Muda</b> — Berhasil mengembangkan bakat-bakat muda di Mainz 05</li>
                </ul>
            """.trimIndent()

            binding.tvCoachAchievements.text = Html.fromHtml(achievementsHtml, Html.FROM_HTML_MODE_COMPACT)
        }

        teamViewModel.error.observe(this) { msg ->
            if (!msg.isNullOrEmpty()) Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}