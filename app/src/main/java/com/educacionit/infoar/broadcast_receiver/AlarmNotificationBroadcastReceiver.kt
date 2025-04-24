package com.educacionit.infoar.broadcast_receiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.educacionit.infoar.InfoArApp.Companion.CHANNEL_ID
import com.educacionit.infoar.R
import com.educacionit.infoar.presentation.HomeActivity

class AlarmNotificationBroadcastReceiver : BroadcastReceiver() {

    companion object {
        const val NOTIFICATION_ID = 3
    }

    override fun onReceive(context: Context, intent: Intent?) {
        createNotification(context)
    }

    private fun createNotification(context: Context) {
        val intent = Intent(context, HomeActivity::class.java).apply {
            // No se van a crear nuevas instancias de la actividad
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val flag = PendingIntent.FLAG_IMMUTABLE

        val pendingIntent = PendingIntent.getActivity(context, 0, intent, flag)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Esta es un Notificación Programada")
            .setContentText("Programada para el día de hoy")
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText("Programada para el día de hoy. Descripción expandida en caso de tener un texto muy largo que se deba mostrar en varias lineas."))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager: NotificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)
    }
}