package com.zigpay.fakeacquirermodule

import android.content.Intent
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.util.UUID

enum class StatusTransaction {
    SUCCESS,
    FAILED,
}


class FakeAcquirerResponse(val price: Float?,
                           val status: StatusTransaction?) : Serializable {

    val id: String = UUID.randomUUID().toString()

    override fun toString(): String {
        return "FakeAcquirerResponse(" +
                "id=$id, " +
                "price=$price, " +
                "status=$status" + ")"
    }

}