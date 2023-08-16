package com.hobber89.androidcolorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.StackView;
import android.widget.TextView;

import com.hobber89.androidcolorpicker.utils.BitmapLoader;

public class PickColorFromImageActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int selectImageRequestId = 1;

    private ImageView imageView;
    private StackView currentColorStackView;
    private Button confirmButton;
    private Bitmap loadedBitmap;
    private short b;
    private short g;
    private short r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_color_from_image);

        imageView = findViewById(R.id.imageView);
        currentColorStackView = findViewById(R.id.currentColorStackView);
        confirmButton = findViewById(R.id.confirmButton);

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN ||
                    motionEvent.getAction() == MotionEvent.ACTION_MOVE){
                    try {

                        float[] values = new float[9];
                        Matrix m = imageView.getImageMatrix();
                        m.getValues(values);

                        int offsetX = (int) values[Matrix.MTRANS_X];
                        int offsetY = (int) values[Matrix.MTRANS_Y];

                        int deltaViewWidth = offsetX + offsetX;
                        int deltaViewHeight = offsetY + offsetY;

                        float scaleX = (float)(imageView.getWidth() - deltaViewWidth) / loadedBitmap.getWidth();
                        float scaleY = (float)(imageView.getHeight() - deltaViewHeight) / loadedBitmap.getHeight();

                        int x = (int) motionEvent.getX() - offsetX;
                        int y = (int) motionEvent.getY() - offsetY;

                        int scaledX = (int)(x / scaleX);
                        int scaledY = (int)(y / scaleY);
                        if(scaledX >= 0 && scaledX < loadedBitmap.getWidth() && scaledY >= 0 && scaledY < loadedBitmap.getHeight()) {
                            int color = loadedBitmap.getPixel(scaledX, scaledY);
                            b = (short)(color & 0xff);
                            g = (short)((color >> 8) & 0xff);
                            r = (short)((color >> 16) & 0xff);

                            currentColorStackView.setBackgroundColor(color);

                            confirmButton.setEnabled(true);
                        } else {

                        }
                    } catch(Exception error) {
                        System.out.println(error.getMessage());
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.selectImageButton) {
            Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickImage, selectImageRequestId);
        }
        else if(view.getId() == R.id.confirmButton) {
            MainActivity.setColor(r, g, b);
            finish(); //TODO: return color as parcable instead
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