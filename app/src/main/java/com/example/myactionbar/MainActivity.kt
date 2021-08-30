package com.example.myactionbar

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    companion object{
        const val NOTIFICATION_CHANNEL_ID = "My channel"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tool:androidx.appcompat.widget.Toolbar = findViewById(R.id.menu)
        tool.setTitle("Action bar")
        tool.inflateMenu(R.menu.activity_menu)
        choosechannel()
        tool.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.download->{
                    allow()
                    Toast.makeText(this,"Downloading",Toast.LENGTH_LONG).show()
                }
                R.id.print->{
                    Toast.makeText(this,"Printing",Toast.LENGTH_LONG).show()
                }
            }
            true
        }
    }
    fun choosechannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel= NotificationChannel(NOTIFICATION_CHANNEL_ID,"My Title",
                NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(NOTIFICATION_SERVICE)as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    var count=0
    fun allow() {
        val builder = NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID)
        builder.setContentTitle("Dowwnload")
        builder.setContentText("Downloding...")
        builder.setSmallIcon(R.mipmap.ic_launcher)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE)as NotificationManager
        notificationManager.notify(NOTIFICATION_CHANNEL_ID,count,builder.build())
    }
}