package com.example.diceroller

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DiceWithButtonAndImage(
    modifier: Modifier = Modifier,
    navController: NavController? = null
) {
    var result by rememberSaveable { mutableIntStateOf(1) }
    var history by rememberSaveable { mutableStateOf(listOf(0)) }
    val screen = history.last()
    val open: (Int) -> Unit = { history = history + it }
    val back: () -> Unit = { if (history.size > 1) history = history.dropLast(1) }
    BackHandler(enabled = history.size > 1, onBack = back)

    Surface(modifier = modifier.fillMaxSize()) {
        key(history) {
            if (screen == 1) {
                GuririTravelBadge(onBack = back)
            } else {
                Column(
                    modifier = Modifier.fillMaxSize().safeDrawingPadding()
                        .verticalScroll(rememberScrollState()).padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
                ) {
                    when (screen) {
                        0 -> {
                            DiceValue(result)
                            Button(onClick = { result = (1..6).random() }) { Text("Roll") }
                            Button(onClick = { open(result) }) { Text("Screen Result") }
                        }
                        2 -> IncrementDice()
                        3 -> EditableDice()
                        4 -> {
                            Text("Escolhe um ecrã", style = MaterialTheme.typography.headlineSmall)
                            (1..6).forEach { value ->
                                Button(onClick = { open(value) }) { Text("Resultado $value") }
                            }
                        }
                        5 -> DiceValue(5)
                        6 -> DiceGame(onOpen = open)
                    }
                    if (screen != 0) {
                        OutlinedButton(onClick = back) { Text("Voltar") }
                    }
                }
            }
        }
    }
}

@Composable
private fun DiceValue(value: Int, label: String = "Resultado") {
    val face = ((value - 1) % 6) + 1
    val resource = when (face) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }
    Image(
        painter = painterResource(resource),
        contentDescription = "Dado com valor $value",
        modifier = Modifier.size(140.dp, 175.dp)
    )
    Text("$label: $value", style = MaterialTheme.typography.titleLarge)
}

@Composable
private fun IncrementDice() {
    var value by rememberSaveable { mutableIntStateOf(2) }
    DiceValue(value)
    Button(onClick = { if (value <= Int.MAX_VALUE - 2) value += 2 }) {
        Text("Aumentar +2")
    }
}

@Composable
private fun EditableDice() {
    var input by rememberSaveable { mutableStateOf("3") }
    var value by rememberSaveable { mutableIntStateOf(3) }
    val valid = input.toIntOrNull() in 1..6
    DiceValue(value)
    OutlinedTextField(
        value = input,
        onValueChange = {
            input = it
            it.toIntOrNull()?.takeIf { number -> number in 1..6 }?.let { number -> value = number }
        },
        label = { Text("Valor do dado") },
        supportingText = { Text("Introduz um número de 1 a 6") },
        isError = !valid,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true
    )
}

@Composable
private fun DiceGame(onOpen: (Int) -> Unit) {
    val current = 6
    var rolled by rememberSaveable { mutableIntStateOf(1) }
    var hasRolled by rememberSaveable { mutableStateOf(false) }
    Text("Ganhar: novo valor ≥ valor atual")
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) { DiceValue(current, "Atual") }
        Column(horizontalAlignment = Alignment.CenterHorizontally) { DiceValue(rolled, "Novo") }
    }
    Button(onClick = {
        rolled = (1..6).random()
        hasRolled = true
    }) { Text("Lançar novo dado") }
    if (hasRolled) {
        if (rolled >= current) {
            Text("Ganhaste!")
            Button(onClick = { onOpen(rolled) }) { Text("Abrir resultado $rolled") }
        } else {
            Text("Perdeste. Tenta novamente!")
        }
    }
}

@Composable
fun DiceResult(navController: NavController, modifier: Modifier = Modifier) {
    OutlinedButton(onClick = { navController.popBackStack() }, modifier = modifier) {
        Text("Voltar")
    }
}
