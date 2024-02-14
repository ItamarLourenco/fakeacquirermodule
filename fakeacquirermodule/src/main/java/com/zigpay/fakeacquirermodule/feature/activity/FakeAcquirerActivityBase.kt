package com.zigpay.fakeacquirermodule.feature.activity

import android.content.Intent
import android.os.Build
import androidx.activity.ComponentActivity
import com.zigpay.fakeacquirermodule.application.FakeAcquirerApplication
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionMethod
import com.zigpay.fakeacquirermodule.domain.repository.FakeTransactionRepository
import com.zigpay.fakeacquirermodule.domain.repository.FakeTransactionRepositoryImpl
import com.zigpay.fakeacquirermodule.usecase.FakeTransactionUseCase
import com.zigpay.fakeacquirermodule.usecase.FakeTransactionUseCaseImpl


open class FakeAcquirerActivityBase: ComponentActivity() {
    companion object {
        fun open(fakeAcquirerActivityBase: FakeAcquirerActivityBase, myClass: Class<out FakeAcquirerActivityBase>, fakeTransaction: FakeTransaction){
            fakeAcquirerActivityBase.startActivity(
                Intent(fakeAcquirerActivityBase, myClass).also {
                    it.putExtra(FakeTransaction::class.java.simpleName, fakeTransaction)
                }
            )
            fakeAcquirerActivityBase.finish()
        }
    }

    val fakeTransactionUseCase:FakeTransactionUseCase = FakeTransactionUseCaseImpl(
        FakeTransactionRepositoryImpl(FakeAcquirerApplication.db.fakeTransactionDAO())
    )

    fun getFakeTransaction() : FakeTransaction = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        intent.getSerializableExtra(FakeTransaction::class.java.simpleName, FakeTransaction::class.java) ?: FakeTransaction(0f, FakeTransactionMethod.CREDIT)
    } else {
        intent.getSerializableExtra(FakeTransaction::class.java.simpleName) as FakeTransaction
    }
}