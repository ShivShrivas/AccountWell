package com.bsninfotech.accountwell.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bsninfotech.accountwell.Helper.Ledger_Helper;
import com.bsninfotech.accountwell.Helper.Stock_Helper;
import com.bsninfotech.accountwell.LedgerActivity;
import com.bsninfotech.accountwell.R;

import java.util.ArrayList;
import java.util.List;

public class LedgerAdapter extends RecyclerView.Adapter<LedgerAdapter.LedgerViewHolder> {
    Context applicationContext;
    int ledger_item_card;
    List<Ledger_Helper> ledger_helpers;
    int i;
    public LedgerAdapter(Context applicationContext, int ledger_item_card, List<Ledger_Helper> ledger_helpers, int i) {
        this.applicationContext=applicationContext;
        this.ledger_item_card=ledger_item_card;
        this.ledger_helpers=ledger_helpers;
        this.i=i;
    }
    public void filterList(ArrayList<Ledger_Helper> filteredList) {
        ledger_helpers = filteredList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public LedgerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ledger_item_card,parent,false);
        return new LedgerViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull LedgerViewHolder holder, int position) {
            if(position==0 || position==ledger_helpers.size()-1){

                holder.itemView.setBackgroundColor(Color.rgb(234, 234, 234));
                String fullName = ledger_helpers.get(position).getName();
                String[] name = fullName.split("#");
                holder.txtVType.setText(ledger_helpers.get(position).getDate());
                holder.textTransactionName.setText(name[0].trim());
                holder.textTransactionName.setTextSize(19);
                if (i==0){
                    try {
                        holder.textTransactionDesc.setText(name[1]);
                    }catch (Exception e){
                        holder.textTransactionDesc.setVisibility(View.GONE);
                    }
                }else {
                    holder.textTransactionDesc.setVisibility(View.GONE);
                }
                holder.txtAmmountbalance.setVisibility(View.GONE);

                holder.txtAmmountbalance.setText("Balance: ₹" + ledger_helpers.get(position).getBalance()+""+ledger_helpers.get(position).getDRCR());
                if (ledger_helpers.get(position).getCreditAmt().equals("-") && !ledger_helpers.get(position).getDebitAmt().equals("-")) {
                    holder.transactionAmount.setText("₹" + ledger_helpers.get(position).getDebitAmt());
                    holder.creditOrDebitTxt.setText("Dr.");
                    holder.transactionAmount.setTextSize(19);
                    holder.creditOrDebitTxt.setTextSize(19);

                    holder.creditOrDebitTxt.setTextColor(Color.rgb(8,89,11));
                    holder.transactionAmount.setTextColor(Color.rgb(8,89,11));
                } else if (ledger_helpers.get(position).getDebitAmt().equals("-") && !ledger_helpers.get(position).getCreditAmt().equals("-")) {
                    holder.transactionAmount.setText("₹" + ledger_helpers.get(position).getCreditAmt());
                    holder.creditOrDebitTxt.setText("Cr.");
                    holder.transactionAmount.setTextSize(19);
                    holder.creditOrDebitTxt.setTextSize(19);
                    holder.creditOrDebitTxt.setTextColor(Color.RED);
                    holder.transactionAmount.setTextColor(Color.RED);
                } else {
                    holder.transactionAmount.setText("₹" + ledger_helpers.get(position).getBalance());
                    holder.creditOrDebitTxt.setText(ledger_helpers.get(position).getDRCR());
                    holder.transactionAmount.setTextSize(19);
                    holder.creditOrDebitTxt.setTextSize(19);
                }
            }else {

                String fullName = ledger_helpers.get(position).getName();
                String[] name = fullName.split("#");
                holder.txtVType.setText(ledger_helpers.get(position).getDate() + " || " + ledger_helpers.get(position).getV_Type() +" || "+ledger_helpers.get(position).getCvNo());
                holder.textTransactionName.setText(name[0]);
                if (i==0){
                    try {
                        holder.textTransactionDesc.setText(name[1]);
                    }catch (Exception e){
                        holder.textTransactionDesc.setVisibility(View.GONE);
                    }
                }else {
                    holder.textTransactionDesc.setVisibility(View.GONE);
                }
                if (ledger_helpers.get(position).getDRCR().equals("Cr.")){
                    holder.txtAmmountbalance.setTextColor(Color.RED);
                    holder.txtAmmountbalance.setText("Balance: ₹" + ledger_helpers.get(position).getBalance()+""+ledger_helpers.get(position).getDRCR());

                }else {
                    holder.txtAmmountbalance.setTextColor(Color.rgb(8,89,11));
                    holder.txtAmmountbalance.setText("Balance: ₹" + ledger_helpers.get(position).getBalance()+""+ledger_helpers.get(position).getDRCR());

                }
                if (ledger_helpers.get(position).getCreditAmt().equals("-") && !ledger_helpers.get(position).getDebitAmt().equals("-")) {
                    holder.transactionAmount.setText("₹" + ledger_helpers.get(position).getDebitAmt());
                    holder.creditOrDebitTxt.setText("Dr.");
                    holder.creditOrDebitTxt.setTextColor(Color.rgb(8,89,11));
                    holder.transactionAmount.setTextColor(Color.rgb(8,89,11));
                } else if (ledger_helpers.get(position).getDebitAmt().equals("-") && !ledger_helpers.get(position).getCreditAmt().equals("-")) {
                    holder.transactionAmount.setText("₹" + ledger_helpers.get(position).getCreditAmt());
                    holder.creditOrDebitTxt.setText("Cr.");
                    holder.creditOrDebitTxt.setTextColor(Color.RED);
                    holder.transactionAmount.setTextColor(Color.RED);
                } else if(!ledger_helpers.get(position).getDebitAmt().equals("-") && !ledger_helpers.get(position).getCreditAmt().equals("-")){
                    holder.transactionAmount.setText(ledger_helpers.get(position).getDebitAmt()+"Dr."+"\n"+ledger_helpers.get(position).getCreditAmt()+"Cr.");
                    holder.creditOrDebitTxt.setVisibility(View.GONE);
                    holder.transactionAmount.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                }
                else {
                    holder.creditOrDebitTxt.setVisibility(View.GONE);
                    holder.transactionAmount.setText("Nill");
                }

            }



        LedgerActivity.mProgressDialog.dismiss();
    }

    @Override
    public int getItemCount() {
        return ledger_helpers.size();
    }

    public class LedgerViewHolder extends RecyclerView.ViewHolder {
        TextView txtVType,textVNo,textTransactionName,textTransactionDesc,txtAmmountbalance,transactionAmount,creditOrDebitTxt,txtDateTranscation;
     ;
        public LedgerViewHolder(@NonNull View itemView) {
            super(itemView);
            textTransactionName=itemView.findViewById(R.id.textTransactionNameledger);
            textTransactionDesc=itemView.findViewById(R.id.textTransactionDescledger);
            txtAmmountbalance=itemView.findViewById(R.id.txtAmmountbalanceledger);
            txtVType=itemView.findViewById(R.id.txtVTypeledger);
            transactionAmount=itemView.findViewById(R.id.transactionAmountledger);
            creditOrDebitTxt=itemView.findViewById(R.id.creditOrDebitTxtledger);
        }
    }
}
