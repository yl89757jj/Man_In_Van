package com.example.luy.maninvan;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HaulingActivity extends AppCompatActivity {
    String phoneNumber = "9177567156";
    String message = "Hi Luna, thanks for accepting my request...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauling);

        String name = "User";
        TextView mock = (TextView) findViewById(R.id.van_note);
        mock.setText(name + "Accept");
    }

    public void sendMessage(View view){
        //SMS APP will handle
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
        intent.putExtra("sms_body", message);
        startActivity(intent);
    }
}
