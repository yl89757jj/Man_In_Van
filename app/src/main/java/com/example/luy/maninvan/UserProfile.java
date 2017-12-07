package com.example.luy.maninvan;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.gms.identity.intents.model.UserAddress;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class UserProfile extends AppCompatActivity {
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_PICK_PHOTO = 2;
    private ImageView PhotoImageView;
    private TextView nameView;
    private TextView numberView;
    private RecyclerView recyclerView;
    private JobAdapter jobAdapter;
    private File photoFile;
    private Intent intent;
    ArrayList<Job> jobs = new ArrayList<Job>();
    private User user = new User("Tester","123-456-7890",false);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        intent = getIntent();
//        Bundle extras = intent.getExtras();
//        user = (User)extras.get("EXTRA_USER");
        Job job1 = new Job(user,2,"12:00","3:00",5,200);
        Job job2 = new Job(user,1,"12:00","3:00",10,100);
        Job job3 = new Job(user,3,"12:00","3:00",12,250);
        jobs.add(job1);
        jobs.add(job2);
        jobs.add(job3);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        if (shouldAskPermissions()) {
            askPermissions();
        }

        PhotoImageView = findViewById(R.id.user_selfie);
        recyclerView = findViewById(R.id.resultRecycler_view);
        nameView = findViewById(R.id.user_name);
        numberView = findViewById(R.id.user_phone);


        //TODO: Request DATA from database and create list of user's jobs; Input parameter should be a list of JOB
        jobAdapter = new JobAdapter(jobs,UserProfile.this);
        recyclerView.setAdapter(jobAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        TODO: showing user info
        nameView.setText(user.name);
        numberView.setText(user.phone);
    }




    public void addJob(View view){
        Intent intent = new Intent(this,JobActivity.class);
        intent.putExtra(EXTRA_MESSAGE,  user);
        startActivity(intent);
    }

    public void takeImage(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoFile = createImageFile();//tell the camera where to save the image
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }

    private File createImageFile() {
        String imageFileName = "JPEG" + System.currentTimeMillis() + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(storageDir.getAbsolutePath(), imageFileName);
    }

    public void addImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_PICK_PHOTO);
    }

    private void setPic() {
        int targetW = PhotoImageView.getWidth();
        int targetH = PhotoImageView.getHeight();

        BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
        bmpOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoFile.getAbsolutePath(), bmpOptions);

        int photoW = bmpOptions.outWidth;
        int photoH = bmpOptions.outHeight;
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        bmpOptions.inJustDecodeBounds = false;
        bmpOptions.inSampleSize = scaleFactor;
        Bitmap image = BitmapFactory.decodeFile(photoFile.getAbsolutePath(), bmpOptions);
        PhotoImageView.setImageBitmap(image);
//TODO: Update User's Selfie
    }

    private void decodeUri(Uri uri) throws FileNotFoundException {
        int targetW = PhotoImageView.getWidth();
        int targetH = PhotoImageView.getHeight();

        BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
        bmpOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, bmpOptions);

        int photoW = bmpOptions.outWidth;
        int photoH = bmpOptions.outHeight;
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        bmpOptions.inJustDecodeBounds = false;
        bmpOptions.inSampleSize = scaleFactor;
        Bitmap image = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, bmpOptions);
        PhotoImageView.setImageBitmap(image);
    }

    private String bitmapToByteString(Bitmap bitmap) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteStream);
        byte[] byteArray = byteStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    private Bitmap byteStringToBitmap(String byteString) {
        byte[] imageAsByte = Base64.decode(byteString.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsByte, 0, imageAsByte.length);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        if (requestCode == REQUEST_TAKE_PHOTO) {
            setPic();
        } else if (requestCode == REQUEST_PICK_PHOTO) {
            try {
                decodeUri(data.getData());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean shouldAskPermissions() {
        return false;
//        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
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
                Intent intent1 = new Intent(UserProfile.this, MainActivity.class);
                return true;
            case R.id.user:
                Intent intent = new Intent(UserProfile.this, UserProfile.class);
                startActivity(intent);
            case R.id.receive:
                Intent intent2 = new Intent(UserProfile.this, HaulingActivity.class);
                startActivity(intent2);
            default:
                return super.onOptionsItemSelected(item);
        }
    }




}
