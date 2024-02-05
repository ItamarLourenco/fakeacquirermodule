package com.zigpay.fakeacquirermodule.domain.repository

import com.zigpay.fakeacquirermodule.application.FakeAppDatabase
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import java.util.UUID

class FakeTransactionRepository(private val db: FakeTransactionDAO) {

    fun saveFakeTransaction(fakeTransaction : FakeTransaction): FakeTransaction {
        db.insertAll(fakeTransaction)
        return getFakeTransaction(byId = fakeTransaction.uid)
    }

    fun getFakeTransaction(byId: UUID): FakeTransaction = db.getById(byId)

    fun getAllFakeTransactions(): List<FakeTransaction> = db.getAll()
}