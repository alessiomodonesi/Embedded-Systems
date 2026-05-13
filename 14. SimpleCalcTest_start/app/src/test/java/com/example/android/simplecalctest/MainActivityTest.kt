package com.example.android.simplecalctest

import android.os.Build
import android.widget.EditText
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

// A differenza del file MainActivityTestX qui utilizzeremo SOLO roboelectric
@RunWith(RobolectricTestRunner::class)
@Config(minSdk = Build.VERSION_CODES.Q, maxSdk = Build.VERSION_CODES.BAKLAVA)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    fun same(){
        operandOne.setText("428.96")

        // questo è un metodo che è stato aggiunto, non è un metodo che ho scritto io
        val d = activity.getOperand(operandOne)
        assertEquals(428.96, d, 0.0)
    }


    @Test
    fun operandConversion1_int(){
        operandOne.setText("3")

        // questo è un metodo che è stato aggiunto, non è un metodo che ho scritto io
        val d = activity.getOperand(operandOne)
        assertEquals(3.0, d, 0.0)
    }

    @Test
    fun operandConversion2_fp(){ // fp = floating point
        operandOne.setText("3.14")

        // questo è un metodo che è stato aggiunto, non è un metodo che ho scritto io
        val d = activity.getOperand(operandOne)
        assertEquals(3.14, d, 0.0)
    }

    @Test
    fun operandConversion3_neg(){
        operandOne.setText("-498.96")

        // questo è un metodo che è stato aggiunto, non è un metodo che ho scritto io
        val d = activity.getOperand(operandOne)
        assertEquals(-498.96, d, 0.0)
    }

    @Test(expected = NumberFormatException::class)
    fun operandConversion4_empty(){
        operandOne.setText("")

        // questo è un metodo che è stato aggiunto, non è un metodo che ho scritto io
        val d = activity.getOperand(operandOne)
    }
}