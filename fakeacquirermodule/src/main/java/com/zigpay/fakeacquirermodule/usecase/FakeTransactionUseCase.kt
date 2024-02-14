package com.zigpay.fakeacquirermodule.usecase

import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.domain.repository.FakeTransactionRepository
import java.util.UUID


interface FakeTransactionUseCase{
    fun saveFakeTransaction(fakeTransaction : FakeTransaction): FakeTransaction
    fun getAllFakeTransactions(): List<FakeTransaction>
    fun getFakeTransaction(byId: UUID): FakeTransaction
    fun getLastTransaction(): FakeTransaction?
}


class FakeTransactionUseCaseImpl(private val repository: FakeTransactionRepository): FakeTransactionUseCase {
    override fun saveFakeTransaction(fakeTransaction : FakeTransaction) : FakeTransaction = repository.saveFakeTransaction(
        fakeTransaction = fakeTransaction
    )
    override fun getAllFakeTransactions(): List<FakeTransaction> = repository.getAllFakeTransactions()
    override fun getFakeTransaction(byId: UUID): FakeTransaction = repository.getFakeTransaction(byId)
    override fun getLastTransaction(): FakeTransaction? = repository.getLastTransaction()
}