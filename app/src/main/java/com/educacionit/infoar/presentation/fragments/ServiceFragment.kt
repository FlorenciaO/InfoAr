package com.educacionit.infoar.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startForegroundService
import androidx.fragment.app.Fragment
import com.educacionit.infoar.databinding.FragmentServiceBinding
import com.educacionit.infoar.services.InfoArService
import com.educacionit.infoar.services.InfoArService.Actions

class ServiceFragment: Fragment() {

    private var _binding: FragmentServiceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServiceBinding.inflate(layoutInflater, container, false)

        binding.stopServiceButton.setOnClickListener {
            val intent = Intent(requireContext(), InfoArService::class.java).apply {
                action = Actions.STOP.toString()
            }
            startForegroundService(requireContext(), intent)
        }

        binding.startServiceButton.setOnClickListener {
            val intent = Intent(requireContext(), InfoArService::class.java).apply {
                action = Actions.START.toString()
            }
            startForegroundService(requireContext(), intent)
        }

        return binding.root
    }
}