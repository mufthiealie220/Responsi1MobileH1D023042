package com.unsoed.responsi1mobile42.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.unsoed.responsi1mobile42.R
import com.unsoed.responsi1mobile42.databinding.FragmentPemainDetailBinding

class PemainDetailFragment(
    private val name: String,
    private val position: String,
    private val nationality: String,
    private val photoUrl: String
) : BottomSheetDialogFragment() {

    private var _binding: FragmentPemainDetailBinding? = null
    private val binding get() = _binding!!

    override fun getTheme(): Int = R.style.ThemeOverlay_App_BottomSheetDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPemainDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        Toast.makeText(context, name, Toast.LENGTH_SHORT).show()
        binding.textViewPemainName.text = name
        binding.textViewPemainPosition.text = position
        binding.textViewPemainNationality.text = nationality

        if (photoUrl.isNotEmpty()) {
            Glide.with(this)
                .load(photoUrl)
                .placeholder(R.drawable.ic_person_placeholder)
                .into(binding.imageViewPemain)
        } else {
            binding.imageViewPemain.setImageResource(R.drawable.ic_person_placeholder)
        }
    }
}
