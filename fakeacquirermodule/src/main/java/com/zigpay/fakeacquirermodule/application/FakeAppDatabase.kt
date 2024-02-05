package com.zigpay.fakeacquirermodule.application

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.domain.repository.FakeTransactionDAO
import java.util.Date

@Database(entities = [FakeTransaction::class], version = 2)
@TypeConverters(Converters::class)
abstract class FakeAppDatabase : RoomDatabase() {
    abstract fun fakeTransactionDAO(): FakeTransactionDAO
}

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}