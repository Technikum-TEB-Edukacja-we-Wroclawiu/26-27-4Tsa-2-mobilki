package com.example.informator;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    int[] koty = {R.drawable.kot1, R.drawable.kot2, R.drawable.kot3, R.drawable.kot4};
    int numerObrazu = 0;


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

        ImageView obraz = findViewById(R.id.imgKot);
        Button przyciskPrev = findViewById(R.id.btnPrev);
        Button przyciskNext = findViewById(R.id.btnNext);
        EditText poleNumer = findViewById(R.id.etNumer);
        Switch tlo = findViewById(R.id.swTlo);
        LinearLayout glownyUklad = findViewById(R.id.main);

        obraz.setImageResource(koty[numerObrazu]);

        przyciskPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (numerObrazu == 0){
                    numerObrazu = 3;
                }
                else{
                    numerObrazu--;
                }
                obraz.setImageResource(koty[numerObrazu]);
            }
        });
        przyciskNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numerObrazu == 3){
                    numerObrazu = 0;
                }
                else{
                    numerObrazu++;
                }
                obraz.setImageResource(koty[numerObrazu]);
            }
        });
        poleNumer.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String tekst = s.toString();
                int liczba = 0;
                if (!tekst.isEmpty()) {
                    liczba = Integer.parseInt(tekst);
                }
                if (liczba >= 1 && liczba <= 4) {
                    numerObrazu = liczba - 1;
                    obraz.setImageResource(koty[numerObrazu]);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        tlo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    glownyUklad.setBackgroundColor(Color.parseColor("#1565C0"));
                }
                else {
                    glownyUklad.setBackgroundColor(Color.parseColor("#00796B"));
                }
            }
        });

    }
}