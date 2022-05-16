package com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Domain.DomainJual;
import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Helper.HitungJual;
import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.Interface.ChangeNumberItemsListener;
import com.example.ujiantengahsemester_bimamaarschal_20552011107_2022.R;

import java.util.ArrayList;

public class RincianPrintAdaptor extends RecyclerView.Adapter<RincianPrintAdaptor.ViewHolder> {
    private ArrayList<DomainJual> domainJuals;
    private HitungJual hitungJual;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public RincianPrintAdaptor(ArrayList<DomainJual> domainJuals, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.domainJuals = domainJuals;
        this.hitungJual = new HitungJual(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_rincian_print, parent, false);

        return new ViewHolder(inflate);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(domainJuals.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(domainJuals.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((domainJuals.get(position).getNumberInCart() * domainJuals.get(position).getFee()) * 100) / 100));
        holder.num.setText(String.valueOf(domainJuals.get(position).getNumberInCart()));
    }

    @Override
    public int getItemCount() {
        return domainJuals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem;
        TextView totalEachItem, num;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            num = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
