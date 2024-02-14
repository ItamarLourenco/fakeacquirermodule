package com.zigpay.fakeacquirermodule.usecase

import android.content.Context
import androidx.room.Room
import com.zigpay.fakeacquirermodule.domain.repository.FakeAcquirerCallback
import com.zigpay.fakeacquirermodule.application.FakeAcquirerApplication
import com.zigpay.fakeacquirermodule.application.FakeAppDatabase
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionMethod
import com.zigpay.fakeacquirermodule.domain.repository.FakeTransactionRepository
import com.zigpay.fakeacquirermodule.domain.repository.FakeTransactionRepositoryImpl
import com.zigpay.fakeacquirermodule.feature.activity.FakeAcquirerActivity
import com.zigpay.fakeacquirermodule.feature.activity.FakeAcquirerTransactionsActivity
import java.io.Serializable
import java.util.UUID

class FakeAcquirerSdk(val context: Context): Serializable {

    var fakeTransactionUseCase:FakeTransactionUseCase

    init {
        FakeAcquirerApplication.db = Room.databaseBuilder(context, FakeAppDatabase::class.java, "fake_acquirer")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

        fakeTransactionUseCase = FakeTransactionUseCaseImpl(
            repository = FakeTransactionRepositoryImpl(
                db = FakeAcquirerApplication.db.fakeTransactionDAO()
            )
        )
    }

    fun getTransactionById(id: String) : FakeTransaction = getTransactionById(UUID.fromString(id))
    fun getTransactionById(id: UUID) : FakeTransaction = fakeTransactionUseCase.getFakeTransaction(id)
    fun makeTransaction(price:Float, method: FakeTransactionMethod, callback: FakeAcquirerCallback) {
        FakeAcquirerApplication.callback = callback
        FakeAcquirerActivity.open(context, price, method);
    }
    fun getLastTransaction(): FakeTransaction? = fakeTransactionUseCase.getLastTransaction()
    fun showAllTransactions() = FakeAcquirerTransactionsActivity.open(context);
}