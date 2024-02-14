package com.zigpay.fakeacquirermodule.domain.repository

import com.zigpay.fakeacquirermodule.application.FakeAppDatabase
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import java.util.UUID

interface FakeTransactionRepository{
    fun saveFakeTransaction(fakeTransaction : FakeTransaction): FakeTransaction

    fun getFakeTransaction(byId: UUID): FakeTransaction

    fun getAllFakeTransactions(): List<FakeTransaction>
    fun getLastTransaction(): FakeTransaction?
}

class FakeTransactionRepositoryImpl(private val db: FakeTransactionDAO): FakeTransactionRepository {
    override fun saveFakeTransaction(fakeTransaction : FakeTransaction): FakeTransaction {
        db.insertAll(fakeTransaction)
        return getFakeTransaction(byId = fakeTransaction.uid)
    }
    override fun getFakeTransaction(byId: UUID): FakeTransaction = db.getById(byId)

    override fun getAllFakeTransactions(): List<FakeTransaction> = db.getAll()
    override fun getLastTransaction(): FakeTransaction? = db.getLastTransaction()
}