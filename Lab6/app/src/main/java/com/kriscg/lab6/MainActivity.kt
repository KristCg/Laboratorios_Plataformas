package com.kriscg.lab6

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kriscg.lab6.ui.theme.Lab6Theme

@Composable
fun Screen (
    modifier: Modifier = Modifier
) {
    var value by remember { mutableStateOf(3) }
    var totalIncrementos by remember { mutableStateOf(0) }
    var totalDecrementos by remember { mutableStateOf(0) }
    val historial = remember { mutableStateListOf<Pair<Int, Boolean>>() }
    val valorMaximo = 100
    val valorMinimo = 0

//modifier = Modifier.fillmaxsize().padding(innerpadding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(
            modifier = modifier
                .height(20.dp)
        )
        Text(
            text = "Kristel Castillo",
            fontSize = 40.sp,
            style = MaterialTheme.typography.titleMedium
        )
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                IconButton(onClick = {
                    if (value > valorMinimo) {
                        value--
                        totalDecrementos++
                        historial.add(Pair(value, false))
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Remove,
                        contentDescription = "Disminuir",
                        tint = Color.White
                    )
                }
            }
            Text(
                text = value.toString(),
                fontWeight = FontWeight.ExtraBold,
                fontSize = 70.sp
            )
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                IconButton(onClick = {
                    if (value < valorMaximo) {
                        value++
                        totalIncrementos++
                        historial.add(Pair(value, true))
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Aumentar",
                        tint = Color.White
                    )
                }
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextStyle1(
                text = "Total Incrementos"
            )
            Text(
                text = totalIncrementos.toString(),
                fontSize = 25.sp
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextStyle1(
                text = "Total Decrementos"
            )
            Text(
                text = totalDecrementos.toString(),
                fontSize = 25.sp
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextStyle1(
                text = "Valor Máximo"
            )
            Text(
                text = "9",
                fontSize = 25.sp
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextStyle1(
                text = "Valor Minimo"
            )
            Text(
                text = "3",
                fontSize = 25.sp
            )


        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextStyle1(
                text = "Total cambios"
            )
            Text(
                text = (totalIncrementos + totalDecrementos).toString(),
                fontSize = 25.sp
            )

        }
        HistorialCard(historial = historial, modifier = Modifier.weight(1f))

        Spacer(modifier = Modifier.height(70.dp))

        Button(onClick = {
            value = 3
            totalDecrementos = 0
            totalIncrementos = 0
            historial.clear()
        },
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Reiniciar"
            )
        }
    }
}

@Composable
fun HistorialCard(
    historial: List<Pair<Int, Boolean>>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        //Podria utilizarse un GridCells.Adaptive()
        //Gridcells.FixedSize()
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(historial) { (valor, incremento) ->
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (incremento) Color(0xFF4CAF50) else Color(0xFFF44336)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = valor.toString(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}
@Composable
fun TextStyle1(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
    )
}
@Preview(showBackground = true)
@Composable
private fun PreviewScreen(){
    Lab6Theme {
        Screen()
    }
}