package com.zigpay.fakeacquirermodule.domain.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import java.util.UUID

@Dao
interface FakeTransactionDAO {
    @Query("SELECT * FROM fake_transaction ORDER BY created_at DESC")
    fun getAll(): List<FakeTransaction>

    @Query("SELECT * FROM fake_transaction WHERE uid = :uuid")
    fun getById(uuid: UUID): FakeTransaction

    @Insert
    fun insertAll(vararg users: FakeTransaction)

    @Delete
    fun delete(user: FakeTransaction)


    @Query("SELECT * FROM fake_transaction ORDER BY created_at DESC LIMIT 1")
    fun getLastTransaction(): FakeTransaction
}