package com.zigpay.fakeacquirermodule.usecase

import android.content.Context
import com.zigpay.fakeacquirermodule.domain.repository.FakeAcquirerCallback
import com.zigpay.fakeacquirermodule.application.FakeAcquirerApplication
import com.zigpay.fakeacquirermodule.application.FakeAppDatabase
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.domain.model.TypeTransaction
import com.zigpay.fakeacquirermodule.domain.repository.FakeTransactionRepository
import com.zigpay.fakeacquirermodule.feature.activity.FakeAcquirerActivity
import java.io.Serializable
import java.util.UUID

class FakeAcquirerSdk(val context: Context): Serializable {

    fun getTransactionById(id: String) : FakeTransaction = getTransactionById(UUID.fromString(id))
    fun getTransactionById(id: UUID) : FakeTransaction = FakeTransactionUseCase(FakeTransactionRepository(
        FakeAcquirerApplication.db.fakeTransactionDAO()
    )).getFakeTransaction(id)

    fun makeTransaction(price:Float, type: TypeTransaction, callback: FakeAcquirerCallback) {
        FakeAcquirerApplication.callback = callback
        FakeAcquirerActivity.open(context, price, type);
    }
}