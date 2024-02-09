package com.zigpay.fakeacquirermodule.feature.activity

import android.R.attr.backdropColor
import android.R.attr.background
import android.R.attr.label
import android.R.attr.text
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService
import com.zigpay.fakeacquirermodule.domain.model.FakeTransaction
import com.zigpay.fakeacquirermodule.feature.viewmodel.FakeTransactionsViewModel
import com.zigpay.fakeacquirermodule.ui.theme.FakeAcquirerProjectTheme
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.Locale


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

    fun copyToClipboard(text: CharSequence){
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("label",text)
        clipboard.setPrimaryClip(clip)
    }
}


@Composable
fun InitViewList(fakeTransactionList: List<FakeTransaction>) {
    val fakeAcquirerTransactionsActivity = LocalContext.current as FakeAcquirerTransactionsActivity
    LazyColumn(
        contentPadding = PaddingValues(0.dp)
    ) {
        itemsIndexed(fakeTransactionList) { _, fakeTransaction ->
            Button(
                modifier = Modifier
                    .padding(0.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                onClick = {
                    fakeAcquirerTransactionsActivity.copyToClipboard(fakeTransaction.uid.toString())
                    Toast.makeText(fakeAcquirerTransactionsActivity, "UUID copidado!", Toast.LENGTH_LONG).show()
                }
            ) {
                Column(
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("UUID: ")
                            }
                            append(fakeTransaction.uid.toString())
                        },
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Valor: ")
                            }
                            append(fakeTransaction.price.toString())
                        },
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Method: ")
                            }
                            append(fakeTransaction.method.toString())
                        },
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Status: ")
                            }
                            append(fakeTransaction.status.toString())
                        },
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Ação: ")
                            }
                            append(fakeTransaction.action.toString())
                        },
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append("Criando em: ")
                            }
                            append(
                                SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale("pt", "BR")).format(fakeTransaction.created_at)
                            )
                        },
                        fontSize = 12.sp,
                        color = Color.DarkGray
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }
    }
}
