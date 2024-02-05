package com.zigpay.fakeacquirermodule.domain.repository

import com.zigpay.fakeacquirermodule.application.FakeAppDatabase
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import java.util.UUID

class FakeTransactionRepository(private val db: FakeTransactionDAO) {

    fun saveFakeTransaction(fakeTransaction : FakeTransaction): FakeTransaction {
        db.insertAll(fakeTransaction)
        return getFakeTransaction(withId = fakeTransaction.uid)
    }

    fun getFakeTransaction(withId: UUID): FakeTransaction {
        return db.getById(withId)
    }
}