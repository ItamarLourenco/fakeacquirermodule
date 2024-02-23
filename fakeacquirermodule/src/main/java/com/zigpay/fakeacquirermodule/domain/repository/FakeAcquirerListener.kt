package com.zigpay.fakeacquirermodule.domain.repository

import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import java.io.Serializable

interface FakeAcquirerListener: Serializable {
    fun transactionSuccess(fakeAcquirerResponse: FakeTransaction?)
    fun transactionFailed(fakeAcquirerResponse: FakeTransaction?)
}