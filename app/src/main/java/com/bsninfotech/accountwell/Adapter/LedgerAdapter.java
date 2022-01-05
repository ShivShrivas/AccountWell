package com.bsninfotech.accountwell.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bsninfotech.accountwell.Helper.Ledger_Helper;
import com.bsninfotech.accountwell.LedgerActivity;
import com.bsninfotech.accountwell.R;

import java.util.List;

public class LedgerAdapter extends RecyclerView.Adapter<LedgerAdapter.LedgerViewHolder> {
    Context applicationContext;
    int ledger_item_card;
    List<Ledger_Helper> ledger_helpers;
    public LedgerAdapter(Context applicationContext, int ledger_item_card, List<Ledger_Helper> ledger_helpers) {
        this.applicationContext=applicationContext;
        this.ledger_item_card=ledger_item_card;
        this.ledger_helpers=ledger_helpers;
    }

    @NonNull
    @Override
    public LedgerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ledger_item_card,parent,false);
        return new LedgerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LedgerViewHolder holder, int position) {



            String fullName = ledger_helpers.get(position).getName();
            String[] name = fullName.split("#");

            holder.txtVType.setText(ledger_helpers.get(position).getV_Type());
            holder.textVNo.setText(ledger_helpers.get(position).getCvNo());
            holder.textTransactionName.setText(name[0]);
            holder.txtDateTranscation.setText(ledger_helpers.get(position).getDate());
            holder.txtAmmountbalance.setText("₹" + ledger_helpers.get(position).getBalance());
            if (ledger_helpers.get(position).getCreditAmt().equals("-") && !ledger_helpers.get(position).getDebitAmt().equals("-")) {

                holder.transactionAmount.setText("₹" + ledger_helpers.get(position).getDebitAmt());
                holder.creditOrDebitTxt.setText("Debit");
            } else if (ledger_helpers.get(position).getDebitAmt().equals("-") && !ledger_helpers.get(position).getCreditAmt().equals("-")) {
                holder.transactionAmount.setText("₹" + ledger_helpers.get(position).getCreditAmt());
                holder.creditOrDebitTxt.setText("Credit");

            } else {
                holder.transactionAmount.setText("No ammount");
                holder.creditOrDebitTxt.setText("Nun");
            }


    }

    @Override
    public int getItemCount() {
        return ledger_helpers.size();
    }

    public class LedgerViewHolder extends RecyclerView.ViewHolder {
        TextView txtVType,textVNo,textTransactionName,txtAmmountbalance,transactionAmount,creditOrDebitTxt,txtDateTranscation;
        ConstraintLayout cardlayoutledger;
        public LedgerViewHolder(@NonNull View itemView) {
            super(itemView);
            textTransactionName=itemView.findViewById(R.id.textTransactionName);
            txtDateTranscation=itemView.findViewById(R.id.txtDateTranscation);
            cardlayoutledger=itemView.findViewById(R.id.cardlayoutledger);
            txtAmmountbalance=itemView.findViewById(R.id.txtAmmountbalance);
            textVNo=itemView.findViewById(R.id.textVNo);
            txtVType=itemView.findViewById(R.id.txtVType);
            transactionAmount=itemView.findViewById(R.id.transactionAmount);
            creditOrDebitTxt=itemView.findViewById(R.id.creditOrDebitTxt);
        }
    }
}
