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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.zigpay.fakeacquirermodule.FakeAcquirerCallback
import com.zigpay.fakeacquirermodule.FakeAcquirerResponse
import com.zigpay.fakeacquirermodule.FakeAcquirerSdk
import com.zigpay.fakeacquirerproject.ui.theme.FakeAcquirerProjectTheme

class MainActivity : ComponentActivity() {

    lateinit var fakeAcquirerSdk: FakeAcquirerSdk

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fakeAcquirerSdk = FakeAcquirerSdk(this)
        fakeAcquirerSdk.callback = object: FakeAcquirerCallback{
            override fun transactionSuccess(fakeAcquirerResponse: FakeAcquirerResponse?) {
                Log.i("DEBUG 123", fakeAcquirerResponse.toString())
            }

            override fun transactionFailed(fakeAcquirerResponse: FakeAcquirerResponse?) {
                Log.i("DEBUG 123 - Failed", fakeAcquirerResponse.toString())
            }
        }

        setContent {
            FakeAcquirerProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Blue
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val mainActivity: MainActivity = LocalContext.current as MainActivity
    Button(
        modifier = Modifier.padding(8.dp),
        onClick = {
            mainActivity.fakeAcquirerSdk.makeTransaction()
        }
    ) {
        Text(text = "Simular transição com sucesso")
    }
}

