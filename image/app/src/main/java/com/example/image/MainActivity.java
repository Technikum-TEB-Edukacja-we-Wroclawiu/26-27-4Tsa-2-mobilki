package com.example.image;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    ImageView[] imageViews = new ImageView[3];
    int selectedItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageViews[0] = findViewById(R.id.image1);
        imageViews[1] = findViewById(R.id.image2);
        imageViews[2] = findViewById(R.id.image3);

        imageViews[0].setOnClickListener(v -> onImageClick(0));
        imageViews[1].setOnClickListener(v -> onImageClick(1));
        imageViews[2].setOnClickListener(v -> onImageClick(2));



    }

    private void onImageClick(int index){
        ImageView imageView = imageViews[index];
        GradientDrawable border = new GradientDrawable();
        border.setShape(GradientDrawable.RECTANGLE);
        border.setStroke(5, Color.BLACK);
        imageView.setBackground(border);

        if(selectedItem >= 0) imageViews[selectedItem].setBackground(null);
        selectedItem = index;
    }
}