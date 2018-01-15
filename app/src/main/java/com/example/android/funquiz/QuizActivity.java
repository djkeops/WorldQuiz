package com.example.android.funquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    TextView WelcomeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //Display welcome message
        WelcomeTextView = findViewById(R.id.WelcomeTextView);
        String welcomeMessage = "Welcome, " + getUserName() + "!";
        WelcomeTextView.setText(welcomeMessage);

    }


    // Returns the user to the Main screen and finnish this activity when the back button is clicked.
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

    //Getting user name from MainActivity
    public String getUserName() {
        Intent openQuiz = getIntent();
        return openQuiz.getStringExtra(MainActivity.PLAYER_NAME);
    }

    //Make a view visible
    public void submit(View view) {

        //declare views
        int[] hintsSeparators = {R.id.separator_1_2, R.id.separator_2_2, R.id.separator_3_2, R.id.separator_4_2, R.id.separator_5_2, R.id.separator_6_2, R.id.separator_7_2, R.id.separator_8_2};
        int[] hintsTextViews = {R.id.hint1, R.id.hint2, R.id.hint3, R.id.hint4, R.id.hint5, R.id.hint6, R.id.hint7, R.id.hint8};

        //make hints separators visible
        for (int j = 0; j < hintsSeparators.length; j++) {
            View hintSeparator = findViewById(hintsSeparators[j]);
            hintSeparator.setVisibility(View.VISIBLE);
        }

        //make hins visible
        for (int j = 0; j < hintsTextViews.length; j++) {
            View hintTextView = findViewById(hintsTextViews[j]);
            hintTextView.setVisibility(View.VISIBLE);
        }

    }


}
