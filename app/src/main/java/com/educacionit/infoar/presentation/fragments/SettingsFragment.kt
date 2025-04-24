package com.educacionit.infoar.presentation.fragments

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.app.PendingIntent.FLAG_NO_CREATE
import android.app.PendingIntent.getBroadcast
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.educacionit.infoar.broadcast_receiver.AlarmNotificationBroadcastReceiver
import com.educacionit.infoar.databinding.FragmentSettingsBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("ScheduleExactAlarm")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)

        val alarmManager = requireContext().getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), AlarmNotificationBroadcastReceiver::class.java)
        val pendingIntent1 = getBroadcast(
            requireContext(),
            1,
            intent,
            FLAG_IMMUTABLE
        )
        val pendingIntent2 = getBroadcast(
            requireContext(),
            2,
            intent,
            FLAG_IMMUTABLE
        )
        val pendingIntent3 = getBroadcast(
            requireContext(),
            3,
            intent,
            FLAG_IMMUTABLE
        )

        binding.setAlarmButton.setOnClickListener {

                alarmManager.setExact(
                    AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    SystemClock.elapsedRealtime() + 5000, // 5 segundos
                    pendingIntent1
                )
        }

        binding.setExactAlarmButton.setOnClickListener {
            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Time")
                .build()

            picker.show(requireActivity().supportFragmentManager, "TAG")

            picker.addOnPositiveButtonClickListener {
                val hour = picker.hour
                val minute = picker.minute
                val calendar = Calendar.getInstance()
                calendar[Calendar.HOUR_OF_DAY] = hour
                calendar[Calendar.MINUTE] = minute
                calendar[Calendar.SECOND] = 0
                calendar[Calendar.MILLISECOND] = 0

                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent2
                )
            }
        }

        binding.setRepeatingAlarmButton.setOnClickListener {
            val picker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Time")
                .build()

            picker.show(requireActivity().supportFragmentManager, "TAG")

            picker.addOnPositiveButtonClickListener {
                val hour = picker.hour
                val minute = picker.minute
                val calendar = Calendar.getInstance()
                calendar[Calendar.HOUR_OF_DAY] = hour
                calendar[Calendar.MINUTE] = minute
                calendar[Calendar.SECOND] = 0
                calendar[Calendar.MILLISECOND] = 0

                alarmManager.setInexactRepeating(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    5000, // 5 segundos
                    pendingIntent3
                )
            }
        }


        binding.cancelAlarmButton.setOnClickListener {
            if (getBroadcast(
                    requireContext(),
                    1,
                    intent,
                    FLAG_NO_CREATE or FLAG_IMMUTABLE
                ) != null
            ) {
                alarmManager.cancel(pendingIntent1)
                Snackbar.make(binding.root, "Se canceló la alarma programada 1", Snackbar.LENGTH_LONG)
                    .show()
            } else {
                Snackbar.make(binding.root, "No hay alarma programada 1", Snackbar.LENGTH_LONG)
                    .show()
            }
            if (getBroadcast(
                    requireContext(),
                    2,
                    intent,
                    FLAG_NO_CREATE or FLAG_IMMUTABLE
                ) != null
            ) {
                alarmManager.cancel(pendingIntent1)
                Snackbar.make(binding.root, "Se canceló la alarma programada 2", Snackbar.LENGTH_LONG)
                    .show()
            } else {
                Snackbar.make(binding.root, "No hay alarma programada 2", Snackbar.LENGTH_LONG)
                    .show()
            }
            if (getBroadcast(
                    requireContext(),
                    3,
                    intent,
                    FLAG_NO_CREATE or FLAG_IMMUTABLE
                ) != null
            ) {
                alarmManager.cancel(pendingIntent1)
                Snackbar.make(binding.root, "Se canceló la alarma programada 3", Snackbar.LENGTH_LONG)
                    .show()
            } else {
                Snackbar.make(binding.root, "No hay alarma programada 3", Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        return binding.root
    }
}