package com.zigpay.fakeacquirermodule.usecase

import android.content.Context
import com.zigpay.fakeacquirermodule.domain.repository.FakeAcquirerListener
import com.zigpay.fakeacquirermodule.application.FakeAcquirerApplication
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionAction
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionMethod
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionStatus
import com.zigpay.fakeacquirermodule.domain.repository.FakeTransactionRepositoryImpl
import com.zigpay.fakeacquirermodule.feature.activity.FakeAcquirerActivity
import com.zigpay.fakeacquirermodule.feature.activity.FakeAcquirerTransactionsActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.Serializable

class FakeAcquirerSdk(private val context: Context): Serializable {

    private var fakeTransactionUseCase: FakeTransactionUseCase

    init {
        FakeAcquirerApplication.initDB(context)
        fakeTransactionUseCase = FakeTransactionUseCaseImpl(
            repository = FakeTransactionRepositoryImpl(
                db = FakeAcquirerApplication.db.fakeTransactionDAO()
            )
        )
    }

    var modeActivity = FakeAcquirerModeActivity(context)
    var modeCallback = FakeAcquirerModeCallback(
        context = context,
        fakeTransactionUseCase = fakeTransactionUseCase
    )

    fun showAllTransactions() = FakeAcquirerTransactionsActivity.open(context);
}