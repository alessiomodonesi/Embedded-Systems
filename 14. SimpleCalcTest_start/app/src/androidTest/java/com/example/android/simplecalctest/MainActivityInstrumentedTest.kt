package com.example.android.simplecalctest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// qui utilizziamo Espresso
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    @get:Rule // senza questo non viene generato il codice che serve a rendere visibile l'activity ad Espresso
    val rule: ActivityScenarioRule<MainActivity> =
        ActivityScenarioRule(MainActivity::class.java)

    // ESERCIZIO 8 (guidato dal docente)
    @Test
    fun addTwoNumbers(){
        // closeSoftKeyboard() -> chiude la tastiera
        onView(withId(R.id.operand_one_edit_text))
            .perform(typeText("1.0"), closeSoftKeyboard())

        onView(withId(R.id.operand_two_edit_text))
            .perform(typeText("1.0"), closeSoftKeyboard())

        onView(withId(R.id.operation_add_btn))
            .perform(click())

        onView(withId(R.id.operation_result_text_view))
            .check(matches(withText("2.0")))
    }

    // ESERCIZIO 9
    @Test
    fun divTwoNumbers(){
        onView(withId(R.id.operand_one_edit_text))
            .perform(typeText("32.0"), closeSoftKeyboard())

        onView(withId(R.id.operand_two_edit_text))
            .perform(typeText("0.0"), closeSoftKeyboard())

        onView(withId(R.id.operation_div_btn))
            .perform(click())

        onView(withId(R.id.operation_result_text_view))
            .check(matches(withText("Error")))
    }

    @Test
    fun recreateActivity(){
        val scenario = rule.scenario
        scenario.onActivity { activity ->
            activity.recreate()
        }
    }
}