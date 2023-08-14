package com.hobber89.androidcolorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.hobber89.androidcolorpicker.utils.BitmapLoader;

public class PickColorFromImageActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int selectImageRequestId = 1;

    private ImageView imageView;
    private Bitmap loadedBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_color_from_image);

        imageView = findViewById(R.id.imageView);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.selectImageButton) {
            Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickImage, selectImageRequestId);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == selectImageRequestId && resultCode == RESULT_OK && !data.equals(null)) {
            Uri imageUri = data.getData();
            loadedBitmap = BitmapLoader.load(this, imageUri);
            imageView.setImageBitmap(loadedBitmap);
        }
    }
}