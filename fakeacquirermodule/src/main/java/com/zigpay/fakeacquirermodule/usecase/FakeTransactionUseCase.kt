package com.zigpay.fakeacquirermodule.usecase

import com.zigpay.fakeacquirermodule.application.FakeAppDatabase
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.domain.repository.FakeTransactionRepository
import java.util.UUID

class FakeTransactionUseCase(private val repository: FakeTransactionRepository) {
    fun saveFakeTransaction(fakeTransaction : FakeTransaction) : FakeTransaction {
        return repository.saveFakeTransaction(
            fakeTransaction = fakeTransaction
        )
    }

    fun getAllFakeTransactions(): List<FakeTransaction> {
        return repository.getAllFakeTransactions()
    }
}