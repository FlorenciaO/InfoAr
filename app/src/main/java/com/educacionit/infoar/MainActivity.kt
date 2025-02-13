package com.educacionit.infoar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.educacionit.infoar.fragments.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applySystemPaddings(findViewById(R.id.main))

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, LoginFragment())
            .commit()
    }

}