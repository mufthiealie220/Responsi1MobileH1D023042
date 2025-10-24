package com.unsoed.responsi1mobile42.data.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.unsoed.responsi1mobile42.data.model.Player
import com.unsoed.responsi1mobile42.databinding.ListPemainBinding

class PemainAdapter(
    private val onItemClick: (Player) -> Unit
) : RecyclerView.Adapter<PemainAdapter.PemainViewHolder>() {

    private var list: List<Player> = emptyList()

    fun submitList(data: List<Player>) {
        list = data
        notifyDataSetChanged()
    }

    inner class PemainViewHolder(private val binding: ListPemainBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(player: Player) {
            binding.tvPemainName.text = player.name
            binding.tvPemainPosition.text = player.position ?: "-"
            binding.tvPemainNationality.text = player.nationality

            binding.cardViewPemain.setCardBackgroundColor(
                when (player.position) {
                    "Goalkeeper" -> Color.parseColor("#FFD700") // Kuning
                    "Defence", "Left-Back", "Right-Back", "Centre-Back" -> Color.parseColor("#2196F3") // Biru
                    "Midfield", "Central Midfield", "Defensive Midfield", "Attacking Midfield" -> Color.parseColor("#4CAF50") // Hijau
                    "Forward", "Offence", "Centre-Forward", "Right Winger", "Left Winger" -> Color.parseColor("#F44336") // Merah
                    else -> Color.WHITE
                }
            )

            binding.root.setOnClickListener { onItemClick(player) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PemainViewHolder {
        val binding = ListPemainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PemainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PemainViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}
