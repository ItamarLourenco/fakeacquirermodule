package com.zigpay.fakeacquirermodule.domain.model

enum class FakeTransactionAction {
    SUCCESS,
    FAILED,
    WITHOUT_RETURN,
    LOCKED,
    FINISH_FIRST_CALLBACK,
    EXCEPTION,
    THROWABLE,
    WITHOUT_ACTIVITY
}