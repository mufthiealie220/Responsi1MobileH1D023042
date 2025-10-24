package com.unsoed.responsi1mobile42

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.unsoed.responsi1mobile42.data.ui.adapter.PemainAdapter
import com.unsoed.responsi1mobile42.data.model.Player
import com.unsoed.responsi1mobile42.data.viewmodel.TeamViewModel
import com.unsoed.responsi1mobile42.databinding.ActivityDaftarPemainBinding
import com.unsoed.responsi1mobile42.ui.fragment.PemainDetailFragment

class DaftarPemain : AppCompatActivity() {

    private lateinit var binding: ActivityDaftarPemainBinding
    private lateinit var viewModel: TeamViewModel
    private lateinit var adapter: PemainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDaftarPemainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PemainAdapter { pemain ->
            val fragment = PemainDetailFragment(
                name = pemain.name,
                position = pemain.position ?: "Unknown",
                nationality = pemain.nationality,
                photoUrl = ""  // placeholder kosong
            )
            fragment.show(supportFragmentManager, "detail")
        }

        binding.recyclerViewPemain.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPemain.adapter = adapter

        viewModel = ViewModelProvider(this)[TeamViewModel::class.java]
        viewModel.fetchTeamData()
        viewModel.teamData.observe(this) { team ->
            // Urutkan ascending: GK → Defence → Midfield → Forward
            val sortedList = team.squad.sortedWith(compareBy { player ->
                when (player.position) {
                    "Goalkeeper" -> 0
                    "Defence", "Left-Back", "Right-Back", "Centre-Back" -> 1
                    "Midfield", "Central Midfield", "Defensive Midfield", "Attacking Midfield" -> 2
                    "Forward", "Offence", "Centre-Forward", "Right Winger", "Left Winger" -> 3
                    else -> 4
                }
            })
            adapter.submitList(sortedList)
        }
    }
}
