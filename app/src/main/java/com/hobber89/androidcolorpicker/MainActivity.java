package com.hobber89.androidcolorpicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.StackView;

public class MainActivity extends AppCompatActivity {

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
}