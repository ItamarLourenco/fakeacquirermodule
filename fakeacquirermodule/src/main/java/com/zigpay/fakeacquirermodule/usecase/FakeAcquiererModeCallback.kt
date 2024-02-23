package com.zigpay.fakeacquirermodule.usecase

import android.content.Context
import com.zigpay.fakeacquirermodule.application.FakeAcquirerApplication
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionAction
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionMethod
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionStatus
import com.zigpay.fakeacquirermodule.domain.repository.FakeAcquirerListener
import com.zigpay.fakeacquirermodule.feature.activity.FakeAcquirerActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FakeAcquirerModeCallback(
    val context: Context,
    val fakeTransactionUseCase: FakeTransactionUseCase
) {
    fun getTransactionByReferenceId(referenceId: String) : FakeTransaction? = fakeTransactionUseCase.getTransactionByReferenceId(referenceId)

    fun makeTransactionWithoutActivitySuccess(referenceId: String, price:Float, method: FakeTransactionMethod, listener: FakeAcquirerListener) {
        MainScope().launch {
            delay(3000)
            listener.transactionSuccess(
                fakeTransactionUseCase.saveFakeTransaction(
                    FakeTransaction(
                        action = FakeTransactionAction.WITHOUT_ACTIVITY,
                        status = FakeTransactionStatus.SUCCESS,
                        referenceId = referenceId,
                        price = price,
                        method = method,
                    )
                )
            )
        }
    }

    fun makeTransactionWithoutActivityException(referenceId: String, price:Float, method: FakeTransactionMethod, listener: FakeAcquirerListener) {
        MainScope().launch {
            delay(3000)
            listener.transactionSuccess(
                fakeTransactionUseCase.saveFakeTransaction(
                    FakeTransaction(
                        action = FakeTransactionAction.EXCEPTION,
                        status = FakeTransactionStatus.SUCCESS,
                        referenceId = referenceId,
                        price = price,
                        method = method,
                    )
                )
            )
            throw Exception("Ocorreu um erro - Exception")
        }
    }

    fun makeTransactionWithoutActivityThrowable(referenceId: String, price:Float, method: FakeTransactionMethod, listener: FakeAcquirerListener) {
        MainScope().launch {
            delay(3000)
            listener.transactionSuccess(
                fakeTransactionUseCase.saveFakeTransaction(
                    FakeTransaction(
                        action = FakeTransactionAction.THROWABLE,
                        status = FakeTransactionStatus.SUCCESS,
                        referenceId = referenceId,
                        price = price,
                        method = method,
                    )
                )
            )
            throw Throwable("Ocorreu um erro - Throwable")
        }
    }

    fun makeTransactionWithoutActivityFailed(referenceId: String, price:Float, method: FakeTransactionMethod, listener: FakeAcquirerListener) {
        MainScope().launch {
            delay(3000)
            listener.transactionFailed(
                fakeTransactionUseCase.saveFakeTransaction(
                    FakeTransaction(
                        action = FakeTransactionAction.WITHOUT_ACTIVITY,
                        status = FakeTransactionStatus.FAILED,
                        price = price,
                        referenceId = referenceId,
                        method = method,
                    )
                )
            )
        }
    }
}