package com.zigpay.fakeacquirermodule.application

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.zigpay.fakeacquirermodule.domain.repository.FakeAcquirerCallback


class FakeAcquirerApplication: Application() {
    companion object    {
        lateinit var callback: FakeAcquirerCallback
        lateinit var db: FakeAppDatabase

        fun initDB(context: Context) {
            db = Room.databaseBuilder(context, FakeAppDatabase::class.java, "fake_acquirer")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }

    }
}