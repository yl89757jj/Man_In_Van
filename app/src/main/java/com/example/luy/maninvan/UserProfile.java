package com.example.luy.maninvan;

import android.annotation.TargetApi;
import android.app.Activity;
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
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class UserProfile extends AppCompatActivity {
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_PICK_PHOTO = 2;
    private ImageView PhotoImageView;
    private File photoFile;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        if (shouldAskPermissions()) {
            askPermissions();
        }
        PhotoImageView = (ImageView) findViewById(R.id.user_selfie);
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
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
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


}
