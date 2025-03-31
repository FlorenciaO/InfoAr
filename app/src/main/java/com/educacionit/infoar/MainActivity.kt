package com.educacionit.infoar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.educacionit.infoar.HomeActivity.Companion.USER_PARAM_EXTRA
import com.educacionit.infoar.fragments.LoginFragment
import com.educacionit.infoar.fragments.TermsAndConditionsFragment
import com.educacionit.infoar.fragments.communication.LoginListener

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
        val intent = Intent(this, HomeActivity::class.java).apply {
            putExtra(USER_PARAM_EXTRA, usuario)
        }
        startActivity(intent)
        finish()
    }
}