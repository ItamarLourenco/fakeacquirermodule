package com.zigpay.fakeacquirermodule.application

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.zigpay.fakeacquirermodule.domain.repository.FakeAcquirerCallback


class FakeAcquirerApplication: Application() {
    companion object    {
        lateinit var callback: FakeAcquirerCallback
        lateinit var db: FakeAppDatabase
    }
}