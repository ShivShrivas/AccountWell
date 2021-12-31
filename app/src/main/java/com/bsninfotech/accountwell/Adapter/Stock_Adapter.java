package com.bsninfotech.accountwell.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsninfotech.accountwell.Helper.CashsummaryHelper;
import com.bsninfotech.accountwell.Helper.Stock_Helper;
import com.bsninfotech.accountwell.R;

import java.util.ArrayList;

public class Stock_Adapter extends RecyclerView.Adapter<Stock_Adapter.StockViewHolder> {
    Context applicationContext;
    int stock_summary_item;
    ArrayList<Stock_Helper> stock_helpers;
    public Stock_Adapter(Context applicationContext, int stock_summary_item, ArrayList<Stock_Helper> stock_helpers) {
        this.applicationContext=applicationContext;
        this.stock_summary_item=stock_summary_item;
        this.stock_helpers=stock_helpers;
    }

    @NonNull
    @Override
    public StockViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_summary_item,parent,false);
        return new StockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StockViewHolder holder, int position) {
    holder.totalBag.setText(stock_helpers.get(position).getTotalBag());
    holder.QtyPerBag.setText(stock_helpers.get(position).getQtyPerBag());
    holder.Noofbag.setText(stock_helpers.get(position).getNoOfBag());
    holder.txtNameStock.setText(stock_helpers.get(position).getName());
    }
    public void filterList(ArrayList<Stock_Helper> filteredList) {
        stock_helpers = filteredList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return stock_helpers.size();
    }

    public class StockViewHolder extends RecyclerView.ViewHolder {
        TextView  txtNameStock,QtyPerBag,Noofbag,totalBag;
        public StockViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameStock=itemView.findViewById(R.id.txtNameStock);
            QtyPerBag=itemView.findViewById(R.id.QtyPerBag);
            Noofbag=itemView.findViewById(R.id.Noofbag);
            totalBag=itemView.findViewById(R.id.totalBag);
        }
    }
}
