package com.educacionit.infoar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.educacionit.infoar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        with(binding) {
            setContentView(root)
            applySystemPaddings(root)

            /**
             * Practica de Sintaxis N1. Repaso de variables, funciones, etc
             * 1. Darle la funcionalidad al botón Iniciar Sesión
             * 2. Validar que la contraseña tenga 8 caracteres min y que los campos no estén vacios
             *
             * TODO(Tarea: Darle la funcionalidad al checkbox para saltearse el login cuando sea requerido)
             */

            btnIniciarSesion.setOnClickListener {
                val usuario: String = etUsuario.text.toString()
                val contrasenia: String = etContrasenia.text.toString()

                if (usuario.isNotEmpty() && contrasenia.isNotEmpty() && contrasenia.length >= 8) {
                    navigateToHome(usuario)
                } else {
                    Toast.makeText(this@MainActivity, "Ingrese una contraseña válida", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun navigateToHome(usuario: String) {
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra("USER", usuario)
        }
        startActivity(intent)
        finish()
    }
}