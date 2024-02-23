package com.zigpay.fakeacquirerproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionMethod
import com.zigpay.fakeacquirermodule.domain.repository.FakeAcquirerListener
import com.zigpay.fakeacquirermodule.usecase.FakeAcquirerSdk
import com.zigpay.fakeacquirerproject.ui.theme.FakeAcquirerProjectTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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
        fakeAcquirerSdk.modeActivity.makeTransaction("reference_id",200f, FakeTransactionMethod.DEBIT, object: FakeAcquirerListener {
            override fun transactionSuccess(fakeAcquirerResponse: FakeTransaction?) {
                Log.i("FAKE_DEBUG", fakeAcquirerResponse.toString())
                continuation.resume(fakeAcquirerResponse)
            }

            override fun transactionFailed(fakeAcquirerResponse: FakeTransaction?) {
                Log.i("FAKE_DEBUG", fakeAcquirerResponse.toString())
                continuation.resume(fakeAcquirerResponse)
            }
        })
    }

    suspend fun makeTransactionWithoutActivity(success: Boolean = true): FakeTransaction? = suspendCoroutine { continuation ->
        val listener = object: FakeAcquirerListener {
            override fun transactionSuccess(fakeAcquirerResponse: FakeTransaction?) {
                Log.i("FAKE_DEBUG", fakeAcquirerResponse.toString())
                continuation.resume(fakeAcquirerResponse)
            }

            override fun transactionFailed(fakeAcquirerResponse: FakeTransaction?) {
                Log.i("FAKE_DEBUG", fakeAcquirerResponse.toString())
                continuation.resume(fakeAcquirerResponse)
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            when(success) {
                true -> fakeAcquirerSdk.modeCallback.makeTransactionWithoutActivitySuccess("reference_id", 200f, FakeTransactionMethod.DEBIT, listener)
                false -> fakeAcquirerSdk.modeCallback.makeTransactionWithoutActivityFailed("reference_id", 200f, FakeTransactionMethod.DEBIT, listener)
            }
        }
    }

    suspend fun getTransactionByActivity(referenceId: String) = suspendCoroutine { continuation ->
        fakeAcquirerSdk.modeActivity.getTransactionByReferenceId(referenceId, object: FakeAcquirerListener {
            override fun transactionSuccess(fakeAcquirerResponse: FakeTransaction?) {
                continuation.resume(fakeAcquirerResponse)
            }

            override fun transactionFailed(fakeAcquirerResponse: FakeTransaction?) {
                continuation.resume(fakeAcquirerResponse)
            }
        })
    }


    fun showAllTransactions() = fakeAcquirerSdk.showAllTransactions()
    fun getTransaction(id: String): FakeTransaction? = fakeAcquirerSdk.modeCallback.getTransactionByReferenceId(id)

}

@Composable
fun Greeting(rememberCoroutineScope: CoroutineScope) {
    val mainActivity: MainActivity = LocalContext.current as MainActivity
    var log by remember { mutableStateOf("") }
    var logWithoutActivitySuccess by remember { mutableStateOf("") }
    var logWithoutActivityFailed by remember { mutableStateOf("") }
    var logReferenceIdByActivity by remember { mutableStateOf("") }
    var getTransaction by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Column (
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            Button(
                onClick = {
                    rememberCoroutineScope.launch {
                        val fakeTransaction = mainActivity.makeTransaction()
                        if(fakeTransaction != null){
                            log = fakeTransaction.toString()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(16.dp)
            ) {
                Text(text = "Simular transações")
            }
            Text(text = log, textAlign = TextAlign.Center, fontSize = 12.sp)

            Button(
                onClick = {
                    logWithoutActivitySuccess = "Fazendo transação"
                    rememberCoroutineScope.launch {
                        logWithoutActivitySuccess = mainActivity.makeTransactionWithoutActivity().toString()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(16.dp)
            ) {
                Text(text = "Simular sem activity - Success")
            }
            Text(text = logWithoutActivitySuccess, textAlign = TextAlign.Center, fontSize = 12.sp)

            Button(
                onClick = {
                    logWithoutActivityFailed = "Fazendo transação"
                    rememberCoroutineScope.launch {
                        logWithoutActivityFailed = mainActivity.makeTransactionWithoutActivity(
                            success = false
                        ).toString()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(16.dp)
            ) {
                Text(text = "Simular sem activity - Failed")
            }
            Text(text = logWithoutActivityFailed, textAlign = TextAlign.Center, fontSize = 12.sp)

            Button(
                onClick = {
                    mainActivity.getTransaction("reference_id").let {
                        getTransaction = it.toString()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(16.dp)
            ) {
                Text(text = "Buscar transações")
            }
            Text(text = getTransaction, textAlign = TextAlign.Center, fontSize = 12.sp)

            Button(
                onClick = {
                    rememberCoroutineScope.launch {
                        mainActivity.getTransactionByActivity("reference_id").let {
                            logReferenceIdByActivity = it.toString()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(16.dp)
            ) {
                Text(text = "Buscar transações por activity")
            }
            Text(text = logReferenceIdByActivity, textAlign = TextAlign.Center, fontSize = 12.sp)

            Button(
                onClick = {
                    mainActivity.showAllTransactions()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(16.dp)
            ) {
                Text(text = "Mostrar transações")
            }
        }
    }
}

