package com.zigpay.fakeacquirerproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.domain.repository.FakeAcquirerCallback
import com.zigpay.fakeacquirermodule.usecase.FakeAcquirerSdk
import com.zigpay.fakeacquirerproject.ui.theme.FakeAcquirerProjectTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class MainActivity : ComponentActivity() {

    lateinit var fakeAcquirerSdk: FakeAcquirerSdk

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fakeAcquirerSdk = FakeAcquirerSdk(this)

        setContent {
            FakeAcquirerProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Greeting(rememberCoroutineScope())
                }
            }
        }
    }


    suspend fun makeTransaction(): FakeTransaction? = suspendCancellableCoroutine { continuation ->
        fakeAcquirerSdk.makeTransaction(object: FakeAcquirerCallback {
            override fun transactionSuccess(fakeAcquirerResponse: FakeTransaction?) {
                continuation.resume(fakeAcquirerResponse)
            }

            override fun transactionFailed(fakeAcquirerResponse: FakeTransaction?) {
                continuation.resume(fakeAcquirerResponse)
            }
        })
    }

}

@Composable
fun Greeting(rememberCoroutineScope: CoroutineScope) {
    val mainActivity: MainActivity = LocalContext.current as MainActivity
    var log by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column {
            Button(
                onClick = {
                    rememberCoroutineScope.launch {
                        log = mainActivity.makeTransaction().toString()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(16.dp)
            ) {
                Text(text = "Simular transição com sucesso")
            }
            Text(text = log, textAlign = TextAlign.Center, fontSize = 12.sp)
        }
    }
}

