package com.hobber89.androidcolorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.StackView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int pickColorFromAnImageRequestId = 1;

    private ColorChannelValues currentColor;
    private StackView currentColorStackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentColor = new ColorChannelValues();

        currentColorStackView = findViewById(R.id.currentColorStackView);
        currentColorStackView.setBackgroundColor(currentColor.getColor());
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.pickColorFromAnImageButton) {
            Intent intent = new Intent(this, PickColorFromImageActivity.class);
            startActivityForResult(intent, pickColorFromAnImageRequestId);
        }
    }
}