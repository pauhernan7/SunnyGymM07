package com.example.sunnygym

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.sunnygym.Register.RegisterActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*


@RunWith(AndroidJUnit4::class)
class PasswordTestRegistre {

    @get:Rule
    val activityRule = ActivityScenarioRule(RegisterActivity::class.java)

    @Test
    fun testEmptyPassword() {
        onView(withId(R.id.registerPasswordInput)).perform(typeText(""))
        onView(withId(R.id.RepeatPasswordInput)).perform(typeText(""))
        closeSoftKeyboard()
        onView(withId(R.id.registerConfirmButton)).perform(click())
        onView(withText("La contrasenya és obligatòria")).check(matches(isDisplayed()))
    }

    @Test
    fun testEmptyConfirmPassword() {
        onView(withId(R.id.registerPasswordInput)).perform(typeText("Password1*"))
        onView(withId(R.id.RepeatPasswordInput)).perform(typeText(""))
        closeSoftKeyboard()
        onView(withId(R.id.registerConfirmButton)).perform(click())
        onView(withText("Heu de confirmar la contrasenya")).check(matches(isDisplayed()))
    }

    @Test
    fun testPasswordMismatch() {
        onView(withId(R.id.registerPasswordInput)).perform(typeText("NaM3*"))
        onView(withId(R.id.RepeatPasswordInput)).perform(typeText("NaN2!"))
        closeSoftKeyboard()
        onView(withId(R.id.registerConfirmButton)).perform(click())
        onView(withText("Les contrasenyes no coincideixen")).check(matches(isDisplayed()))
    }

    @Test
    fun testPasswordTooShort() {
        onView(withId(R.id.registerPasswordInput)).perform(typeText("N4n0*"))
        onView(withId(R.id.RepeatPasswordInput)).perform(typeText("N4n0*"))
        closeSoftKeyboard()
        onView(withId(R.id.registerConfirmButton)).perform(click())
        onView(withText("La contrasenya ha de tenir almenys 6 caràcters")).check(matches(isDisplayed()))
    }

    @Test
    fun testPasswordTooLong() {
        onView(withId(R.id.registerPasswordInput)).perform(typeText("Passwordinfinit12345678*"))
        onView(withId(R.id.RepeatPasswordInput)).perform(typeText("Passwordinfinit12345678*"))
        closeSoftKeyboard()
        onView(withId(R.id.registerConfirmButton)).perform(click())
        onView(withText("La contrasenya és massa llarga")).check(matches(isDisplayed()))
    }

    @Test
    fun testPasswordNoUppercase() {
        onView(withId(R.id.registerPasswordInput)).perform(typeText("password2*"))
        onView(withId(R.id.RepeatPasswordInput)).perform(typeText("password2*"))
        closeSoftKeyboard()
        onView(withId(R.id.registerConfirmButton)).perform(click())
        onView(withText("Ha d’incloure almenys una majúscula")).check(matches(isDisplayed()))
    }

    @Test
    fun testPasswordNoLowercase() {
        onView(withId(R.id.registerPasswordInput)).perform(typeText("PASSWORD2*"))
        onView(withId(R.id.RepeatPasswordInput)).perform(typeText("PASSWORD2*"))
        closeSoftKeyboard()
        onView(withId(R.id.registerConfirmButton)).perform(click())
        onView(withText("Ha d’incloure almenys una minúscula")).check(matches(isDisplayed()))
    }

    @Test
    fun testPasswordNoNumber() {
        onView(withId(R.id.registerPasswordInput)).perform(typeText("Password*"))
        onView(withId(R.id.RepeatPasswordInput)).perform(typeText("Password*"))
        closeSoftKeyboard()
        onView(withId(R.id.registerConfirmButton)).perform(click())
        onView(withText("Ha d’incloure almenys un número")).check(matches(isDisplayed()))
    }

    @Test
    fun testPasswordNoSymbol() {
        onView(withId(R.id.registerPasswordInput)).perform(typeText("Password2"))
        onView(withId(R.id.RepeatPasswordInput)).perform(typeText("Password2"))
        closeSoftKeyboard()
        onView(withId(R.id.registerConfirmButton)).perform(click())
        onView(withText("Inclou un símbol")).check(matches(isDisplayed()))
    }
}
