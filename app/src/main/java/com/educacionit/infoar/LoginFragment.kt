package com.educacionit.infoar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.educacionit.infoar.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.termsAndCondTextView.setOnClickListener {
            navigateToTermsAndCondFragment()
        }
        return binding.root
    }


    private fun navigateToTermsAndCondFragment() {
        activity?.supportFragmentManager?.apply {
            beginTransaction()
                .replace(R.id.fragment_container_view, TermsAndConditionsFragment())
                // Optimiza el cambio de estado de los fragments involucrados, debe ir junto con addToBackStack(null)
                .setReorderingAllowed(true)
                // No destruye el fragment inicial, ya que permite volver mediante el back button del sistema
                .addToBackStack(null)
                .commit()
        }
    }
}