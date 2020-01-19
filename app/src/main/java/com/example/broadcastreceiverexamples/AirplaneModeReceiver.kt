package com.example.broadcastreceiverexamples

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirplaneModeReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "android.intent.action.AIRPLANE_MODE") {
            Toast.makeText(
                context!!, "Se modifico el estado del Modo de avion",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}