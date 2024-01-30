package com.zigpay.fakeacquirermodule

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.zigpay.fakeacquirermodule.ui.theme.FakeAcquirerProjectTheme
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class FakeAcquirerActivity : ComponentActivity() {
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            result.data?.let {
                setResult(Activity.RESULT_OK,
                    Intent().putExtra("result", FakeAcquirerResponse.getData(it))
                )
                finish()
            }
        }

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

    fun startFakeAcquirerFailedActivity() {
        val intent = Intent(this, FakeAcquirerFailedActivity::class.java)
        resultLauncher.launch(intent)
    }

    fun startFakeAcquirerSuccessActivity() {
        val intent = Intent(this, FakeAcquirerSuccessActivity::class.java)
        resultLauncher.launch(intent)
    }

}


@Composable
fun InitView() {
    val fakeAcquirerActivity: FakeAcquirerActivity = LocalContext.current as FakeAcquirerActivity
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    fakeAcquirerActivity.startFakeAcquirerSuccessActivity()
                }
            ) {
                Text(text = "Simular transição com sucesso")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                fakeAcquirerActivity.startFakeAcquirerFailedActivity()
            },
            modifier = Modifier.padding(8.dp),
            colors =  ButtonDefaults.buttonColors(
                containerColor = Color.LightGray
            ),
        ) {
            Text(text = "Simular transição com erro", textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                throw Exception("Exception Lançanda com sucesso!")
            },
            modifier = Modifier.padding(8.dp),
            colors =  ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),
        ) {
            Text("Lançar Exception", textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                throw Throwable("Throwable Lançanda com sucesso!")
            },
            modifier = Modifier.padding(8.dp),
            colors =  ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),
        ) {
            Text("Lançar Throwable", textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun MakeSuccessContentPreview() {
    FakeAcquirerProjectTheme {
        InitView()
    }
}