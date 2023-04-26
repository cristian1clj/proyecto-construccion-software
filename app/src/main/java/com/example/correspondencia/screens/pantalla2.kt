package com.example.correspondencia

import  android.os.Bundle
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.layout.RowScopeInstance.weight
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.painterResource
import com.example.pantalla222.R
//import com.example.correspondencia.ui.theme.CorrespondenciaTheme
import com.example.pantalla222.ui.theme.Pantalla222Theme

private val messages: List<MyMessage> = listOf(
    MyMessage("Entrega del paquete", "Para: Andres", "De: Exito"),
)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CorrespondenciaTheme {
                MyMessages(messages)
            }
        }
    }
}
data class MyMessage(val tittle: String, val by: String, val from: String)

@Composable
fun MyMessages(message: List<MyMessage>){
    LazyColumn{
        items(message){ message ->
            MyComponent(message)
        }
    }
}

@Composable
fun MyComponent(message: MyMessage,){
    Row (modifier = Modifier
        .padding(8.dp)
        .background(MaterialTheme.colors.background)) {
        MyImage()
        MyTexts(message)
    }
}

@Composable
fun MyImage(){
    Image(
        painterResource(R.drawable.ic_launcher_foreground),
        "Mi imagen de prueba",
        //este para modificar la imagen, background para el color y clip para la forma y size par ael tamaño
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colors.primary)
    )
}

@Composable
fun MyTexts(message: MyMessage){
    var expanded = false
    Column(modifier = Modifier.padding(start=8.dp).clickable{
        expanded = !expanded
    }) {
        MyText(
            message.tittle,
            MaterialTheme.colors.primaryVariant,
            MaterialTheme.typography.subtitle1
        )
        //este para generar espacio entre las columnas
        Spacer(modifier = Modifier.height(16.dp))
        MyText(
            message.by,
            MaterialTheme.colors.onBackground,
            MaterialTheme.typography.subtitle2,
            if (expanded) Int.MAX_VALUE else 1
        )
        MyText(
            message.from,
            MaterialTheme.colors.onBackground,
            MaterialTheme.typography.subtitle2,
            if (expanded) Int.MAX_VALUE else 1
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .padding(8.dp)
                .background(MaterialTheme.colors.background)
        ) {
            Button(
                onClick = {
                    // go to the 3th page
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Red)
            ) {
                Text(text = "Rechazar")
            }

            Button(
                onClick = {
                    // go to the 3th page
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Green)
            ) {
                Text(text = "Aceptar")
            }
            //           SimpleButton("Rechazar", Red)
//            SimpleButton("Aceptar", Green)
        }
    }
}

@Composable
fun MyText(text: String, color: androidx.compose.ui.graphics.Color, style: androidx.compose.ui.text.TextStyle, lines:Int =Int.MAX_VALUE){
    Text(text, color = color, style = style, maxLines = lines)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Pantalla222Theme {
        MyMessages(messages)
    }
    // CorrespondenciaTheme {
    //      Greeting("Android")
    //   Spinner(itemList = names, selectedItem = selectedName, onItemSelected = { selectedName = it })
}
//}