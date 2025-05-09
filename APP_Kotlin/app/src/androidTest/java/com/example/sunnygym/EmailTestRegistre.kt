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
class EmailTestRegistre {

    @get:Rule
    val activityRule = ActivityScenarioRule(RegisterActivity::class.java)

    @Test
    fun testEmptyEmail() {
        onView(withId(R.id.registerEmailInput)).perform(typeText(" "))
        closeSoftKeyboard()
        onView(withId(R.id.registerConfirmButton)).perform(click())
        onView(withText("El correu electrònic és obligatori")).check(matches(isDisplayed()))
    }

    @Test
    fun testInvalidEmailFormat() {
        onView(withId(R.id.registerEmailInput)).perform(typeText("Seth.perez"))
        closeSoftKeyboard()
        onView(withId(R.id.registerConfirmButton)).perform(click())
        onView(withText("Format de correu electrònic no vàlid")).check(matches(isDisplayed()))
    }

    @Test
    fun testEmailAlreadyRegistered() {
        onView(withId(R.id.registerEmailInput)).perform(typeText("Seth.perez@gmail.com"))
        closeSoftKeyboard()
        onView(withId(R.id.registerConfirmButton)).perform(click())
        onView(withText("Aquest correu ja està registrat")).check(matches(isDisplayed()))
    }

    @Test
    fun testEmailWithInvalidCharacters() {
        onView(withId(R.id.registerEmailInput)).perform(typeText("usuari<>@mail.com"))
        closeSoftKeyboard()
        onView(withId(R.id.registerConfirmButton)).perform(click())
        onView(withText("Caràcter no permès en una direcció de correu electrònic")).check(matches(isDisplayed()))
    }

    @Test
    fun testEmailWithUppercase() {
        onView(withId(R.id.registerEmailInput)).perform(typeText("FERNANDO.MG@HOTMAIL.COM"))
        closeSoftKeyboard()
        onView(withId(R.id.registerConfirmButton)).perform(click())
        onView(withText("Caràcter no permès en una adreça de correu electrònic")).check(matches(isDisplayed()))
    }
}