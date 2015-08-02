package com.example.android.library;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.TextView;

public class JokesActivity extends AppCompatActivity {

    public static final String JOKE_TEXT_STRING = "joke.text.string";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        String joke = getIntent().getStringExtra(JOKE_TEXT_STRING);
        ((TextView) findViewById(R.id.jokeTextView)).setText(joke);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jokes, menu);
        return true;
    }


}
