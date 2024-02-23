package com.zigpay.fakeacquirermodule.feature.activity.actions

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zigpay.fakeacquirermodule.application.FakeAcquirerApplication
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionAction
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionMethod
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionStatus
import com.zigpay.fakeacquirermodule.feature.activity.FakeAcquirerActivity
import com.zigpay.fakeacquirermodule.feature.activity.FakeAcquirerActivityBase
import com.zigpay.fakeacquirermodule.ui.theme.FakeAcquirerProjectTheme
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FakeAcquirerGetTransactionByReferenceIdActivity : FakeAcquirerActivityBase(){

    companion object {
        fun open(context: Context, referenceId:String) {
            context.startActivity(Intent(context, FakeAcquirerGetTransactionByReferenceIdActivity::class.java).also {
                it.putExtra("reference_id", referenceId)
            })
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainScope().launch {
            delay(2000)
            FakeAcquirerApplication.listener.transactionSuccess(
                fakeTransactionUseCase.getTransactionByReferenceId(getReferenceId())
            )
            finish()
        }

        setContent {
            FakeAcquirerProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    InitViewGetTransactionByReferenceId()
                }
            }
        }
    }
}


@Composable
fun InitViewGetTransactionByReferenceId() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Text(text = "Buscando Transição por Reference Id", color = Color.Blue)
        }
    }
}