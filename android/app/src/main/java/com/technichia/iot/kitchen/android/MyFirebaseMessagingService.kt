package com.technichia.iot.kitchen.android

import android.app.Notification
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import android.app.NotificationManager

import android.app.NotificationChannel
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MyFirebaseMessagingService : FirebaseMessagingService() {
    private var broadcaster: LocalBroadcastManager? = null
    private val processLater = false

    override fun onCreate() {
        broadcaster = LocalBroadcastManager.getInstance(this)
    }

    override fun onNewToken(token: String) {
        Log.d(TAG, "Notification Refreshed token: $token")

        getSharedPreferences("_", MODE_PRIVATE).edit().putString("fcm_token", token).apply()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        Log.d(TAG, "Notification From: ${remoteMessage.from}")


        if (/* Check if data needs to be processed by long running job */ processLater) {
            //scheduleJob()
            Log.d(TAG, "Notification executing schedule job")
        } else {
            // Handle message within 10 seconds
            handleNow(remoteMessage)
        }
    }

    private fun getChannel(): NotificationChannel {
        val channel = NotificationChannel(
            getString(R.string.default_notification_channel_id),
            getString(R.string.app_name),
            NotificationManager.IMPORTANCE_DEFAULT
        )
        channel.description = "Iot-channel to display from BE"
        return channel
    }

    private fun getNotificationBuilder(title: String?, content: String?): Notification {
        return NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id))
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).build()
    }

    private fun getNotificationManager(notification: Notification) {
        val manager = getSystemService(
            NotificationManager::class.java
        )
        manager.createNotificationChannel(getChannel())
        val mNotificationManager = NotificationManagerCompat.from(this)
        mNotificationManager.notify(Math.random().toInt(), notification)
    }

    private fun handleNow(remoteMessage: RemoteMessage) {
            remoteMessage.notification?.let {
                Log.d(TAG, "Notification data: ${remoteMessage.data}")
                getNotificationManager(
                    getNotificationBuilder(it.title, it.body)
                )
                val intent = Intent("MyData")
                intent.putExtra("message", remoteMessage.data["text"])
                broadcaster?.sendBroadcast(intent)
            }
    }

    companion object {
        private const val TAG = "MyFirebaseMessagingS"
    }
}