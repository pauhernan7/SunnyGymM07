package com.example.sunnygym

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sunnygym.Register.RegisterActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NameTestRegistre {

    @get:Rule
    val activityRule = ActivityScenarioRule(RegisterActivity::class.java)

    @Test
    fun testEmptyUsername() {
        onView(withId(R.id.usernameEditText)).perform(typeText(" "))
        closeSoftKeyboard()
        onView(withId(R.id.registerButton)).perform(click())
        onView(withText("El nom d'usuari és obligatori")).check(matches(isDisplayed()))
    }

    @Test
    fun testUsernameTooShort() {
        onView(withId(R.id.usernameEditText)).perform(typeText("AB"))
        closeSoftKeyboard()
        onView(withId(R.id.registerButton)).perform(click())
        onView(withText("El nom ha de tenir almenys 3 caràcters")).check(matches(isDisplayed()))
    }

    // Añade aquí tests para massa llarg, caràcters especials, només números, espais, etc.
}
