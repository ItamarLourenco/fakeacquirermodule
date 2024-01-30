package com.zigpay.fakeacquirermodule

interface FakeAcquirerCallback {
    fun transactionSuccess(fakeAcquirerResponse: FakeAcquirerResponse?)
    fun transactionFailed(fakeAcquirerResponse: FakeAcquirerResponse?)
}