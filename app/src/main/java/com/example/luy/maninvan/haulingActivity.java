package com.example.luy.maninvan;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HaulingActivity extends AppCompatActivity {
    String phoneNumber = "9177567156";
    String message = "Hi Luna, thanks for accepting my request...";
    Job job;
    boolean taken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauling);

        String name = "User";
        TextView mock = (TextView) findViewById(R.id.van_note);
        mock.setText(name + " has accept your job. Make sure to contact Luna to confirm pick up time and any details! Don't forget you need to pay in chash as soon as the job done.");
    }

    public void sendMessage(View view){
        //SMS APP will handle
        if(!taken){
//            job.beTook();
            taken=true;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
            intent.putExtra("sms_body", message);
            startActivity(intent);

        }else {
            Toast.makeText(HaulingActivity.this, "Congratulation! Your Job was Already Accepted.", Toast.LENGTH_LONG).show();
            Intent back = new Intent(this, UserProfile.class);
            startActivity(back);
        }


    }
}
