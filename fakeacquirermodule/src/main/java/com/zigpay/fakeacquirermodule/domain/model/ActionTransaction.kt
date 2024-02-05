package com.zigpay.fakeacquirermodule.domain.model

enum class ActionTransaction {
    SUCCESS,
    FAILED,
    WITHOUT_RETURN,
    LOCKED,
    EXCEPTION,
    THROWABLE
}