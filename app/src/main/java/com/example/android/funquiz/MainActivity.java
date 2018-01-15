package com.example.android.funquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public final static String PLAYER_NAME = "com.example.android.funquiz.MESSAGE";
    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameField = findViewById(R.id.nameField);
    }

    public void start(View view) {
        Intent openQuiz = new Intent(this, QuizActivity.class);
        String message = nameField.getText().toString().trim();
        if (message.equals("")) {
            Toast.makeText(this, R.string.no_name_error, Toast.LENGTH_SHORT).show();
            return;
        } else {
            openQuiz.putExtra(PLAYER_NAME, message);
            startActivity(openQuiz);
            this.finish();
        }
    }


}
