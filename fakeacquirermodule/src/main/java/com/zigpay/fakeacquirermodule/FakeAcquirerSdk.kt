package com.zigpay.fakeacquirermodule

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class FakeAcquirerSdk(val activity: ComponentActivity) {
    var resultLauncher: ActivityResultLauncher<Intent>
    lateinit var callback: FakeAcquirerCallback

    init {
        resultLauncher = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            result.data?.let {
                val data = FakeAcquirerResponse.getData(it)
                when (data?.status){
                    StatusTransaction.SUCCESS -> callback.transactionSuccess(data)
                    StatusTransaction.FAILED -> callback.transactionFailed(data)
                    else -> callback.transactionFailed(data)
                }
            }
        }
    }

    fun makeTransaction() {
        val intent = Intent(activity, FakeAcquirerActivity::class.java)
        resultLauncher.launch(intent)
    }
}