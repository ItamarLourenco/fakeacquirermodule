package com.zigpay.fakeacquirermodule.domain.model

import java.io.Serializable
import java.util.UUID


class FakeTransaction(val price: Float?,
                      val status: StatusTransaction?) : Serializable {

    val id: String = UUID.randomUUID().toString()

    override fun toString(): String {
        return "FakeAcquirerResponse(" +
                "id=$id, " +
                "price=$price, " +
                "status=$status" + ")"
    }

}