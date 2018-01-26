package com.example.android.funquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    public final static String PLAYER_NAME = "com.example.android.funquiz.MESSAGE";
    // two variables used by SharedPreferences
    private static final String FILE_NAME = "file_lang"; // preference file name
    private static final String KEY_LANG = "key_lang"; // preference key
    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLanguage();
        setContentView(R.layout.activity_main);
        nameField = findViewById(R.id.nameField);
    }

    //this method start the quiz activity and load the user name
    public void start(View view) {
        Intent openQuiz = new Intent(this, QuizActivity.class);
        String message = nameField.getText().toString().trim();
        if (message.equals("")) {
            Toast.makeText(this, R.string.no_name_error, Toast.LENGTH_SHORT).show();
        } else {
            openQuiz.putExtra(PLAYER_NAME, message);
            startActivity(openQuiz);
            this.finish();
        }
    }

    //this method load the language menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // this method save the last language
    private void saveLanguage(String lang) {
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_LANG, lang);
        editor.apply();
        recreate();
    }

    //this method load the language
    private void loadLanguage() {
        Locale locale = new Locale(getLangCode());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
        setTitle(getResources().getString(R.string.app_name));
    }

    //this method save english as the default language
    private String getLangCode() {
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        String langCode = preferences.getString(KEY_LANG, "en");
        return langCode;
    }

    //this method deal language changing
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.language_english:
                saveLanguage("en");
                loadLanguage();
                break;

            case R.id.language_romanian:
                saveLanguage("ro");
                loadLanguage();
                break;
        }
        return true;
    }

}
