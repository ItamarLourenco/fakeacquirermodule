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
    @ColumnInfo(name = "action") var action: ActionTransaction,
    @ColumnInfo(name = "type") val type: TypeTransaction,
    @ColumnInfo(name = "created_at") val created_at: Date,
    @ColumnInfo(name = "updated_at") val updated_at: Date
): Serializable {
    constructor(price: Float, statusTransaction: ActionTransaction, typeTransaction: TypeTransaction) : this(
        UUID.randomUUID(),
        price,
        statusTransaction,
        typeTransaction,
        Date(),
        Date()
    )

    constructor(price: Float, typeTransaction: TypeTransaction) : this(
        UUID.randomUUID(),
        price,
        ActionTransaction.SUCCESS,
        typeTransaction,
        Date(),
        Date()
    )
}