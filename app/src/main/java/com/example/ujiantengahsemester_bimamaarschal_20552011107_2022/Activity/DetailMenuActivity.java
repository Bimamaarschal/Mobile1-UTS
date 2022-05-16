package com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Domain.DomainJual;
import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Helper.HitungJual;
import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

//Happy Code - Bima Maarschal

public class DetailMenuActivity extends AppCompatActivity {
    private TextView addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, numberOrderTxt;
    private ImageView plusBtn, minusBtn, picFood;
    private DomainJual object;
    int numberOrder = 1;
    private HitungJual hitungJual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        hitungJual = new HitungJual(this);
        initView();
        getBundle();
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(view -> startActivity(new Intent(DetailMenuActivity.this, MainActivity.class)));

    }

    @SuppressLint("SetTextI18n")
    private void getBundle() {
        object = (DomainJual) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("Rp. " + object.getFee());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));

        plusBtn.setOnClickListener(view -> {
            numberOrder = numberOrder + 1;
            numberOrderTxt.setText(String.valueOf(numberOrder));
        });

        minusBtn.setOnClickListener(view -> {
            if (numberOrder > 1) {
                numberOrder = numberOrder - 1;
            }
            numberOrderTxt.setText(String.valueOf(numberOrder));
        });

        addToCartBtn.setOnClickListener(view -> {
            object.setNumberInCart(numberOrder);
            hitungJual.insertFood(object);
        });
    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.picfood);
    }
}