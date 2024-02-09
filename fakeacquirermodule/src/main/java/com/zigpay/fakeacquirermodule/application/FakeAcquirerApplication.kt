package com.zigpay.fakeacquirermodule.application

import android.app.Application
import androidx.room.Room
import com.zigpay.fakeacquirermodule.domain.repository.FakeAcquirerCallback


class FakeAcquirerApplication: Application() {
    companion object    {
        lateinit var callback: FakeAcquirerCallback
        lateinit var db: FakeAppDatabase
    }

    override fun onCreate() {
        super.onCreate()

    }
}