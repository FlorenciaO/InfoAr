package com.educacionit.infoar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.educacionit.infoar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applySystemPaddings(binding.root)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        /**
         * Practica de Sintaxis N1. Repaso de variables, funciones, etc
         * 1. Darle la funcionalidad al botón Iniciar Sesión
         * 2. Validar que la contraseña tenga 8 caracteres min y que los campos no estén vacios
         *
         * TODO(Tarea: Darle la funcionalidad al checkbox para saltearse el login cuando sea requerido)
         */
        return super.onCreateView(name, context, attrs)
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}