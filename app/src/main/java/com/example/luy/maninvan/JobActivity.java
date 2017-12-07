package com.example.luy.maninvan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Time;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;


public class JobActivity extends AppCompatActivity {
    private String rooms;
    private String startTime;
    private String endTime;
    private String description;
    private String distance;
    EditText editText ;
    EditText editText2 ;
    EditText editText3 ;
    EditText editText4 ;
    EditText editText5 ;
    EditText editText6 ;
    private String price;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job);

    }

    public void submitJobRequest(View view){
//catch user input and create Job object
        editText = (EditText) findViewById(R.id.input_rooms);
        editText2 = (EditText) findViewById(R.id.input_startTime);
        editText3 = (EditText) findViewById(R.id.input_endTime);
        editText4 = (EditText) findViewById(R.id.input_price);
        editText5 = (EditText) findViewById(R.id.input_description);
        editText6 = (EditText) findViewById(R.id.input_distance);

        rooms = editText.getText().toString();
        startTime = editText2.getText().toString();
        endTime = editText3.getText().toString();
        price = editText4.getText().toString();
        description = editText5.getText().toString();
        distance=editText6.getText().toString();


        if(rooms.equals("")||startTime.equals("")||endTime.equals("")||price.equals("")||distance.equals("")){
            Toast.makeText(JobActivity.this, "Please Enter Job Information.", Toast.LENGTH_LONG).show();
        }else{
            //TODO:Accordingly Create Job and Push job to database
        Job newJob = new Job(user,parseInt(rooms),startTime,endTime,parseInt(distance),parseInt(price));
        newJob.description=description;
//        String message = "Information or Object that should be sent to next activity";
//        intent.putExtra(EXTRA_MESSAGE, message);
            Intent intent = new Intent(this,HaulingActivity.class);
            startActivity(intent);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out:
                //Log Out from App
                Intent intent1 = new Intent(JobActivity.this, MainActivity.class);
                return true;
            case R.id.user:
                Intent intent = new Intent(JobActivity.this, UserProfile.class);
                startActivity(intent);
            case R.id.receive:
                Intent intent2 = new Intent(JobActivity.this, HaulingActivity.class);
                startActivity(intent2);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
