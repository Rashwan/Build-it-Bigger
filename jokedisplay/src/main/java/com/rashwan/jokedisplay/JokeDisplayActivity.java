package com.rashwan.jokedisplay;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {
    public static final String EXTRA_JOKE = "com.rashwan.jokedisplay.EXTRA_JOKE";

    public static Intent getDisplayIntent(Context context,String joke){
        Intent intent = new Intent(context,JokeDisplayActivity.class);
        intent.putExtra(EXTRA_JOKE,joke);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);
        Intent intent = getIntent();
        String joke  = intent.getStringExtra(EXTRA_JOKE);
        TextView tv_joke = (TextView) findViewById(R.id.joke);
        tv_joke.setText(joke);
    }
}
