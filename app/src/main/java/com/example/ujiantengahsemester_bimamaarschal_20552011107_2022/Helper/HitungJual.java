package com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Domain.DomainJual;
import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

//Happy Code - Bima Maarschal

public class HitungJual {
    private final Context context;
    private final TinyDB tinyDB;

    public HitungJual(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(DomainJual item) {
        ArrayList<DomainJual> listFood = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++) {
            if (listFood.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listFood.add(item);
        }
        tinyDB.putListObject("CartList", listFood);
        Toast.makeText(context, "Menu Di tambahkan", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<DomainJual> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberFood(ArrayList<DomainJual> listFood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemsListener.changed();
    }

    public void minusNumberFood(ArrayList<DomainJual> listfood, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listfood.get(position).getNumberInCart() == 1) {
            listfood.remove(position);
        } else {
            listfood.get(position).setNumberInCart(listfood.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CartList", listfood);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee() {
        ArrayList<DomainJual> listfood = getListCart();
        double fee = 0;
        for (int i = 0; i < listfood.size(); i++) {
            fee = fee + (listfood.get(i).getFee() * listfood.get(i).getNumberInCart());
        }
        return fee;
    }

}
