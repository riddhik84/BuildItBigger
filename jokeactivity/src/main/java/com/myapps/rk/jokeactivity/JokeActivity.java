package com.myapps.rk.jokeactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        getSupportActionBar().setDisplayShowTitleEnabled(true);

        TextView tv = (TextView) findViewById(R.id.joke_text);
        Intent intent = getIntent();
        if (intent.getStringExtra(Intent.EXTRA_TEXT) != null) {
            tv.setText(intent.getStringExtra(Intent.EXTRA_TEXT));
        } else {
            tv.setText("No Joke found!");
        }
    }
}
