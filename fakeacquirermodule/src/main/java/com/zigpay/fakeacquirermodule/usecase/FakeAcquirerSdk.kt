package com.zigpay.fakeacquirermodule.usecase

import android.content.Context
import com.zigpay.fakeacquirermodule.domain.repository.FakeAcquirerCallback
import com.zigpay.fakeacquirermodule.application.FakeAcquirerApplication
import com.zigpay.fakeacquirermodule.domain.model.TypeTransaction
import com.zigpay.fakeacquirermodule.feature.activity.FakeAcquirerActivity
import java.io.Serializable

class FakeAcquirerSdk(val context: Context): Serializable {
    fun makeTransaction(price:Float, type: TypeTransaction, callback: FakeAcquirerCallback) {
        FakeAcquirerApplication.callback = callback
        FakeAcquirerActivity.open(context, price, type);
    }
}