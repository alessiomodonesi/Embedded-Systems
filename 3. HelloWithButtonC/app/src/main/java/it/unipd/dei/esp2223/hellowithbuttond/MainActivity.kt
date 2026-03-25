package it.unipd.dei.esp2223.hellowithbuttond

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import it.unipd.dei.esp2223.hellowithbuttond.ui.theme.ComposeHelloWithButtonTheme

class MainActivity: ComponentActivity()
{
    // Called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        // Enable edge-to-edge display on API level < 35
        enableEdgeToEdge()

        // Set and display the UI content
        setContent { // implemento l'UI dell'activity come funzione @composable
            ComposeHelloWithButtonTheme {
                // Reference: https://developer.android.com/develop/ui/compose/components/scaffold
                // The scaffold fills the whole display area
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // MainScreen consumes the insets
                    // to keep the app UI away from the system UI and display cutouts
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable // fuori dalla classe main sactivity
fun MainScreen(modifier: Modifier = Modifier)
{
    val pleasePress = stringResource(R.string.please_press) // recupero la stringa dal file strings.xml
    val goodJob = stringResource(R.string.good_job)
    val counter = stringResource(R.string.counter) // init della tv al valore iniziale

    // Reference: https://developer.android.com/jetpack/compose/state
    var t1 by remember { mutableStateOf(pleasePress) } // il contenuto della casella di testo è segnalata come stato
    var t2 by remember { mutableStateOf(counter) } // counter come stato
    // quando la casella di testo cambia, compose scatena la ricomposizione

    // Reference: https://developer.android.com/develop/ui/compose/layouts/constraintlayout
    ConstraintLayout(modifier = modifier) { // interfaccia utente
        val (bu1, bu2, tv1, tv2) = createRefs() // creare le reference <=> creare gli ID nella classe View

        // bottone "Press me"
        Button(
            modifier = Modifier.constrainAs(bu1) { // modifier per il positioning
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 4.dp)
            },
            // Set the action to be performed when the button is pressed
            onClick = { t1 = goodJob } // Listener come funzione lambda
        ) {
            Text(text = stringResource(R.string.press_me)) // altro oggetto Text contenuto nel bottone
            // stringa costante che non fa parte dello stato
        }

        // bottone "+1"
        Button(
            modifier = Modifier.constrainAs(bu2) {
                top.linkTo(bu1.bottom)
                start.linkTo(parent.start, margin = 4.dp)
            },
            // Set the action to be performed when the button is pressed
            onClick = {
                val i : Int = try {
                    t2.toInt()
                } catch (e: NumberFormatException) {
                    -1
                }
                t2 = "${i + 1}"
            }
        ) {
            Text(text = stringResource(R.string.plus_one))
        }

        // casella di testo != text view
        Text(
            modifier = Modifier.constrainAs(tv1) { // modifier per il positioning
                start.linkTo(bu1.end, margin = 4.dp) // 4.dp == 4 pixel density independent
                baseline.linkTo(bu1.baseline)
            },
            text = t1 // il testo dell'oggetto è lo stato
        )

        Text(
            modifier = Modifier.constrainAs(tv2) {
                start.linkTo(bu2.end, margin = 4.dp)
                baseline.linkTo(bu2.baseline)
            },
            text = t2 // il testo dell'oggetto è lo stato
        )
    }
}

// Reference: https://developer.android.com/develop/ui/compose/tooling/previews
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
