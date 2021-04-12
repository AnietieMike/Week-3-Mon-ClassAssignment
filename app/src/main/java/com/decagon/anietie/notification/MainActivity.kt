package com.decagon.anietie.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

const val CHANNEL_ID = "channel"
lateinit var notificationButton: Button
lateinit var nextActivityButton: Button

class MainActivity : AppCompatActivity() {

    lateinit var notificationButton: Button
    lateinit var nextActivityButton: Button

    val notificationId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationButton = findViewById(R.id.notification_button)
        nextActivityButton = findViewById(R.id.next_button)

        nextActivityButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("Inactive", "Inactive")
            startActivity(intent)
        }

        notificationButton.setOnClickListener {
            showNotification()
        }
    }

    fun showNotification() {
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                    CHANNEL_ID,
                    getString(R.string.channel_name),
                    NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = getString(R.string.channel_description)
            mNotificationManager.createNotificationChannel(channel)
        }
        val mBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle(getString(R.string.notification_title)) // title for notification
                .setContentText(getString(R.string.notification_message))// message for notification
                .setAutoCancel(true) // clear notification after click

        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("Active", "Active")
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        mBuilder.setContentIntent(pendingIntent)
        mNotificationManager.notify(0, mBuilder.build())
    }

}