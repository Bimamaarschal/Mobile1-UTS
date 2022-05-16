package com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Adaptor.RincianHargaAdaptor;
import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Helper.HitungJual;
import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

//Happy Code - Bima Maarschal

public class RincianHargaActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private HitungJual hitungJual;
    TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt, nama, kembalian, totalbayar, pecahan;
    EditText cash;
    String hs_nama, hs_cash, hs_cash2, hs_kembalian, hs_pecahan, hs_totalFeeTxt, hs_taxTxt, hs_deliveryTxt, hs_totalTxt, hs_totalbayar;
    private ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_list);

        hitungJual = new HitungJual(this);
        nama = findViewById(R.id.nama);
        cash = findViewById(R.id.cash);

        initView();
        initList();
        CalculateCart();
        bayar();
        cetak();
        bottomNavigation();

        if (getIntent().getStringExtra("nama") != null) {
            hs_nama = getIntent().getStringExtra("nama");
            nama.setText(hs_nama);
        }

        if (getIntent().getStringExtra("cash") != null) {
            hs_cash = getIntent().getStringExtra("cash");
            cash.setText(hs_cash);
        }


    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.homeBtn);

        floatingActionButton.setOnClickListener(view -> startActivity(new Intent(RincianHargaActivity.this, MainActivity.class)));
    }

    private void initView() {

        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        totalbayar = findViewById(R.id.totalbayar);
        pecahan = findViewById(R.id.pecahan);
        kembalian = findViewById(R.id.kembalian);
        emptyTxt = findViewById(R.id.emptyTxt);
        cash = findViewById(R.id.cash);
        scrollView = findViewById(R.id.scrollView3);
        recyclerViewList = findViewById(R.id.cartView);

    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new RincianHargaAdaptor(hitungJual.getListCart(), this, this::CalculateCart);

        recyclerViewList.setAdapter(adapter);
        if (hitungJual.getListCart().isEmpty()) {
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        } else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void bayar() {

        cash = findViewById(R.id.cash);
        nama = findViewById(R.id.nama);

        TextView TextView = findViewById(R.id.bayar);


        TextView.setOnClickListener(view -> {

            hs_cash = cash.getText().toString();
            hs_nama = nama.getText().toString();

            Intent i = new Intent(RincianHargaActivity.this, RincianHargaActivity.class);
            if (hs_cash.equals("")) {
                Toast.makeText(RincianHargaActivity.this, "Pecahan Uang Tidak Boleh Kosong !", Toast.LENGTH_LONG).show();
            } else {
                i.putExtra("cash", hs_cash);
                i.putExtra("nama", hs_nama);
                startActivity(i);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void CalculateCart() {

        double percentTax = 0.010;
        double delivery = 2000;
        double tax = Math.round((hitungJual.getTotalFee() * percentTax) * 100) / 100;
        double itemTotal = Math.round(hitungJual.getTotalFee() * 100) / 100;
        double total = Math.round((hitungJual.getTotalFee() + tax + delivery) * 100) / 100;


        if (getIntent().getStringExtra("cash") != null) {
            hs_cash2 = getIntent().getStringExtra("cash");

            double dbl_cash = Double.parseDouble(hs_cash2);

            double kembali = (dbl_cash - total);

            kembalian.setText("Rp." + kembali);
            pecahan.setText("Rp." + dbl_cash);


        }

        totalFeeTxt.setText("Rp." + itemTotal);
        taxTxt.setText("Rp." + tax);
        deliveryTxt.setText("Rp." + delivery);
        totalTxt.setText("Rp." + total);
        totalbayar.setText("Rp." + total);

    }

    private void cetak() {

        cash = findViewById(R.id.cash);
        nama = findViewById(R.id.nama);
        kembalian = findViewById(R.id.kembalian);
        pecahan = findViewById(R.id.pecahan);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        totalbayar = findViewById(R.id.totalbayar);

        TextView TextView1 = findViewById(R.id.cetak);

        TextView1.setOnClickListener(view -> {

            hs_cash = cash.getText().toString();
            hs_nama = nama.getText().toString();
            hs_kembalian = kembalian.getText().toString();
            hs_pecahan = pecahan.getText().toString();
            hs_totalFeeTxt = totalFeeTxt.getText().toString();
            hs_taxTxt = taxTxt.getText().toString();
            hs_deliveryTxt = deliveryTxt.getText().toString();
            hs_totalTxt = totalTxt.getText().toString();
            hs_totalbayar = totalbayar.getText().toString();

            Intent i = new Intent(RincianHargaActivity.this, PrintActivity.class);
            if (hs_cash.equals("")) {
                Toast.makeText(RincianHargaActivity.this, "Pecahan Uang Tidak Boleh Kosong !", Toast.LENGTH_LONG).show();
            } else {
                i.putExtra("cash", hs_cash);
                i.putExtra("nama", hs_nama);
                i.putExtra("kembalian", hs_kembalian);
                i.putExtra("pecahan", hs_pecahan);
                i.putExtra("totalFeeTxt", hs_totalFeeTxt);
                i.putExtra("taxTxt", hs_taxTxt);
                i.putExtra("deliveryTxt", hs_deliveryTxt);
                i.putExtra("totalTxt", hs_totalTxt);
                i.putExtra("totalbayar", hs_totalbayar);
                startActivity(i);
            }
        });

    }


}