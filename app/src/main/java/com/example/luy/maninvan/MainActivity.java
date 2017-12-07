package com.example.luy.maninvan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    boolean exist = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendCode(View view){
        EditText editText = (EditText) findViewById(R.id.text_input_phone);
        String phone = editText.getText().toString();
        //TODO: Check the existance of phone number in database
        if(exist){//jump to password verify
            Intent intent = new Intent(this, Code_verify.class);
            intent.putExtra("EXTRA_PHONE", phone);
            intent.putExtra("EXTRA_EXIST", exist);
            startActivity(intent);
        }else {//Jump to name input and create new account
            Intent intent = new Intent(this, InfoActivity.class);
            intent.putExtra("EXTRA_PHONE", phone);
            startActivity(intent);
        }

    }
}
