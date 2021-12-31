package com.bsninfotech.accountwell.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsninfotech.accountwell.Helper.Transactions_Helper;
import com.bsninfotech.accountwell.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class Transaction_Adapter extends RecyclerView.Adapter<Transaction_Adapter.TransactionViewHolder> {
    Context applicationContext;
    int transaction_summary;
    ArrayList<Transactions_Helper> transactions_helpers;
    public Transaction_Adapter(Context applicationContext, int transaction_summary, ArrayList<Transactions_Helper> transactions_helpers) {
        this.transaction_summary=transaction_summary;
        this.applicationContext=applicationContext;
        this.transactions_helpers=transactions_helpers;

    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        holder.textVNo.setText(transactions_helpers.get(position).getPVNo());
        holder.txtVType.setText(transactions_helpers.get(position).getV_Type());
        String currentString = transactions_helpers.get(position).getPVDate();
        String[] separated = currentString.split("T");

        holder.txtDateTranscation.setText(separated[0]);
        holder.txtAmmountTrnsaction.setText(transactions_helpers.get(position).getAmount()+"/-");
        holder.textTransactionName.setText(transactions_helpers.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return transactions_helpers.size();
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_summary,parent,false);
       return new TransactionViewHolder(view);
    }

    public void filterList(ArrayList<Transactions_Helper> filteredList) {
        transactions_helpers=filteredList;
        notifyDataSetChanged();
    }


    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView txtVType,textVNo,textTransactionName,txtAmmountTrnsaction,txtDateTranscation;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAmmountTrnsaction=itemView.findViewById(R.id.txtAmmountTrnsaction);
            txtDateTranscation=itemView.findViewById(R.id.txtDateTranscation);
            txtVType=itemView.findViewById(R.id.txtVType);
            textTransactionName=itemView.findViewById(R.id.textTransactionName);
            textVNo=itemView.findViewById(R.id.textVNo);
        }
    }
}
