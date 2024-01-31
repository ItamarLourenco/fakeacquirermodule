package com.zigpay.fakeacquirermodule

import android.app.Application

class FakeAcquirerApplication: Application() {
    companion object    {
        lateinit var callback: FakeAcquirerCallback
    }
}