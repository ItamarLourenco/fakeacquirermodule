package com.zigpay.fakeacquirerproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.zigpay.fakeacquirermodule.FakeAcquirerCallback
import com.zigpay.fakeacquirermodule.FakeAcquirerResponse
import com.zigpay.fakeacquirermodule.FakeAcquirerSdk
import com.zigpay.fakeacquirerproject.ui.theme.FakeAcquirerProjectTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
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
                    color = Color.Blue
                ) {
                    Greeting(rememberCoroutineScope())
                }
            }
        }
    }


    suspend fun teste(){
        val fakeAcquirerResponse = suspendCancellableCoroutine { c ->
            fakeAcquirerSdk.makeTransaction(object: FakeAcquirerCallback{
                override fun transactionSuccess(fakeAcquirerResponse: FakeAcquirerResponse?) {
                    c.resume(fakeAcquirerResponse)
                }

                override fun transactionFailed(fakeAcquirerResponse: FakeAcquirerResponse?) {
                    c.resume(fakeAcquirerResponse)
                }
            })
        }
        Log.i("DEBUG 123", fakeAcquirerResponse.toString())
    }
}

@Composable
fun Greeting(rememberCoroutineScope: CoroutineScope) {
    val mainActivity: MainActivity = LocalContext.current as MainActivity
    Button(
        modifier = Modifier.padding(8.dp),
        onClick = {
            rememberCoroutineScope.launch {
                mainActivity.teste()
            }
        }
    ) {
        Text(text = "Simular transição com sucesso")
    }
}

