package com.example.growighassignment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var nextImageButton: ImageView
    private lateinit var sharedPref: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (onBoardingFinished()) {
            Log.d("SharedPref", "In the if part shared pref")
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        } else {
            Log.d("SharedPref", "In the else part shared pref")
            val editor = sharedPref.edit()
            editor.putBoolean("Finished", true)
            editor.apply()
            setOnboardingItems()
        }
    }

    private fun setOnboardingItems() {
        onboardingItemsAdapter = OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                    onboardingImage = R.drawable.onboarding_image_1,
                    onboardingText = "About Us",
                    onboardingDesc = getString(R.string.onBoardingDesc_1),
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.onboarding_image_2,
                    onboardingText = "Our Mission",
                    onboardingDesc = getString(R.string.onBoardingDesc_2),
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.onboarding_image_3,
                    onboardingText = "Our Vision",
                    onboardingDesc = getString(R.string.onBoardingDesc_3),
                )
            )
        )

        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingViewPager)
        onboardingViewPager.adapter = onboardingItemsAdapter
        nextImageButton = findViewById(R.id.nextButton)

        Log.d("MainActivity OutSide", "Value is:  ${onboardingViewPager.currentItem}")
//        if (onboardingViewPager.currentItem == 0) {
//            nextImageButton.setImageResource(R.drawable.progress_button_1)
//        }
//        if (onboardingViewPager.currentItem == 1) {
//            nextImageButton.setImageResource(R.drawable.progress_button_2)
//        } else {
//            nextImageButton.setImageResource(R.drawable.progress_button_3)
//        }

        nextImageButton.setOnClickListener {
            if (onboardingViewPager.currentItem + 1 < onboardingItemsAdapter.itemCount) {
                Log.d("MainActivity", "In the If")
                Log.d("MainActivity", "Value is:  ${onboardingViewPager.currentItem}")
                if (onboardingViewPager.currentItem == 0) {
                    nextImageButton.setImageResource(R.drawable.progress_button_2)
                }
                if (onboardingViewPager.currentItem == 1) {
                    nextImageButton.setImageResource(R.drawable.progress_button_3)
                }
                onboardingViewPager.currentItem += 1
            } else {
                Log.d("MainActivity", "In the Else")
                navigateToWelcomeScreen()
            }
        }
        findViewById<TextView>(R.id.skipIntroBtn).setOnClickListener {
            navigateToWelcomeScreen()
        }

    }

    private fun navigateToWelcomeScreen() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }

    private fun onBoardingFinished(): Boolean {
        sharedPref = getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
//        val editor= sharedPref.edit()
//        editor.putBoolean("Finished", true)
//        editor.apply()
        return sharedPref.getBoolean("Finished", false)
    }
}