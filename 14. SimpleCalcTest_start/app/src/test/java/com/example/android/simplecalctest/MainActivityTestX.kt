package com.example.android.simplecalctest

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

// inserisco una activity in uno scenario fittizzio per eseguire dei test su di essa

@RunWith(AndroidJUnit4::class) // will delegate to RoboelectricTestRunner
// Since no minSdk or maxSdk are specified (see MainActivitytest.kt)
// tests will be run only against the latest Android version
class MainActivityTestX {
    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp(){
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun addition_isCorrect(){
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addTwoNumbers(){
        scenario.moveToState(Lifecycle.State.RESUMED) // = stato running
        scenario.onActivity { activity:MainActivity -> // posso fare tutto (invocre metodi) quello che l'activity solitamente fa

            // never keep references obtained into your action
            // because the activity can be recreated at any time during state transitions
            val operandOne: EditText = activity.findViewById(R.id.operand_one_edit_text)
            val operandTwo: EditText = activity.findViewById(R.id.operand_two_edit_text)
            val operandionAdd: Button = activity.findViewById(R.id.operation_add_btn)
            val operationResult: TextView = activity.findViewById(R.id.operation_result_text_view)

            operandOne.setText("1.0")
            operandTwo.setText("1.0")
            operandionAdd.performClick()
            assertEquals("2.0", operationResult.text.toString())
        }
    }

    @Test
    fun divByZero(){
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.onActivity { activity:MainActivity ->
            val operandOne: EditText = activity.findViewById(R.id.operand_one_edit_text)
            val operandTwo: EditText = activity.findViewById(R.id.operand_two_edit_text)
            val operandionDiv: Button = activity.findViewById(R.id.operation_div_btn)
            val operationResult: TextView = activity.findViewById(R.id.operation_result_text_view)

            operandOne.setText("32.0")
            operandTwo.setText("0.0")
            operandionDiv.performClick()
            assertEquals(activity.getString(R.string.computationError), operationResult.text.toString())
        }
    }

    @After
    fun cleanUp(){
        scenario.close()
    }
}
