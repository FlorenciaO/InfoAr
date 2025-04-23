package com.educacionit.infoar.presentation.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.educacionit.infoar.databinding.ServiceFragmentBinding
import com.educacionit.infoar.services.InfoArService

class ServiceFragment: Fragment() {

    private var _binding: ServiceFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = ServiceFragmentBinding.inflate(inflater, container, false)

        binding.startServiceButton.setOnClickListener {
            Intent(requireContext(), InfoArService::class.java).also {
                it.action = InfoArService.Actions.START.toString()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    requireActivity().startForegroundService(it)
                } else {
                    requireActivity().startService(it)
                }
            }
        }

        binding.stopServiceButton.setOnClickListener {
            Intent(requireContext(), InfoArService::class.java).also {
                it.action = InfoArService.Actions.STOP.toString()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    requireActivity().startForegroundService(it)
                } else {
                    requireActivity().startService(it)
                }
            }
        }

        return binding.root
    }
}