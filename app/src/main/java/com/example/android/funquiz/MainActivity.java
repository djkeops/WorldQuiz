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
    // two variables used in SharedPreferences
    public static final String FILE_NAME = "file_lang"; // preference file name
    public static final String KEY_LANG = "key_lang"; // preference key
    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLanguage();
        setContentView(R.layout.activity_main);
        nameField = findViewById(R.id.nameField);
    }

    /**
     * this method starts the quiz activity and loads the user name
     *
     * @param view beginButton
     */
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

    /**
     * this method loads the language menu
     *
     * @param menu menu_main.xml
     * @return true for the menu to be displayed
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * this method saves the last used language
     *
     * @param lang language code
     */
    private void saveLanguage(String lang) {
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_LANG, lang);
        editor.apply();
        recreate();
    }

    //this method loads the language
    private void loadLanguage() {
        Locale locale = new Locale(getLangCode());
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    /**
     * this method is getting the language code from preference
     *
     * @return language code
     */
    private String getLangCode() {
        SharedPreferences preferences = getSharedPreferences(FILE_NAME, MODE_PRIVATE);
        return preferences.getString(KEY_LANG, "en");
    }

    /**
     * this method is dealing with the language changing
     *
     * @param item selected item from the menu
     * @return true
     */
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
