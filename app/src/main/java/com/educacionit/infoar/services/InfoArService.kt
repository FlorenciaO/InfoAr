package com.educacionit.infoar.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.educacionit.infoar.InfoArApp.Companion.CHANNEL_ID
import com.educacionit.infoar.R

class InfoArService: Service() {

    companion object {
        const val NOTIFICATION_SERVICE_ID = 1
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> {
                Log.d("ServicioTesting", "Servicio está iniciando...")
                start()
            }
            Actions.STOP.toString() -> {
                Log.d("ServicioTesting", "Servicio está deteniendose...")
                // stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
            }
            else -> {
                Log.d("ServicioTesting", "No hacer nada")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        // Creamos la notificacion
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Está corriendo el servicio")
            .setContentText("Mantiene activa la aplicación, incluso si la matamos.")
            .build()

        startForeground(NOTIFICATION_SERVICE_ID, notification)

        // Iniciamos la operacion en background
    }

    enum class Actions {
        START, STOP
    }
}