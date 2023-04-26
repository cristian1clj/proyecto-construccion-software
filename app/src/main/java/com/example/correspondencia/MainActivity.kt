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
import androidx.compose.ui.res.painterResource
import com.example.correspondencia.ui.theme.CorrespondenciaTheme
private val messages: List<MyMessage> = listOf(
    MyMessage("permitir", "entrega dentro de  "),
)
    class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CorrespondenciaTheme {
                // A surface container using the 'background' color from the theme
                val names = listOf("30 minutos", "1 Hora", "2 Horas")
                var selectedName by rememberSaveable() {
                    mutableStateOf("30 minutos")}
                MyMessages(messages)
  //              Spinner(itemList = names, selectedItem = selectedName, onItemSelected = { selectedName = it })
                }
            }
        }
    }
data class MyMessage(val tittle: String, val body: String)

@Composable
fun Spinner(
    itemList: List<String>,
    selectedItem: String,
    onItemSelected: (selectedItem: String) -> Unit
){
    var expanded by rememberSaveable(){ mutableStateOf(false) }

    OutlinedButton(onClick = { expanded = true}) {
        Text(
            text = selectedItem,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.weight(
                1f,
            ),
        )
        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
    }

    DropdownMenu(
        expanded =expanded,
        onDismissRequest = { expanded = false}
    ){
        itemList.forEach{
            DropdownMenuItem(onClick = {
                expanded = false
                onItemSelected(it)
            }) {
                Text (text= it)
            }
        }
    }
}

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
            message.body,
            MaterialTheme.colors.onBackground,
            MaterialTheme.typography.subtitle2,
            if (expanded) Int.MAX_VALUE else 1
        )
        val names = listOf("30 minutos", "1 Hora", "2 Horas")
        var selectedName by rememberSaveable() {
            mutableStateOf("30 minutos")}
        Spinner(itemList = names, selectedItem = selectedName, onItemSelected = { selectedName = it })

    }
}

@Composable
fun MyText(text: String, color: androidx.compose.ui.graphics.Color, style: androidx.compose.ui.text.TextStyle, lines:Int =Int.MAX_VALUE){
    Text(text, color = color, style = style, maxLines = lines)
}
/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CorrespondenciaTheme {
  //      Greeting("Android")
     //   Spinner(itemList = names, selectedItem = selectedName, onItemSelected = { selectedName = it })
    }
}*/