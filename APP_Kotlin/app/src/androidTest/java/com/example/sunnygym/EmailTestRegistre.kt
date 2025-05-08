package com.example.sunnygym


import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sunnygym.Register.RegisterActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class EmailTestRegistre {

    @get:Rule
    val activityRule = ActivityScenarioRule(RegisterActivity::class.java)

    @Test
    fun testEmptyEmail() {
        onView(withId(R.id.emailEditText)).perform(typeText(" "))
        closeSoftKeyboard()
        onView(withId(R.id.registerButton)).perform(click())
        onView(withText("El correu electrònic és obligatori")).check(matches(isDisplayed()))
    }

    @Test
    fun testInvalidEmailFormat() {
        onView(withId(R.id.emailEditText)).perform(typeText("Seth.perez"))
        closeSoftKeyboard()
        onView(withId(R.id.registerButton)).perform(click())
        onView(withText("Format de correu electrònic no vàlid")).check(matches(isDisplayed()))
    }

    // Añade aquí tests para correu ja registrat, caràcters especials, majúscules, etc.
}
