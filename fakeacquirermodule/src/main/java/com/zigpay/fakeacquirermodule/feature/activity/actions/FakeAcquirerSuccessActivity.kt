package com.zigpay.fakeacquirermodule.feature.activity.actions

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.zigpay.fakeacquirermodule.application.FakeAcquirerApplication
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionAction
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionStatus
import com.zigpay.fakeacquirermodule.feature.activity.FakeAcquirerActivityBase
import com.zigpay.fakeacquirermodule.ui.theme.FakeAcquirerProjectTheme
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FakeAcquirerSuccessActivity : FakeAcquirerActivityBase(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainScope().launch {
            delay(2000)
            val fakeTransaction = getFakeTransaction()
            fakeTransaction.action = FakeTransactionAction.SUCCESS
            fakeTransaction.status = FakeTransactionStatus.SUCCESS
            fakeTransactionUseCase.saveFakeTransaction(fakeTransaction)
            FakeAcquirerApplication.listener.transactionSuccess(fakeTransaction)
            finish()
        }

        setContent {
            FakeAcquirerProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    InitViewSuccess()
                }
            }
        }
    }
}


@Composable
fun InitViewSuccess() {
    val context: Context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Text(text = "Fluxo: Sucesso - Pagamento: Sucesso", color = Color.Blue)
        }
    }
}