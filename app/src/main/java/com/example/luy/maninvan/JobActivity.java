package com.example.luy.maninvan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import java.sql.Time;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class JobActivity extends AppCompatActivity {
    User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

    }

    public void submitJobRequest(View view){

//catch user input and create Job object
        EditText editText = (EditText) findViewById(R.id.input_rooms);
        int rooms = Integer.valueOf(editText.getText().toString());

        EditText editText2 = (EditText) findViewById(R.id.input_startTime);
        Time startTime = Time.valueOf(editText2.getText().toString());

        EditText editText3 = (EditText) findViewById(R.id.input_endTime);
        Time endTime = Time.valueOf(editText3.getText().toString());

        EditText editText4 = (EditText) findViewById(R.id.input_price);
        int price = Integer.valueOf(editText4.getText().toString());

        EditText editText5 = (EditText) findViewById(R.id.input_description);
        String description = editText5.getText().toString();

        Job newJob = new Job(user,rooms,startTime,endTime,price,description);

        Intent intent = new Intent(this,HaulingActivity.class);
        //Push job to database
//        String message = "Information or Object that should be sent to next activity";
//        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
