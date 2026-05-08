package com.example.android.simplecalctest

import android.os.Build
import android.widget.EditText
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

// A differenza del file MainActivityTestX qui utilizzeremo SOLO roboelectric
@RunWith(RobolectricTestRunner::class)
@Config(minSdk = Build.VERSION_CODES.Q, maxSdk = Build.VERSION_CODES.BAKLAVA)
class MainActivityTest {
    private lateinit var activity: MainActivity

    private lateinit var operandOne: EditText

    @Before
    fun setUp(){
        // Devo chiamare gli stati per mettere in running l'activity, proprio come avviene quando viene normalmente eseguita
        activity = Robolectric.buildActivity(MainActivity::class.java)
            .create() //onCreate
            .start() // onStart
            .resume() // onResume
            .get()

        operandOne = activity.findViewById(R.id.operand_one_edit_text)
    }

    @Test
    fun addition_isCorrect(){
        assertEquals(4, 2 + 2)
    }

    @Test
    fun operandConversion1_int(){
        operandOne.setText("3")

        // questo e' un metodo che e' stato aggiunto, non e' un metodo che ho scritto io
        val d = activity.getOperand(operandOne)
        assertEquals(3.0, d, 0.0)
    }

    @Test
    fun samePi(){
        operandOne.setText("3.14")

        // questo e' un metodo che e' stato aggiunto, non e' un metodo che ho scritto io
        val d = activity.getOperand(operandOne)
        assertEquals(3.14, d, 0.0)
    }

    @Test
    fun same(){
        operandOne.setText("428.96")

        // questo e' un metodo che e' stato aggiunto, non e' un metodo che ho scritto io
        val d = activity.getOperand(operandOne)
        assertEquals(428.96, d, 0.0)
    }
}