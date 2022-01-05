package com.bsninfotech.accountwell.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsninfotech.accountwell.Bank_Summary;
import com.bsninfotech.accountwell.Helper.CashsummaryHelper;
import com.bsninfotech.accountwell.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CashSummary_Adapter_New extends RecyclerView.Adapter<CashSummary_Adapter_New.CashSummaryViewHolder> {
    Context applicationContext;
    int item_vash_summary_new;
    ArrayList<CashsummaryHelper> cashsummaryHelpers;

    public CashSummary_Adapter_New(Context applicationContext, int item_vash_summary_new, ArrayList<CashsummaryHelper> cashsummaryHelpers) {
        this.applicationContext=applicationContext;
        this.item_vash_summary_new=item_vash_summary_new;
        this.cashsummaryHelpers=cashsummaryHelpers;
    }

    @NonNull
    @Override
    public CashSummary_Adapter_New.CashSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View bankListLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vash_summary_new, null);
        CashSummaryViewHolder bankListViewHolder = new CashSummaryViewHolder(bankListLayout);
        return bankListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CashSummary_Adapter_New.CashSummaryViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(applicationContext,Bank_Summary.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                applicationContext.startActivity(i);
            }
        });
    holder.textViewNation.setText(cashsummaryHelpers.get(position).getNotation());
    holder.textViewName.setText(cashsummaryHelpers.get(position).getName());
        Log.d("TAG", "onBindViewHolder: "+cashsummaryHelpers.get(position).getBalance());
        Log.d("TAG", "onBindViewHolder: "+Double.parseDouble(cashsummaryHelpers.get(position).getBalance().toString()));
        String balance=new DecimalFormat("#.00").format(cashsummaryHelpers.get(position).getBalance());

    holder.textViewBalance.setText(balance);
    }

    @Override
    public int getItemCount() {
        return cashsummaryHelpers.size();
    }

    public void filterList(ArrayList<CashsummaryHelper> filteredList) {
        cashsummaryHelpers = filteredList;
        notifyDataSetChanged();
    }

    public class CashSummaryViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName,textViewBalance,textViewNation;
        public CashSummaryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName=itemView.findViewById(R.id.textviewNameInCashSummaryItemCard);
            textViewBalance=itemView.findViewById(R.id.textBalance);
            textViewNation=itemView.findViewById(R.id.txtNation);

        }
    }
}
