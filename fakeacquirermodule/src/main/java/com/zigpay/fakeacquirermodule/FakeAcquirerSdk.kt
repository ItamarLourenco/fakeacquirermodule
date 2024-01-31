package com.zigpay.fakeacquirermodule

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import java.io.Serializable

class FakeAcquirerSdk(val context: Context): Serializable {

    fun makeTransaction(callback: FakeAcquirerCallback) {
        FakeAcquirerApplication.callback = callback
        val intent = Intent(context, FakeAcquirerActivity::class.java)
        context.startActivity(intent)
    }
}