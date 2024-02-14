package com.zigpay.fakeacquirermodule.feature.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zigpay.fakeacquirermodule.application.FakeAcquirerApplication
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.domain.repository.FakeTransactionRepository
import com.zigpay.fakeacquirermodule.domain.repository.FakeTransactionRepositoryImpl
import com.zigpay.fakeacquirermodule.usecase.FakeTransactionUseCase
import com.zigpay.fakeacquirermodule.usecase.FakeTransactionUseCaseImpl

class FakeTransactionsViewModel: ViewModel() {
    private val fakeTransactionUseCase:FakeTransactionUseCase = FakeTransactionUseCaseImpl(
        repository = FakeTransactionRepositoryImpl(FakeAcquirerApplication.db.fakeTransactionDAO())
    )

    private val _fakeTransactions = MutableLiveData<List<FakeTransaction>>()
    val fakeTransactionList: LiveData<List<FakeTransaction>> get() = _fakeTransactions

    fun updateFakeTransactionList() {
        _fakeTransactions.value = fakeTransactionUseCase.getAllFakeTransactions()
    }
}