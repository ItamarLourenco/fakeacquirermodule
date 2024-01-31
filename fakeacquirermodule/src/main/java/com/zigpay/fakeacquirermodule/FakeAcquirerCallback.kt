package com.zigpay.fakeacquirermodule

import android.os.Parcelable
import java.io.Serializable

interface FakeAcquirerCallback: Serializable {
    companion object {
        const val PUT_EXTRA_NAME = "callback"
    }
    fun transactionSuccess(fakeAcquirerResponse: FakeAcquirerResponse?)
    fun transactionFailed(fakeAcquirerResponse: FakeAcquirerResponse?)
}