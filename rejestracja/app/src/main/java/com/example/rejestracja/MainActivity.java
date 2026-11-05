package com.example.rejestracja;

import android.icu.util.LocaleData;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int[] images = new int[]{R.mipmap.psychiatrist, R.mipmap.neurologist, R.mipmap.proctologist};
    int[] radioButtons = new int[]{R.id.radioPsychiatrist, R.id.radioNeurologist, R.id.radioProctologist};
    int selectedItem = -1;
    Random random = new Random();

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


        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull RadioGroup group, int checkedId) {
                ImageView imageView = findViewById(R.id.imageView);
                for(int i=0; i<radioButtons.length; i++){
                    if(radioButtons[i] == checkedId){
                        imageView.setImageResource(images[i]);
                        selectedItem = i;
                        break;
                    }
                }
            }
        });

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            if(selectedItem != -1){
                TextView textView = findViewById(R.id.result);
                int days = random.nextInt(901) + 100;
                LocalDate date = LocalDate.now();
                date = date.plusDays(days);
                textView.setText(String.format("Termin wizyty: %s", date));
            }
        });
    }
}