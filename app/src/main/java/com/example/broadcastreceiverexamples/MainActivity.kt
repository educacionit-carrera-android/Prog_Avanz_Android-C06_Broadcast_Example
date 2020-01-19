package com.example.broadcastreceiverexamples

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MainActivity : AppCompatActivity() {

    lateinit var btnSendLocalBroadcast: Button
    val aireplaneModeReceiver = AirplaneModeReceiver()
    val myReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "CustomReceiver") {
                Log.d("Receiver", "CustomReceiver recibido")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSendLocalBroadcast = findViewById(R.id.btnSendLocalBroadcast)
        btnSendLocalBroadcast.setOnClickListener {
            LocalBroadcastManager.getInstance(this).sendBroadcast(
                Intent("CustomReceiver")
            )
        }
    }

    override fun onResume() {
        super.onResume()

        doLocalRegister()
        registerReceiver(aireplaneModeReceiver, IntentFilter("android.intent.action.AIRPLANE_MODE"))
//        doRegisterDinamico()
    }

    private fun doLocalRegister() {
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(
                myReceiver,
                IntentFilter("CustomReceiver")
            )
    }

    private fun doRegisterDinamico() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)

        registerReceiver(aireplaneModeReceiver, intentFilter)
    }

    override fun onStop() {
        unregisterReceiver(aireplaneModeReceiver)
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver)

        super.onStop()
    }
}
