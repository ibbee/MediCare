package com.practice.medicare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class FindDoctorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);

//        back button functionality
        CardView exit = findViewById(R.id.cardFDBack);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FindDoctorActivity.this, HomeActivity.class));
            }
        });

        CardView familyphysician = findViewById(R.id.cardFDFamilyPhysician);
        familyphysician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Family Physicians");
                startActivity(it);
            }
        });
        CardView dietician = findViewById(R.id.cardFDDietician);
        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Dietician");
                startActivity(it);
            }
        });
        CardView dentist = findViewById(R.id.cardFDDentist);
        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Dentist");
                startActivity(it);
            }
        });
        CardView surgeon = findViewById(R.id.cardFDSurgeon);
        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Surgeon");
                startActivity(it);
            }
        });
        CardView cardiologist = findViewById(R.id.cardFDCardiologist);
        cardiologist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(FindDoctorActivity.this, DoctorDetailsActivity.class);
                it.putExtra("title", "Cardiologists");
                startActivity(it);
            }
        });
    }
}