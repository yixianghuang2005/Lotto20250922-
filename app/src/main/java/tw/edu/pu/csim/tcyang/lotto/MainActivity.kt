package tw.edu.pu.csim.tcyang.lotto

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import tw.edu.pu.csim.tcyang.lotto.ui.theme.LottoTheme
import androidx.compose.runtime.setValue // 引入 setValue
import androidx.compose.ui.platform.LocalContext // 引入 LocalContext
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.geometry.Offset

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LottoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Play(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Play(modifier: Modifier = Modifier) {
    var lucky by remember {
        mutableStateOf((1..100).random())
    }

    val context = LocalContext.current

    Column (
        // 使用 pointerInput 來處理更詳細的觸控事件
        modifier = modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                // 偵測單擊手勢
                detectTapGestures { offset: Offset ->
                    // 取得觸控的 x 和 y 座標
                    val x = offset.x.toInt()
                    val y = offset.y.toInt()
                    // 顯示包含座標的 Toast 訊息
                    Toast.makeText(context, "觸控座標：($x, $y)", Toast.LENGTH_SHORT).show()
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "樂透數字(1-100)為 $lucky"
        )

        Button(
            onClick = { lucky = (1..100).random() }
        ) {
            Text("重新產生樂透碼")
        }

        Text("楊子青共同編輯程式")
    }
}