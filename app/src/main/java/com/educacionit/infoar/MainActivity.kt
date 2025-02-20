package com.educacionit.infoar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.educacionit.infoar.fragments.LoginFragment
import com.educacionit.infoar.fragments.communication.LoginListener

class MainActivity : AppCompatActivity(), LoginListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applySystemPaddings(findViewById(R.id.main))

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, LoginFragment())
            .commit()
    }


    override fun onLoginSuccessful() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}