package com.example.diceroller

import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme

private val Sand = Color(0xFFFFF8ED)
private val Ocean = Color(0xFF075E68)
private val Ink = Color(0xFF17383D)

@Composable
fun GuririTravelBadge(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {}
) {
    var temperature by rememberSaveable { mutableIntStateOf(28) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Sand)
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        OutlinedButton(onClick = onBack) {
            Text("Back")
        }

        Text(
            text = "Temperatura fictícia: $temperature °C",
            color = Ocean,
            fontWeight = FontWeight.Bold
        )

        Button(
            onClick = {
                temperature = (20..35)
                    .filter { it != temperature }
                    .random()
            }
        ) {
            Text("Refresh")
        }
        Text(
            text = "O MEU DESTINO FAVORITO",
            color = Ocean,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 2.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .clip(RoundedCornerShape(28.dp))
        ) {
            Image(
                painter = painterResource(R.drawable.guriri_praia),
                contentDescription = "Vista da praia de Guriri",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.8f)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(24.dp)
            ) {
                Text(
                    text = "Guriri",
                    color = Color.White,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = "São Mateus · Espírito Santo",
                    color = Color.White,
                    fontSize = 16.sp
                )

                Text(
                    text = "BRASIL",
                    color = Color.White,
                    fontSize = 12.sp,
                    letterSpacing = 3.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        Text(
            text = "Porque devemos visitar?",
            color = Ink,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Vale a pena pelas suas águas mornas, extensa faixa de areia e ambiente vibrante que atrai famílias e turistas em busca de lazer e descanso.",
            color = Ink,
            style = MaterialTheme.typography.bodyLarge
        )

        Surface(
            shape = RoundedCornerShape(24.dp),
            color = Color.White,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.guriri_placa),
                    contentDescription = "Outra perspetiva de Guriri",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(100.dp)
                        .height(150.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Porque escolhi Guriri",
                        color = Ocean,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = "É o local onde cresci e tenho atualmente família e casa.",
                        color = Ink,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "Pontos turísticos",
                    color = Ocean,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )

                Text(
                    text = "Praia de Guriri\n" +
                            "Casa de Cabeça para Baixo\n" +
                            "Guriri Beach Acqua Park",
                    color = Ink,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.bodyLarge,
                )
            }

            Image(
                painter = painterResource(R.drawable.guriri_casa_invertida),
                contentDescription = "Casa de Cabeça para Baixo em Guriri",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
    }
}


@Preview(showBackground = true, widthDp = 390, heightDp = 900)
@Composable
fun GuririTravelBadgePreview() {
    DiceRollerTheme {
        GuririTravelBadge()
    }
}
