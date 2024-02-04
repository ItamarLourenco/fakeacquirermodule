package com.zigpay.fakeacquirermodule.usecase

import android.content.Context
import android.content.Intent
import com.zigpay.fakeacquirermodule.domain.repository.FakeAcquirerCallback
import com.zigpay.fakeacquirermodule.application.FakeAcquirerApplication
import com.zigpay.fakeacquirermodule.feature.activity.FakeAcquirerActivity
import java.io.Serializable

class FakeAcquirerSdk(val context: Context): Serializable {
    fun makeTransaction(callback: FakeAcquirerCallback) {
        FakeAcquirerApplication.callback = callback
        val intent = Intent(context, FakeAcquirerActivity::class.java)
        context.startActivity(intent)
    }
}