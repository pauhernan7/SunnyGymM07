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
class NameTestRegistre {

    @get:Rule
    val activityRule = ActivityScenarioRule(RegisterActivity::class.java)

    @Test
    fun testEmptyUsername() {
        onView(withId(R.id.nameInput)).perform(typeText(" "))
        closeSoftKeyboard()
        onView(withId(R.id.registerButton)).perform(click())
        onView(withText("El nom d'usuari és obligatori")).check(matches(isDisplayed()))
    }

    @Test
    fun testUsernameTooShort() {
        onView(withId(R.id.nameInput)).perform(typeText("AB"))
        closeSoftKeyboard()
        onView(withId(R.id.registerButton)).perform(click())
        onView(withText("El nom ha de tenir almenys 3 caràcters")).check(matches(isDisplayed()))
    }

    @Test
    fun testUsernameTooLong() {
        onView(withId(R.id.nameInput)).perform(typeText("ABCDEFGHIJKLMNOPQRSTUVWXYZ"))
        closeSoftKeyboard()
        onView(withId(R.id.registerButton)).perform(click())
        onView(withText("El nom d’usuari és massa llarg")).check(matches(isDisplayed()))
    }

    @Test
    fun testUsernameWithSpecialChars() {
        onView(withId(R.id.nameInput)).perform(typeText("N4m#-¨"))
        closeSoftKeyboard()
        onView(withId(R.id.registerButton)).perform(click())
        onView(withText("Nom d’usuari no vàlid")).check(matches(isDisplayed()))
    }

    @Test
    fun testUsernameWithSpaces() {
        onView(withId(R.id.nameInput)).perform(typeText(" NaM3 "))
        closeSoftKeyboard()
        onView(withId(R.id.registerButton)).perform(click())
        onView(withText("El nom no pot tenir espais al principi o final")).check(matches(isDisplayed()))
    }

    @Test
    fun testUsernameOnlyNumbers() {
        onView(withId(R.id.nameInput)).perform(typeText("1234"))
        closeSoftKeyboard()
        onView(withId(R.id.registerButton)).perform(click())
        onView(withText("El nom d’usuari ha de contenir lletres")).check(matches(isDisplayed()))
    }
}
