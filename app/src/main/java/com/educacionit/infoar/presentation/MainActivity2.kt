package com.educacionit.infoar.presentation

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.educacionit.infoar.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {

    private lateinit var varbtnObtenerNumero: Button
    private lateinit var boundService: MyBoundService
    private var mBound: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)

        Log.d("MainActivity2", "onCreate - Servicio status: ${mBound}")

        varbtnObtenerNumero = findViewById(R.id.btnGetNumber)
        varbtnObtenerNumero.setOnClickListener {
            if (mBound) {
                Toast.makeText(this, boundService.getRandomNumber().toString(), Toast.LENGTH_LONG)
                        .show()
            }
            startActivity(Intent(this, MainActivity::class.java))
            lifecycleScope.launch {
                delay(1000)
                Log.d("MainActivity2", "delay- Servicio status: ${mBound}")
                delay(1000)
                Log.d("MainActivity2", "delay- Servicio status: ${mBound}")
                delay(1000)
                Log.d("MainActivity2", "delay- Servicio status: ${mBound}")
                delay(1000)
                Log.d("MainActivity2", "delay- Servicio status: ${mBound}")
            }
            finish()
        }
    }

    private val connection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyBoundService.MyBinder
            boundService = binder.getService()
            mBound = true
            Log.d("MainActivity2", "onServiceConnected - Servicio status: ${mBound}")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
            Log.d("MainActivity2", "onServiceDisconnected - Servicio status: ${mBound}")
        }

        override fun onBindingDied(name: ComponentName?) {
            super.onBindingDied(name)
            Log.d("MainActivity2", "onBindingDied - Servicio status: ${mBound}")
        }
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this@MainActivity2, MyBoundService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity2", "onPause- Servicio status: ${mBound}")
    }

    override fun onStop() {
        super.onStop()
        unbindService(connection)
    }

    override fun onDestroy() {
        Log.d("MainActivity2", "onDestroy - Servicio status: ${mBound}")
        super.onDestroy()
    }
}
