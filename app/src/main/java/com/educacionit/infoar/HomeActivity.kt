package com.educacionit.infoar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.educacionit.infoar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    companion object {
        const val USER_PARAM_EXTRA = "USER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)
        applySystemPaddings(binding.root)

        val usuario = intent.extras?.getString(USER_PARAM_EXTRA)
        binding.homeTextView.text = getString(R.string.home_text, usuario)
    }
}