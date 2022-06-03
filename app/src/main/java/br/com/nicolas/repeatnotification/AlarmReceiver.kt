package br.com.nicolas.repeatnotification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendReminderNotification(applicationContext = context, channelId = "2")
        ReminderManager.startReminder(context.applicationContext)
    }
}

fun NotificationManager.sendReminderNotification(
    applicationContext: Context,
    channelId: String
) {
    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(
        applicationContext,
        1,
        contentIntent,
        PendingIntent.FLAG_CANCEL_CURRENT
    )

    val builder = NotificationCompat.Builder(
        applicationContext, channelId
    ).setContentTitle(applicationContext.getString(R.string.app_name))
        .setContentText(applicationContext.getString(R.string.app_name))
        .setSmallIcon(R.drawable.ic_launcher_background)
        .setStyle(NotificationCompat.BigTextStyle().bigText("Description"))
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)

    notify(NOTIFICATION_ID, builder.build())
}

const val NOTIFICATION_ID = 1