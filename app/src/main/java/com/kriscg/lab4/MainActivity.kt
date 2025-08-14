package com.kriscg.lab4

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.kriscg.lab4.ui.theme.Lab4Theme

val miVerde = Color(0xFF048021)

@Composable
fun PortadaUVG(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .border(8.dp, miVerde)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo UVG",
            modifier = Modifier
                .size(500.dp)
                .alpha(0.4f)
        )
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(35.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Universidad del Valle\nde Guatemala",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier

            )
            Text(
                text = "Programacion de plataformas\nmóviles, Sección 30",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight(475),
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
            )
            Row (
                horizontalArrangement = Arrangement.spacedBy(77.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Encabezado(text = "INTEGRANTES")
                Column (
                    modifier = Modifier
                ){
                    Contenido(
                        text = "Kristel Castillo\nSarah Estrada\nSofia Lopez"
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(47.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Encabezado(text = "CATEDRÁTICO")
                Contenido(text = "Juan Carlos Durini")
            }
            Contenido(text = "Kristel Castillo\n241294")

        }
    }
}

@Composable
fun Encabezado(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold,
        fontSize = 17.5.sp
    )
}
@Composable
fun Contenido(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight(475),
        fontSize = 17.5.sp,
        textAlign = TextAlign.Center,
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab4Theme {
        PortadaUVG()
    }
}