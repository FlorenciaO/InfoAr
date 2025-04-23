package com.educacionit.infoar.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.educacionit.infoar.InfoArApp.Companion.CHANNEL_ID
import com.educacionit.infoar.R

class InfoArService: Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            Actions.START.toString() -> start()
            Actions.STOP.toString() -> {
                Log.d("InfoArService", "Service stopped")
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {
        Log.d("InfoArService", "Service started")
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Servicio está corriendo")
            .setContentText("Esto persiste la aplicación, incluso si la matamos, hasta que detenemos el servicio")
            .build()
        startForeground(2, notification)
    }

    enum class Actions {
        START, STOP

    }
}