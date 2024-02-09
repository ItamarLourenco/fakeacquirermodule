package com.zigpay.fakeacquirermodule.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date
import java.util.UUID


@Entity(tableName = "fake_transaction")
data class FakeTransaction(
    @PrimaryKey val uid: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "price") val price: Float,
    @ColumnInfo(name = "action") var action: FakeTransactionAction,
    @ColumnInfo(name = "status") var status: FakeTransactionStatus,
    @ColumnInfo(name = "method") val method: FakeTransactionMethod,
    @ColumnInfo(name = "created_at") val created_at: Date,
    @ColumnInfo(name = "updated_at") val updated_at: Date
): Serializable {
    constructor(price: Float,
                action: FakeTransactionAction,
                statusTransaction: FakeTransactionStatus,
                method: FakeTransactionMethod) : this(
        uid = UUID.randomUUID(),
        price = price,
        action = action,
        status = statusTransaction,
        method = method,
        created_at = Date(),
        updated_at = Date()
    )

    constructor(price: Float,
                method: FakeTransactionMethod) : this(
        uid = UUID.randomUUID(),
        price = price,
        action = FakeTransactionAction.SUCCESS,
        status = FakeTransactionStatus.SUCCESS,
        method = method,
        created_at = Date(),
        updated_at = Date()
    )
}