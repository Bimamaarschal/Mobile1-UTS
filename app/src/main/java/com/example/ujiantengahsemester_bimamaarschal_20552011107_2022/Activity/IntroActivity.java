package com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.R;

//Happy Code - Bima Maarschal

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ConstraintLayout btn_mulai = findViewById(R.id.btn_mulai);
        btn_mulai.setOnClickListener(view -> startActivity(new Intent(IntroActivity.this, MainActivity.class)));
    }
}