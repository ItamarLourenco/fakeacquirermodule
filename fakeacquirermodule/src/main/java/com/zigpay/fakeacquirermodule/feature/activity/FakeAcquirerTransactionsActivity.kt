package com.zigpay.fakeacquirermodule.feature.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.domain.model.TypeTransaction
import com.zigpay.fakeacquirermodule.feature.activity.actions.FakeAcquirerExceptionActivity
import com.zigpay.fakeacquirermodule.feature.activity.actions.FakeAcquirerFailedActivity
import com.zigpay.fakeacquirermodule.feature.activity.actions.FakeAcquirerLockedActivity
import com.zigpay.fakeacquirermodule.feature.activity.actions.FakeAcquirerSuccessActivity
import com.zigpay.fakeacquirermodule.feature.activity.actions.FakeAcquirerThrowableActivity
import com.zigpay.fakeacquirermodule.feature.activity.actions.FakeAcquirerWithoutReturnActivity
import com.zigpay.fakeacquirermodule.feature.viewmodel.FakeTransactionsViewModel
import com.zigpay.fakeacquirermodule.ui.theme.FakeAcquirerProjectTheme
import java.io.Serializable

class FakeAcquirerTransactionsActivity : FakeAcquirerActivityBase(), Serializable {
    companion object {
        fun open(context: Context) {
            context.startActivity(
                Intent(context, FakeAcquirerTransactionsActivity::class.java)
            )
        }
    }

    private val fakeTransactionsViewModel: FakeTransactionsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fakeTransactionsViewModel.fakeTransactionList.observe(this, Observer {
            Log.i("DEBUG", it.toString())
        })
        fakeTransactionsViewModel.updateFakeTransactionList()

        setContent {
            FakeAcquirerProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    fakeTransactionsViewModel.fakeTransactionList.value?.let {list ->
                        InitViewList(list)
                    }
                }
            }
        }
    }
}


@Composable
fun InitViewList(fakeTransactionList: List<FakeTransaction>) {
    LazyColumn {
        itemsIndexed(fakeTransactionList) { _, fakeTransaction ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                ),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("UUID: ")
                            }
                            append(fakeTransaction.uid.toString())
                        },
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Status: ")
                            }
                            append(fakeTransaction.status.toString())
                        },
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Type: ")
                            }
                            append(fakeTransaction.type.toString())
                        },
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Status: ")
                            }
                            append(fakeTransaction.status.toString())
                        },
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}
