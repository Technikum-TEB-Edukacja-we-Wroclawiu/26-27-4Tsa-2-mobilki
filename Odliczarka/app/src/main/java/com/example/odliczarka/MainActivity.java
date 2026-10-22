package com.example.odliczarka;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class MainActivity extends AppCompatActivity {

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

        LocalDate today = LocalDate.now();
        LocalDate holidaysStart;
        if(today.isAfter(LocalDate.of(today.getYear(), 8, 31))){
            holidaysStart = getLastFridayOfJune(today.getYear() + 1);
        }else{
            holidaysStart = getLastFridayOfJune(today.getYear());
        }

        long days = ChronoUnit.DAYS.between(today, holidaysStart);
        TextView textView = findViewById(R.id.days);
        textView.setText(String.format("%d dni", days));

       // LocalDate localDate = LocalDate.parse("20264-10-08", DateTimeFormatter.ISO_DATE);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    }

    private LocalDate getLastFridayOfJune(int year){
        LocalDate localDate = LocalDate.of(year, 6, 30);
        while(localDate.getDayOfWeek() != DayOfWeek.FRIDAY){
            localDate = localDate.minusDays(1);
        }

        return localDate;
    }
}