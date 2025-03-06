package com.example.scanme;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.hololo.tutorial.library.Step;
import com.hololo.tutorial.library.TutorialActivity;
//import com.msayan.tutorialview.Step;
//import com.msayan.tutorialview.TutorialActivity;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class OnBoardingScreen extends TutorialActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addFragment(new Step.Builder().setTitle("Extract text from images")
                .setContent("Recognize text in images with ML Kit on Android")
                .setBackgroundColor(Color.parseColor("#FF0957"))
                .setDrawable(R.drawable.onboard1)
                .setSummary("You can use ML Kit to recognize text in images or video, such as the text of a street sign.")
                .build());

        addFragment(new Step.Builder().setTitle("Copy and use the Extracted text in seconds")
                .setContent("Recognize text in images with ML Kit on Android")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.onboard2) // int top drawable
                .setSummary("You can use ML Kit to recognize text in images or video, such as the text of a street sign.")
                .build());

        addFragment(new Step.Builder().setTitle("This is header")
                .setContent("Recognize text in images with ML Kit on Android")
                .setBackgroundColor(Color.parseColor("#FF0957")) // int background color
                .setDrawable(R.drawable.onboard1) // int top drawable
                .setSummary("You can use ML Kit to recognize text in images or video, such as the text of a street sign.")
                .build());

    }

    @Override
    public void finishTutorial(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Finish the current activity
    }

    @Override
    public void currentFragmentPosition(int position) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}