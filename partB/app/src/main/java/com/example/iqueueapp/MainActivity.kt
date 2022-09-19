package com.example.iqueueapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.iqueueapp.ui.theme.Purple700
import kotlinx.coroutines.delay
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            // Flag to check if we have made it past the first screen
            var inFirstScreen by remember {mutableStateOf(true)}
            // Store license plate
            var licensePlate by remember { mutableStateOf("")}

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly)
            {
                TitleCard(
                    inFirstScreen=inFirstScreen,
                    licensePlate = licensePlate,
                )
                // Center State
                if (inFirstScreen){
                    Text(
                        buildAnnotatedString {
                            append("Your License Plate\n")
                            val text = licensePlate.ifEmpty { "-empty-" }
                            val color =
                                if (!checkLicensePlate(text)) Color.Red
                                else Color(0, 121,0)
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = color)
                            ){
                                append(text)
                            }
                        },
                        style = TextStyle(fontSize = 25.sp, textAlign = TextAlign.Center)
                    )
                    OutlinedTextField(
                        value = licensePlate,
                        onValueChange = {licensePlate = it},
                        label = { Text("License Plate") },
                        placeholder = {Text("eg. ABC1234")},
                        maxLines = 1,
                        singleLine = true
                    )
                }
                else{
                    // Shows where our car is in the queue, as well which cars are being washed
                    StatusMenu(
                        licensePlate = licensePlate,
                        queue = queueSetup(licensePlate)
                    )
                }
                NavigationButtons(
                    inFirstScreen= inFirstScreen,
                    licensePlate = licensePlate,
                    enabled = checkLicensePlate(licensePlate),
                    onFirstTimeButtonClick = {
                        inFirstScreen = !inFirstScreen
                        licensePlate = it
                    }
                )
            }
        }
    }
}

@Composable
fun TitleCard(
    inFirstScreen : Boolean,
    licensePlate : String
){
    if (inFirstScreen) {
        Text(
            modifier = Modifier
                .border(BorderStroke(3.dp, Purple700), shape = RoundedCornerShape(15.dp))
                .padding(15.dp),
            style = TextStyle(fontSize = 25.sp, textAlign = TextAlign.Center),
            text = "Welcome to the Car Wash\nPlease Join the Queue"
        )
    }
    else{
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold))
                {
                    append("Your Ticket Number\n")
                }
                    val color = Color(0, 121,0)
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = color))
                    {
                        append("$licensePlate\n")
                    }
                    withStyle(style = SpanStyle(fontWeight = FontWeight.SemiBold, fontSize = 13.sp))
                    {
                        append("Refer ticket number to our staff for any assistance")
                    }
            },
            style = TextStyle(fontSize = 25.sp, textAlign = TextAlign.Center),
            modifier = Modifier
                .border(BorderStroke(3.dp, Purple700), shape = RoundedCornerShape(15.dp))
                .padding(15.dp)
        )
    }
}

@Composable
fun StatusMenu(
    licensePlate: String,
    queue: QUEUE
){
    var currentTime by remember{
        mutableStateOf(queue.size() * 10 * 1000L)
    }

    LaunchedEffect(key1 = currentTime) {
        if (currentTime > 0) {
            delay(10 * 1000L)
            currentTime -= 10 * 1000L
        }
    }
    if (currentTime > 0){
        if (queue.size() - 1 != 0){
            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, fontSize = 20.sp))
                    {
                        append("Your Position in Queue:\n")
                        append("\u2192    ${queue.size() - 1}\n")
                        append("Currently Washing:\n")
                        append("\u2192    ${queue.dequeue()}\n")
                        append("Remaining Cars:\n")
                        queue.getQueue().forEach {
                            if (it == licensePlate)
                                append("\u2022  $it \u2190Your Car \n")
                            else append("\u2022  $it\n")
                        }
                    }
                },
                modifier = Modifier
                    .border(BorderStroke(3.dp, Purple700), shape = RoundedCornerShape(15.dp))
                    .padding(15.dp)
            )
        }
        else{
            Text(
                modifier = Modifier
                    .border(BorderStroke(3.dp, Purple700), shape = RoundedCornerShape(15.dp))
                    .padding(15.dp),
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center),
                text="Your Car is now being washed\nPlease be patient"
            )
        }
    }
    else{
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.ExtraBold, fontSize = 20.sp))
                {
                    append("Your Car has been Washed!\n")
                    append("Thank You and have a nice day")
                }
            },
            style = TextStyle(textAlign = TextAlign.Center),
            modifier = Modifier
                .border(BorderStroke(3.dp, Purple700), shape = RoundedCornerShape(15.dp))
                .padding(15.dp)
        )
    }
}

@Composable
fun NavigationButtons(
    inFirstScreen: Boolean,
    licensePlate: String,
    enabled: Boolean,
    onFirstTimeButtonClick: (String) -> Unit
){
    if (inFirstScreen) {
        val randomNum = Random.nextInt(from = 100, until = 999)
        val newLicense = "$licensePlate-$randomNum"
        Button(
            modifier = Modifier.padding(5.dp),
            enabled = enabled,
            onClick = {onFirstTimeButtonClick(newLicense)},
            colors = ButtonDefaults.buttonColors(backgroundColor = Purple700)
        ){
            Text(if (enabled) "Join Queue" else "Invalid License Plate")
        }
    }
}

// Checks if given license plate number is valid or not
fun checkLicensePlate(licensePlate: String): Boolean{
    val licensePlateRegex = "^[A-Z]{3}[0-9]{1,4}$".toRegex()
    return licensePlateRegex.containsMatchIn(licensePlate)
}