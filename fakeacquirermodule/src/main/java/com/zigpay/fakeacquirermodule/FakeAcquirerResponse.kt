package com.zigpay.fakeacquirermodule

import android.content.Intent
import android.os.Build
import java.io.Serializable
import java.util.UUID

enum class StatusTransaction {
    SUCCESS,
    FAILED,
}


class FakeAcquirerResponse(val price: Float?,
                           val status: StatusTransaction?) : Serializable {

    companion object {
        fun getData(data: Intent): FakeAcquirerResponse? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            data.getSerializableExtra("result", FakeAcquirerResponse::class.java)
        } else {
            data.getSerializableExtra("result") as FakeAcquirerResponse
        }
    }


    val id: String = UUID.randomUUID().toString()

    override fun toString(): String {
        return "FakeAcquirerResponse(" +
                "id=$id, " +
                "price=$price, " +
                "status=$status" + ")"
    }

}