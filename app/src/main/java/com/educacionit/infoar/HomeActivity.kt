package com.educacionit.infoar

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.educacionit.infoar.databinding.ActivityHomeBinding
import com.educacionit.infoar.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)
        applySystemPaddings(binding.root)

        val usuario = intent.extras?.getString("USER")
        binding.homeTextView.text = getString(R.string.home_text, usuario)
    }
}