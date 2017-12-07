package com.example.luy.maninvan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class InfoActivity extends AppCompatActivity {
    String phone;
    String name;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        intent = getIntent();
        Bundle extras = intent.getExtras();
        phone = extras.getString("EXTRA_PHONE");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
    }

    public void inputName(View view){
        EditText editText = (EditText) findViewById(R.id.text_input_name);
        name = editText.getText().toString();
        Intent intent = new Intent(this, Code_verify.class);
        intent.putExtra("EXTRA_PHONE", phone);
        intent.putExtra("EXTRA_NAME", name);
        intent.putExtra("EXTRA_EXIST",false);
        startActivity(intent);

    }
}
