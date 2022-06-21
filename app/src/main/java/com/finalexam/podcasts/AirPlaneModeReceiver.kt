package com.finalexam.podcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class AirPlaneModeReceiver:  BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isAirplaneModeOn: Boolean = intent?.getBooleanExtra("state", false) ?: return

        if(isAirplaneModeOn){
            Toast.makeText(context, "airplane mode is enabled", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(context, "airplane mode is disabled", Toast.LENGTH_LONG).show()
        }
    }
}