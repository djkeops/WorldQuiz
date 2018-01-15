package com.example.android.funquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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


}
