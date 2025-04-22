package com.educacionit.infoar.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.educacionit.infoar.R
import com.educacionit.infoar.databinding.FragmentLoginBinding
import com.educacionit.infoar.presentation.fragments.communication.LoginListener

class LoginFragment private constructor(): Fragment() {

    private lateinit var loginListener: LoginListener

    companion object {
        fun newInstance(loginListener: LoginListener): LoginFragment {
            return LoginFragment().apply {
                this.loginListener = loginListener
            }
        }
    }

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        with(binding) {

            binding.btnIniciarSesion.setOnClickListener {
                /**
                 * TODO(Tarea: Darle la funcionalidad al checkbox para saltearse el login cuando sea requerido)
                 */

                val usuario: String = etUsuario.text.toString()
                navigateToHome(usuario)
                /*val contrasenia: String = etContrasenia.text.toString()

                if (usuario.isNotEmpty() && contrasenia.isNotEmpty() && contrasenia.length >= 8) {

                } else {
                    Toast.makeText(
                        context,
                        "Ingrese una contraseña válida",
                        Toast.LENGTH_LONG
                    ).show()

                }*/

            }

            termsAndCondTextView.setOnClickListener {
                navigateToTermsAndCondFragment()
            }
            return root
        }
    }

    private fun navigateToHome(usuario: String) {
        loginListener.onLoginSuccessful(usuario)
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