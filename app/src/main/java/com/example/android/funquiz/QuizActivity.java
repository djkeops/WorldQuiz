package com.example.android.funquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    TextView WelcomeTextView;
    //declare views
    int[] hintsSeparators = {R.id.separator_1_2, R.id.separator_2_2, R.id.separator_3_2, R.id.separator_4_2, R.id.separator_5_2, R.id.separator_6_2, R.id.separator_7_2, R.id.separator_8_2};
    int[] hintsTextViews = {R.id.hint1, R.id.hint2, R.id.hint3, R.id.hint4, R.id.hint6, R.id.hint7, R.id.hint5, R.id.hint8};
    int[] radioGroups = {R.id.radio_group_1, R.id.radio_group_2, R.id.radio_group_3, R.id.radio_group_4, R.id.radio_group_6, R.id.radio_group_7};
    int[] checkBoxes = {R.id.check_8_a, R.id.check_8_b, R.id.check_8_c, R.id.check_8_d, R.id.check_8_e};
    Button shareButton;
    Button resetButton;
    TextView answerQ5;
    TextView hintTextView;

    // declare a variable for the quiz results
    String resultMessage;
    int totalNoQuestions = 8;

    //declare correct answers;
    int[] correctRadioAnswers = {R.id.radio_1_a, R.id.radio_2_c, R.id.radio_3_d, R.id.radio_4_c, R.id.radio_6_a, R.id.radio_7_a};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        //declare buttons
        shareButton = findViewById(R.id.shareButton);
        resetButton = findViewById(R.id.restButton);

        //declare view with question 5 answer
        answerQ5 = findViewById(R.id.answer_edit_text_5);

        //Display welcome message
        WelcomeTextView = findViewById(R.id.WelcomeTextView);
        String welcomeMessage = getString(R.string.welcome) + getUserName() + "!";
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

    //counts no. of answered questions
    public int countAnswers() {
        int answeredQuestions = 0;
        for (int groupID : radioGroups) {
            RadioGroup radioGroup = findViewById(groupID);
            if (radioGroup.getCheckedRadioButtonId() != -1) answeredQuestions++;
        }
        if (!answerQ5.getText().toString().equals("")) {
            answeredQuestions++;
        }
        for (int checkBoxID : checkBoxes) {
            CheckBox checkBox = findViewById(checkBoxID);
            if (checkBox.isChecked()) {
                answeredQuestions++;
                break;
            }
        }
        return answeredQuestions;
    }

    //this method is called when the submit button is clicked and calculate results according to the user answers
    public void submit(View view) {

        //Correct answers score
        int score = 0;
        //Incorrect answers score
        int incorrectScore = 0;

        //check if at least one question is answered
        int answeredQuestions = countAnswers();
        if (answeredQuestions == 0) {
            Toast.makeText(this, R.string.zero_answers_message, Toast.LENGTH_SHORT).show();
        } else {
            shareButton.setAlpha(1);
            shareButton.setEnabled(true);

            //check radioGroups answers
            for (int j = 0; j < radioGroups.length; j++) {
                RadioGroup radioGroup = findViewById(radioGroups[j]);
                hintTextView = findViewById(hintsTextViews[j]);
                RadioButton checkedRadioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                if (radioGroup.getCheckedRadioButtonId() == correctRadioAnswers[j]) {
                    score++;
                    checkedRadioButton.setTextColor(getResources().getColor(R.color.colorCorrect));
                    hintTextView.setTextColor(getResources().getColor(R.color.colorCorrect));
                } else if (radioGroup.getCheckedRadioButtonId() != -1) {
                    incorrectScore++;
                    checkedRadioButton.setTextColor(getResources().getColor(R.color.colorWrong));
                }
            }

            //check Text answer
            if (answerQ5.getText().toString().trim().equals(getString(R.string.answer5))) {
                score++;
                answerQ5.setTextColor(getResources().getColor(R.color.colorCorrect));
                hintTextView = findViewById(hintsTextViews[6]);
                hintTextView.setTextColor(getResources().getColor(R.color.colorCorrect));
            } else if (!answerQ5.getText().toString().trim().equals("")) {
                incorrectScore++;
                hintTextView = findViewById(hintsTextViews[6]);
                hintTextView.setTextColor(getResources().getColor(R.color.colorWrong));
            }

            //check Check answers
            CheckBox checkBox1 = findViewById(checkBoxes[0]);
            CheckBox checkBox2 = findViewById(checkBoxes[1]);
            CheckBox checkBox3 = findViewById(checkBoxes[2]);
            CheckBox checkBox4 = findViewById(checkBoxes[3]);
            CheckBox checkBox5 = findViewById(checkBoxes[4]);
            if (checkBox2.isChecked() && checkBox4.isChecked() && !checkBox1.isChecked() && !checkBox3.isChecked() && !checkBox5.isChecked()) {
                score++;
                checkBox2.setTextColor(getResources().getColor(R.color.colorCorrect));
                checkBox4.setTextColor(getResources().getColor(R.color.colorCorrect));
                hintTextView = findViewById(hintsTextViews[7]);
                hintTextView.setTextColor(getResources().getColor(R.color.colorCorrect));
            } else {


                for (int checkBoxID : checkBoxes) {
                    CheckBox checkBox = findViewById(checkBoxID);
                    if (checkBox.isChecked()) {
                        incorrectScore++;
                        break;
                    }
                }


                hintTextView = findViewById(hintsTextViews[7]);
                hintTextView.setTextColor(getResources().getColor(R.color.colorWrong));

                for (int checkBoxID : checkBoxes) {
                    CheckBox checkBox = findViewById(checkBoxID);
                    if (checkBox.isChecked() && (checkBoxID == checkBoxes[1] || checkBoxID == checkBoxes[3])) {
                        checkBox.setTextColor(getResources().getColor(R.color.colorCorrect));
                    } else if (checkBox.isChecked()) {
                        checkBox.setTextColor(getResources().getColor(R.color.colorWrong));
                    } else if (!checkBox.isChecked() && (checkBoxID == checkBoxes[1] || checkBoxID == checkBoxes[3])) {
                        checkBox.setTextColor(getResources().getColor(R.color.colorWrong));
                    }
                }
            }

            makeHintsVisible();

            String resultMessage = createScoreSummary(score, incorrectScore, answeredQuestions);
            Toast.makeText(this, resultMessage, Toast.LENGTH_LONG).show();
        }

    }

    //this method make the hints visible
    public void makeHintsVisible() {
        //make hints separators visible
        for (int hintSeparatorID : hintsSeparators) {
            View hintSeparator = findViewById(hintSeparatorID);
            hintSeparator.setVisibility(View.VISIBLE);
        }

        //make hints visible
        for (int hintTextViewID : hintsTextViews) {
            hintTextView = findViewById(hintTextViewID);
            hintTextView.setVisibility(View.VISIBLE);
        }

    }

    //this method is called when the reset button is clicked and reset all answers restarting the activity
    public void reset(View view) {
        Intent resetQuiz = new Intent(this, QuizActivity.class);
        String message = getUserName();
        resetQuiz.putExtra(MainActivity.PLAYER_NAME, message);
        startActivity(resetQuiz);
        this.finish();
    }

    //this method creates Score summary.
    private String createScoreSummary(int score, int incorrectScore, int noAnsweredQuestions) {
        resultMessage = getUserName() + getString(R.string.result_name_message);
        resultMessage += "\n" + getString(R.string.result_correct) + score + "/" + totalNoQuestions;
        resultMessage += "\n" + getString(R.string.resut_wrong) + incorrectScore + "/" + totalNoQuestions;
        resultMessage += "\n" + getString(R.string.result_answered) + noAnsweredQuestions + "/" + totalNoQuestions;
        return resultMessage;
    }

    // this method is called when the Share button is clicked and allow the user to share the game results via e-mail, social apps, etc.
    public void share(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, resultMessage);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
