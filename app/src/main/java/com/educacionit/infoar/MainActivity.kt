package com.educacionit.infoar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.educacionit.infoar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemPaddings(binding.root)

        /**
         * Practica de Sintaxis N1. Repaso de variables, funciones, etc
         * 1. Darle la funcionalidad al botón Iniciar Sesión
         * 2. Validar que la contraseña tenga 8 caracteres min y que los campos no estén vacios
         *
         * TODO(Tarea: Darle la funcionalidad al checkbox para saltearse el login cuando sea requerido)
         */
    }

    private fun navigateToHome(usuario: String) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}