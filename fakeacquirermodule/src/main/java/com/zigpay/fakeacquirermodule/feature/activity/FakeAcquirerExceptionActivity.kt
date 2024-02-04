package com.zigpay.fakeacquirermodule.feature.activity

import android.content.Context
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.zigpay.fakeacquirermodule.ui.theme.FakeAcquirerProjectTheme
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.zigpay.fakeacquirermodule.application.FakeAcquirerActivityBase
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FakeAcquirerExceptionActivity : FakeAcquirerActivityBase(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MainScope().launch {
            delay(2000)
            throw Exception("Ocorreu um apartada - Exception!")
        }

        setContent {
            FakeAcquirerProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    InitViewException()
                }
            }
        }
    }
}


@Composable
fun InitViewException() {
    val context: Context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Text(text = "Simulando transação com Exception", color = Color.Red)
        }
    }
}