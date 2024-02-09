package com.zigpay.fakeacquirermodule.domain.model

enum class FakeTransactionAction {
    SUCCESS,
    FAILED,
    WITHOUT_RETURN,
    LOCKED,
    EXCEPTION,
    THROWABLE
}