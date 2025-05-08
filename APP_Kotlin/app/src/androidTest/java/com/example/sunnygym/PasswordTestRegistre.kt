package com.example.sunnygym

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sunnygym.Register.RegisterActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PasswordTestRegistre {

    @get:Rule
    val activityRule = ActivityScenarioRule(RegisterActivity::class.java)

    @Test
    fun testEmptyPassword() {
        onView(withId(R.id.passwordEditText)).perform(typeText(""))
        onView(withId(R.id.confirmPasswordEditText)).perform(typeText(""))
        closeSoftKeyboard()
        onView(withId(R.id.registerButton)).perform(click())
        onView(withText("La contrasenya és obligatòria")).check(matches(isDisplayed()))
    }

    @Test
    fun testEmptyConfirmPassword() {
        onView(withId(R.id.passwordEditText)).perform(typeText("Password1*"))
        onView(withId(R.id.confirmPasswordEditText)).perform(typeText(""))
        closeSoftKeyboard()
        onView(withId(R.id.registerButton)).perform(click())
        onView(withText("Heu de confirmar la contrasenya")).check(matches(isDisplayed()))
    }

    @Test
    fun testPasswordMismatch() {
        onView(withId(R.id.passwordEditText)).perform(typeText("Password1*"))
        onView(withId(R.id.confirmPasswordEditText)).perform(typeText("Password2*"))
        closeSoftKeyboard()
        onView(withId(R.id.registerButton)).perform(click())
        onView(withText("Les contrasenyes no coincideixen")).check(matches(isDisplayed()))
    }

    // Añade aquí los otros tests: massa curta, massa llarga, sense majúscula, etc.
}
