package com.venkateshpamarthi.myloginapp;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasErrorText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void loginActivityTest() {
        ViewInteraction appCompatAutoCompleteTextView = onView(
                withId(R.id.email));
        appCompatAutoCompleteTextView.perform(click());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.email_sign_in_button), withText("Sign in or register")));
        appCompatButton.perform(click());
        appCompatAutoCompleteTextView.check(matches(hasErrorText("This field is required")));
    }


    @Test
    public void checkValidEmailAddress(){
        ViewInteraction appCompatAutoCompleteTextView = onView(
                withId(R.id.email));
        appCompatAutoCompleteTextView.perform(click(),typeText("test"));
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.email_sign_in_button), withText("Sign in or register")));
        appCompatButton.perform(click());
        appCompatAutoCompleteTextView.check(matches(hasErrorText("This email address is invalid")));
    }

    @Test
    public void checkEmptyPassword(){
        ViewInteraction emailAddressTextView = onView(withId(R.id.email));
        emailAddressTextView.perform(typeText("test@gmail.com"));
        ViewInteraction passwordViewInteraction = onView(withId(R.id.password));
        ViewInteraction buttonViewInteraction = onView(withId(R.id.email_sign_in_button));
        buttonViewInteraction.perform(click());
        passwordViewInteraction.check(matches(hasErrorText("This field is required")));
    }

    @Test
    public void checkMinimumPasswordLength(){
        ViewInteraction emailViewInteraction = onView(withId(R.id.email));
        emailViewInteraction.perform(typeText("test@gmail.com"));
         ViewInteraction passwordViewInteraction = onView(withId(R.id.password));
        passwordViewInteraction.perform(typeText("1234"));
        ViewInteraction buttonViewInteraction = onView(withId(R.id.email_sign_in_button));
        buttonViewInteraction.perform(click());
        passwordViewInteraction.check(matches(hasErrorText("This password is too short")));
    }

}
