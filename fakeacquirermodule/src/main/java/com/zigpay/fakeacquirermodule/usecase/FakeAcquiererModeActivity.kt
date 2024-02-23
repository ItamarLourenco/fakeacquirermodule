package com.zigpay.fakeacquirermodule.usecase

import android.content.Context
import com.zigpay.fakeacquirermodule.application.FakeAcquirerApplication
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionMethod
import com.zigpay.fakeacquirermodule.domain.repository.FakeAcquirerListener
import com.zigpay.fakeacquirermodule.feature.activity.FakeAcquirerActivity
import com.zigpay.fakeacquirermodule.feature.activity.actions.FakeAcquirerGetTransactionByReferenceIdActivity

class FakeAcquirerModeActivity(val context: Context) {
    fun makeTransaction(referenceId: String, price:Float, method: FakeTransactionMethod, listener: FakeAcquirerListener) {
        FakeAcquirerApplication.listener = listener
        FakeAcquirerActivity.open(
            context = context,
            referenceId = referenceId,
            price = price,
            method = method
        )
    }

    fun  getTransactionByReferenceId(referenceId: String, listener: FakeAcquirerListener) {
        FakeAcquirerApplication.listener = listener
        FakeAcquirerGetTransactionByReferenceIdActivity.open(context, referenceId)
    }
}