package com.example.calculatorapp.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorapp.R


val buttonlist= listOf(
   "C","(",")","/",
   "7","8","9","*",
   "4","5","6","+",
   "1","2","3","-",
   "AC","0",".","="
)



@Composable
fun calculator(modifier: Modifier=Modifier ,viewModel: CalculatorViewModel)
{

    val equationText=viewModel.equationText.observeAsState()
    val ResultText=viewModel.Result.observeAsState()



Box(modifier = Modifier)
{

    Image(
        painter = painterResource(id = R.drawable.white),
        contentDescription = "Background Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
  Column(modifier= Modifier
      .fillMaxSize()
      .padding(50.dp), horizontalAlignment = Alignment.End) {

      Text(text = "Welcome to Calculator App", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Blue)

      Spacer(modifier = Modifier.height(16.dp))

     Text(text = equationText.value?:"" ,
        style = TextStyle(fontSize = 30.sp, textAlign = TextAlign.End, color = Color.Black),
        maxLines = 5, overflow = TextOverflow.Ellipsis)

      Spacer(modifier = Modifier.weight(1f))

     Text(text = ResultText.value?:"" ,
        style = TextStyle(fontSize = 60.sp, textAlign = TextAlign.End,color = Color.Black),
        maxLines = 2 )

     Spacer(modifier = Modifier.height(10.dp))

     LazyVerticalGrid(columns = GridCells.Fixed(4)) {

        items(buttonlist)
        {

           CalculatorButton(btn = it, onclick = {
               viewModel.onbuttonclick(it)
           })
        }

     }


  }
}
}

@Composable
fun CalculatorButton(btn:String,onclick:()->Unit)
{
  Box(modifier = Modifier.padding(8.dp))
  {
    FloatingActionButton(onClick = onclick,
       modifier = Modifier.size(70.dp), shape = CircleShape,
       containerColor = getcolor(btn),
       contentColor = Color.Black
    ) {
       Text(text = btn, fontSize = 20.sp, fontWeight = FontWeight.Bold )

    }
  }
}

fun getcolor(btn:String):Color{
    if (btn== "C" || btn== "AC")
        return Color.Red
    if (btn== "+" || btn== "-" || btn== "*" || btn== "/" || btn== "=")
        return Color.Green
    if (btn== "(" || btn== ")")
        return Color.Yellow

    return Color.Cyan
}