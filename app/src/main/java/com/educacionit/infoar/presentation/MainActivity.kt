package com.educacionit.infoar.presentation

import android.Manifest
import android.app.AlarmManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.educacionit.infoar.R
import com.educacionit.infoar.utils.applySystemPaddings
import com.educacionit.infoar.presentation.fragments.LoginFragment
import com.educacionit.infoar.presentation.fragments.communication.LoginListener

class MainActivity : AppCompatActivity(), LoginListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        applySystemPaddings(findViewById(R.id.main))

        requestPermissionIfRequired()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view, LoginFragment.newInstance(this))
            .commit()
    }

    private fun requestPermissionIfRequired() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
                //no pedirlo
            } else {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    1000
                    )
            }
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
                // Prompt the user to allow the permission manually
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                startActivity(intent)
            }
        } else {
            //no es necesario pedir el permiso
            Toast.makeText(this, "Ya están otorgados los permisos", Toast.LENGTH_LONG).show()
        }
    }

    override fun onLoginSuccessful(usuario: String) {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}