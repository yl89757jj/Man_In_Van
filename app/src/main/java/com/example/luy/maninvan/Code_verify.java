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
    String phone;
    String name;
    boolean exist = false;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        Bundle extras = intent.getExtras();
        phone = extras.getString("EXTRA_PHONE");
        name = extras.getString("EXTRA_NAME");
        exist = (boolean)extras.get("EXTRA_EXIST");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verify);
    }

    public void verifyCode(View view){
        EditText editText = (EditText) findViewById(R.id.text_input_code);
        String password = editText.getText().toString();
//        if(exist){
//            user = new User(name,phone,false);//create new user
//        }else{
////      TODO: set verified user
//        }

        //TODO: REQUEST VERIFIED USER FROM DATABASE
        Intent intent = new Intent(this, UserProfile.class);
        intent.putExtra("EXTRA_USER", user);
        startActivity(intent);
    }

}
