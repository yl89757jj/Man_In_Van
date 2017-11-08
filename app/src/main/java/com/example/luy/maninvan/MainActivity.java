package com.example.luy.maninvan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendCode(View view){
        EditText editText = (EditText) findViewById(R.id.text_input_phone);
        String message = editText.getText().toString();
        Intent intent = new Intent(this, Code_verify.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
