package com.tapaafandi.core.sync.initializers

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.ForegroundInfo
import com.tapaafandi.core.R

private const val SyncNotificationId = 0
private const val SyncNotificationChannelID = "SyncNotificationChannel"

fun Context.syncForegroundInfo() = ForegroundInfo(SyncNotificationId, syncWorkNotification())

private fun Context.syncWorkNotification(): Notification {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            SyncNotificationChannelID,
            getString(R.string.sync_notification_channel_name),
            NotificationManager.IMPORTANCE_DEFAULT,
        ).apply {
            description = getString(R.string.sync_notification_channel_description)
        }
        val notificationManager: NotificationManager? =
            getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

        notificationManager?.createNotificationChannel(channel)
    }

    return NotificationCompat.Builder(this, SyncNotificationChannelID)
        .setSmallIcon(R.drawable.ic_videogame)
        .setContentTitle(getString(R.string.sync_notification_title))
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .build()
}