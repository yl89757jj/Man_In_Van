package com.example.luy.maninvan;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class Code_verify extends AppCompatActivity {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verify);
    }

    public void verifyCode(View view){
        Intent intent = new Intent(this, UserProfile.class);

        EditText editText = (EditText) findViewById(R.id.text_input_code);
        String code = editText.getText().toString();
        //TODO: REQUEST VERIFIED USER FROM DATABASE
        intent.putExtra(EXTRA_MESSAGE, user);
        startActivity(intent);

    }

}
