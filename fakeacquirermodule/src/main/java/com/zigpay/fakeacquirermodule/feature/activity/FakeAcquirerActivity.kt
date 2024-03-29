package com.zigpay.fakeacquirermodule.feature.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.domain.model.FakeTransactionMethod
import com.zigpay.fakeacquirermodule.feature.activity.actions.FakeAcquirerCloseActivityAndSendCallbackActivity
import com.zigpay.fakeacquirermodule.feature.activity.actions.FakeAcquirerExceptionActivity
import com.zigpay.fakeacquirermodule.feature.activity.actions.FakeAcquirerFailedActivity
import com.zigpay.fakeacquirermodule.feature.activity.actions.FakeAcquirerLockedActivity
import com.zigpay.fakeacquirermodule.feature.activity.actions.FakeAcquirerSuccessActivity
import com.zigpay.fakeacquirermodule.feature.activity.actions.FakeAcquirerThrowableActivity
import com.zigpay.fakeacquirermodule.feature.activity.actions.FakeAcquirerWithoutReturnActivity
import com.zigpay.fakeacquirermodule.ui.theme.FakeAcquirerProjectTheme
import java.io.Serializable

class FakeAcquirerActivity : FakeAcquirerActivityBase(), Serializable {
    companion object {
        fun open(context: Context, referenceId:String, price: Float, method: FakeTransactionMethod) {
            context.startActivity(Intent(context, FakeAcquirerActivity::class.java).also {
                it.putExtra(FakeTransaction::class.java.simpleName, FakeTransaction(
                    referenceId = referenceId,
                    price = price,
                    method = method,
                ))
            })
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FakeAcquirerProjectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    InitView()
                }
            }
        }
    }



    fun startFakeAcquirerSuccessActivity() {
        open(this, FakeAcquirerSuccessActivity::class.java, getFakeTransaction())
    }

    fun startFakeAcquirerFailedActivity() {
        open(this, FakeAcquirerFailedActivity::class.java, getFakeTransaction())
    }

    fun startFakeAcquirerExceptionActivity() {
        open(this, FakeAcquirerExceptionActivity::class.java, getFakeTransaction())
    }

    fun startFakeAcquirerThrowableActivity() {
        open(this, FakeAcquirerThrowableActivity::class.java, getFakeTransaction())
    }

    fun startFakeAcquirerWithoutReturnActivity() {
        open(this, FakeAcquirerWithoutReturnActivity::class.java, getFakeTransaction())
    }

    fun startFakeAcquirerLockedActivity() {
        open(this, FakeAcquirerLockedActivity::class.java, getFakeTransaction())
    }

    fun startFakeAcquirerTransactionsActivity() {
        FakeAcquirerTransactionsActivity.open(this)
    }

    fun startFakeAcquirerTransactionsCloseActivityAndSendCallback() {
        open(this, FakeAcquirerCloseActivityAndSendCallbackActivity::class.java, getFakeTransaction())
    }

}


@Composable
fun InitView() {
    val fakeAcquirerActivity: FakeAcquirerActivity = LocalContext.current as FakeAcquirerActivity

    data class MyButton(
        val text: String,
        val onClick: () -> Unit,
        val buttonColors: ButtonColors
    )

    val buttons = listOf(
        MyButton(
            "Fluxo: Sucesso\nPagamento: Sucesso",
            { fakeAcquirerActivity.startFakeAcquirerSuccessActivity() },
            ButtonDefaults.buttonColors()
        ),
        MyButton(
            "Fluxo: Sucesso\nPagamento: Error",
            { fakeAcquirerActivity.startFakeAcquirerFailedActivity() },
            ButtonDefaults.buttonColors(containerColor = Color.LightGray)
        ),
        MyButton(
            "Fluxo: Sem retorno\nPagamento: Sucesso",
            { fakeAcquirerActivity.startFakeAcquirerWithoutReturnActivity() },
            ButtonDefaults.buttonColors(containerColor = Color.LightGray)
        ),
        MyButton(
            "Fluxo: Travamento Activity\nPagamento: Sucesso",
            { fakeAcquirerActivity.startFakeAcquirerLockedActivity() },
            ButtonDefaults.buttonColors(containerColor = Color.LightGray)
        ),
        MyButton(
            "Fluxo: Fechar Act e enviar Callback\nPagamento: Sucesso",
            { fakeAcquirerActivity.startFakeAcquirerTransactionsCloseActivityAndSendCallback() },
            ButtonDefaults.buttonColors(containerColor = Color.LightGray)
        ),
        MyButton(
            "Fluxo: Exception\nPagamento: Sucesso",
            { fakeAcquirerActivity.startFakeAcquirerExceptionActivity() },
            ButtonDefaults.buttonColors(containerColor = Color.Red)
        ),
        MyButton(
            "Fluxo: Throwable\nPagamento: Sucesso",
            { fakeAcquirerActivity.startFakeAcquirerThrowableActivity() },
            ButtonDefaults.buttonColors(containerColor = Color.Red)
        ),
        MyButton(
            "Ver transações",
            { fakeAcquirerActivity.startFakeAcquirerTransactionsActivity() },
            ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
        ),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(buttons) { button ->
            Button(
                onClick = button.onClick,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                colors = button.buttonColors
            ) {
                Text(text = button.text, textAlign = TextAlign.Center)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MakeSuccessContentPreview() {
    FakeAcquirerProjectTheme {
        InitView()
    }
}