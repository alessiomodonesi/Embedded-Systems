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

    // Reference: https://developer.android.com/jetpack/compose/state
    var t by remember { mutableStateOf(pleasePress) } // il contenuto della casella di testo è segnalata come stato
    // quando la casella di testo cambia, compose scatena la ricomposizione

    // Reference: https://developer.android.com/develop/ui/compose/layouts/constraintlayout
    ConstraintLayout(modifier = modifier) { // interfaccia utente
        val (bu, tv) = createRefs() // creare le reference <=> creare gli ID nella classe View

        // bottone con testo
        Button(
            modifier = Modifier.constrainAs(bu) { // modifier per il positioning
                top.linkTo(parent.top)
                start.linkTo(parent.start, margin = 4.dp)
            },
            // Set the action to be performed when the button is pressed
            onClick = { t = goodJob } // Listener come funzione lambda
        ) {
            Text(text = stringResource(R.string.press_me)) // altro oggetto Text contenuto nel bottone
            // stringa costante che non fa parte dello stato
        }

        // casella di testo != text view
        Text(
            modifier = Modifier.constrainAs(tv) { // modifier per il positioning
                start.linkTo(bu.end, margin = 4.dp) // 4.dp == 4 pixel density independent
                baseline.linkTo(bu.baseline)
            },
            text = t // il testo dell'oggetto è lo stato
        )
    }
}

// Reference: https://developer.android.com/develop/ui/compose/tooling/previews
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}
