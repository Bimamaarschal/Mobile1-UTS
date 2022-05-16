package com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Adaptor.MenuJualAdaptor;
import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Domain.DomainJual;
import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

//Happy Code - Bima Maarschal

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter2;
    EditText nama;
    String snama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {

        nama = findViewById(R.id.nama);
        FloatingActionButton floatingActionButton = findViewById(R.id.cartBtn);
        floatingActionButton.setOnClickListener(view -> {
            snama = nama.getText().toString();
            Intent i = new Intent(MainActivity.this, RincianHargaActivity.class);
            if (snama.equals("")) {
                Toast.makeText(MainActivity.this, "Nama tidak boleh kosong !", Toast.LENGTH_LONG).show();
            } else {
                i.putExtra("nama", snama);
                startActivity(i);
            }

        });
    }


    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<DomainJual> foodList = new ArrayList<>();
        foodList.add(new DomainJual("HGarl Chicken", "img3", "Nasi, Ayam dengan tamburan saus madu", 35000.0));
        foodList.add(new DomainJual("Beef Regular", "img5", "Burger sapi, Ukuran besar", 30000.0));
        foodList.add(new DomainJual("IceCream Cone", "img6", "Ice Cream Cone", 10000.0));
        foodList.add(new DomainJual("Flurry Oreo", "img7", "Ice dengan campuran Oreo", 18000.0));
        foodList.add(new DomainJual("Regular Fries", "img2", "Kentang goreng, Ukuran sedand", 25000.0));
        foodList.add(new DomainJual("Fanta Float", "img1", "Minuman Ice dengan canpuran Fanta", 15000.0));

        adapter2 = new MenuJualAdaptor(foodList);
        recyclerViewPopularList.setAdapter(adapter2);
    }

}