package com.kenstarry.houseops_revamped.core.data.repository

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.core.app.NotificationCompat
import androidx.core.graphics.drawable.IconCompat
import com.google.android.gms.location.LocationServices
import com.kenstarry.houseops_revamped.R
import com.kenstarry.houseops_revamped.core.domain.repository.LocationClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import okhttp3.internal.notify

class LocationService : Service() {

    //  making sure that if one job fails, the others keep running
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private lateinit var locationClient: LocationClient

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        locationClient = LocationClientImpl(
            applicationContext,
            LocationServices.getFusedLocationProviderClient(applicationContext)
        )
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when (intent?.action) {
            ACTION_START_LOCATION_TRACKING -> startTracking()
            ACTION_STOP_LOCATION_TRACKING -> stopTracking()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    private fun startTracking() {
        //  create a foreground service notification
        val notification = NotificationCompat.Builder(this, "Location")
            .setContentTitle("HouseOps is tracking your location...")
            .setContentText("Location: null")
            .setSmallIcon(R.drawable.baseline_location_on_24)
            .setOngoing(true)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //  launch our callback flow together with the location client
        locationClient.getLocationUpdates(10000L, onResponse = {})
            .catch { e -> Log.d("location", "Error: $e") }
            .onEach { location ->
                val latitude = location.latitude
                val longitude = location.longitude
                val updatedNotification = notification
                    .setContentText("Location is ($latitude, $longitude)")

                //  Notify our notification to make changes
                notificationManager.notify(1, updatedNotification.build())
            }
            .launchIn(serviceScope)

        startForeground(1, notification.build())
    }

    private fun stopTracking() {

        //  remove the notification
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        //  we should automatically stop tracking location when the service is destroyed
        serviceScope.cancel()
    }

    companion object {
        const val ACTION_START_LOCATION_TRACKING = "ACTION START LOCATION TRACKING"
        const val ACTION_STOP_LOCATION_TRACKING = "ACTION STOP LOCATION TRACKING"
    }
}