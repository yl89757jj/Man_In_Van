package com.example.luy.maninvan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class HaulingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hauling);


        TextView mock = (TextView) findViewById(R.id.mock);
        mock.setText("Mock Info");
    }
}
