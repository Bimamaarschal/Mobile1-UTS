package com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Adaptor.RincianPrintAdaptor;
import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Helper.HitungJual;
import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

//Happy Code - Bima Maarschal

public class PrintActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private HitungJual hitungJual;
    TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt, nama, kembalian, totalbayar, pecahan, tanggal;
    String hs_nama, hs_kembalian, hs_pecahan, hs_totalFeeTxt, hs_taxTxt, hs_deliveryTxt, hs_totalTxt, hs_totalbayar;
    private ScrollView scrollView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_print);

        hitungJual = new HitungJual(this);


        nama = findViewById(R.id.nama);

        tanggal = findViewById(R.id.tanggal);

        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        taxTxt = findViewById(R.id.taxTxt);
        totalTxt = findViewById(R.id.totalTxt);
        totalbayar = findViewById(R.id.totalbayar);
        pecahan = findViewById(R.id.pecahan);
        kembalian = findViewById(R.id.kembalian);

        initView();
        initList();
        CalculateCart();
        bottomNavigation();
        getDateToday();


        tanggal.setText("TGl/Trs - " + getDateToday());


        if (getIntent().getStringExtra("nama") != null) {
            hs_nama = getIntent().getStringExtra("nama");
            nama.setText(hs_nama);
        }
        if (getIntent().getStringExtra("kembalian") != null) {
            hs_kembalian = getIntent().getStringExtra("kembalian");
            kembalian.setText(hs_kembalian);
        }
        if (getIntent().getStringExtra("pecahan") != null) {
            hs_pecahan = getIntent().getStringExtra("pecahan");
            pecahan.setText(hs_pecahan);
        }
        if (getIntent().getStringExtra("totalFeeTxt") != null) {
            hs_totalFeeTxt = getIntent().getStringExtra("totalFeeTxt");
            totalFeeTxt.setText(hs_totalFeeTxt);
        }
        if (getIntent().getStringExtra("taxTxt") != null) {
            hs_taxTxt = getIntent().getStringExtra("taxTxt");
            taxTxt.setText(hs_taxTxt);
        }
        if (getIntent().getStringExtra("deliveryTxt") != null) {
            hs_deliveryTxt = getIntent().getStringExtra("deliveryTxt");
            deliveryTxt.setText(hs_deliveryTxt);
        }
        if (getIntent().getStringExtra("totalTxt") != null) {
            hs_totalTxt = getIntent().getStringExtra("totalTxt");
            totalTxt.setText(hs_totalTxt);
        }
        if (getIntent().getStringExtra("totalbayar") != null) {
            hs_totalbayar = getIntent().getStringExtra("totalbayar");
            totalbayar.setText(hs_totalbayar);
        }

    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(view -> startActivity(new Intent(PrintActivity.this, MainActivity.class)));
    }

    private String getDateToday() {
        @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private void initView() {

        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView3);
        recyclerViewList = findViewById(R.id.cartView);

    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new RincianPrintAdaptor(hitungJual.getListCart(), this, this::CalculateCart);

        recyclerViewList.setAdapter(adapter);
        if (hitungJual.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void CalculateCart() {

    }
}