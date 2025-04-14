package com.educacionit.infoar.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.educacionit.infoar.R
import com.educacionit.infoar.utils.applySystemPaddings
import com.educacionit.infoar.presentation.fragments.LoginFragment
import com.educacionit.infoar.presentation.fragments.communication.LoginListener

class MainActivity : AppCompatActivity(), LoginListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applySystemPaddings(findViewById(R.id.main))

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, LoginFragment.newInstance(this))
            .commit()
    }


    override fun onLoginSuccessful(usuario: String) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}