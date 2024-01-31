package com.zigpay.fakeacquirermodule

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zigpay.fakeacquirermodule.ui.theme.FakeAcquirerProjectTheme
import java.io.Serializable

class FakeAcquirerActivity : FakeAcquirerActivityBase(), Serializable {

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

    fun startFakeAcquirerFailedActivity() {
        startActivity(
            Intent(this, FakeAcquirerFailedActivity::class.java)
        )
        finish()
    }

    fun startFakeAcquirerSuccessActivity() {
        startActivity(
            Intent(this, FakeAcquirerSuccessActivity::class.java)
        )
        finish()
    }

    fun startFakeAcquirerExceptionActivity(){
        startActivity(
            Intent(this, FakeAcquirerExceptionActivity::class.java)
        )
        finish()
    }

    fun startFakeAcquirerThrowableActivity(){
        startActivity(
            Intent(this, FakeAcquirerThrowableActivity::class.java)
        )
        finish()
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
                fakeAcquirerActivity.startFakeAcquirerExceptionActivity()

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
                fakeAcquirerActivity.startFakeAcquirerThrowableActivity()
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