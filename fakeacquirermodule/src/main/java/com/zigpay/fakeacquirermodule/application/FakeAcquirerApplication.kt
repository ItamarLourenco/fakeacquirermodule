package com.zigpay.fakeacquirermodule.application

import android.app.Application
import com.zigpay.fakeacquirermodule.domain.repository.FakeAcquirerCallback


class FakeAcquirerApplication: Application() {
    companion object    {
        lateinit var callback: FakeAcquirerCallback
    }

    override fun onCreate() {
        super.onCreate()
    }
}