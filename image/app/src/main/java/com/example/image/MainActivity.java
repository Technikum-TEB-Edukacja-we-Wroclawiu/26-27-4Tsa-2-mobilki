package com.example.image;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.icu.util.LocaleData;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageView[] imageViews = new ImageView[3];
    int selectedItem = -1;
    LocalDate arrival;

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

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            EditText editText = findViewById(R.id.numberOfDays);
            int days = Integer.parseInt(editText.getText().toString());
            LocalDate departureDate = arrival.plusDays(days);

            String text = String.format( "Wybrano pokój numer: %d, data przyjazdu %s, data wyjazdu %s",
                    selectedItem+1, arrival.toString(), departureDate.toString());

            Toast.makeText(getBaseContext(), text, Toast.LENGTH_SHORT).show();

        });

        CalendarView calendarView = findViewById(R.id.arrivalDate);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                arrival = LocalDate.of(year, month + 1, dayOfMonth);
            }
        });



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