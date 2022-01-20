package com.bsninfotech.accountwell.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

            if(ledger_helpers.get(position).getName().equals(" Opening Balance") || ledger_helpers.get(position).getName().equals("Closing Balance") ){
                holder.openCloseLayout.setVisibility(View.VISIBLE);
                holder.transactionLayout.setVisibility(View.GONE);
                holder.txtOpeningBalance.setText(ledger_helpers.get(position).getName().trim());
                        holder.txtOpeningdateTxt.setText("As on: "+ledger_helpers.get(position).getDate());
                if (ledger_helpers.get(position).getDRCR().equals("Cr.")){
                    holder.transactionAmountOpneCloseledger.setTextColor(Color.RED);
                    holder.transactionAmountOpneCloseledger.setText("₹" + ledger_helpers.get(position).getBalance()+""+ledger_helpers.get(position).getDRCR());

                }else {
                    holder.transactionAmountOpneCloseledger.setTextColor(Color.rgb(8,89,11));
                    holder.transactionAmountOpneCloseledger.setText("₹" + ledger_helpers.get(position).getBalance()+""+ledger_helpers.get(position).getDRCR());

                }
                holder.layourBalance.setVisibility(View.GONE);
                holder.VTypeLayout.setVisibility(View.GONE);
                String fullName = ledger_helpers.get(position).getName();
                String[] name = fullName.split("#");
                holder.textDateView.setVisibility(View.GONE);
                holder.textTransactionName.setVisibility(View.GONE);
                holder.DrCrledger.setVisibility(View.GONE);
                if (i==0){
                    try {
                        holder.textTransactionDesc.setVisibility(View.GONE);
                    }catch (Exception e){
                        holder.textTransactionDesc.setVisibility(View.GONE);
                    }
                }else {
                    holder.textTransactionDesc.setVisibility(View.GONE);
                }
                holder.txtAmmountbalance.setVisibility(View.GONE);

                holder.txtAmmountbalance.setVisibility(View.GONE);
                if (ledger_helpers.get(position).getCreditAmt().equals("-") && !ledger_helpers.get(position).getDebitAmt().equals("-")) {


                    holder.transactionAmount.setVisibility(View.GONE);




                } else if (ledger_helpers.get(position).getDebitAmt().equals("-") && !ledger_helpers.get(position).getCreditAmt().equals("-")) {

                    holder.transactionAmount.setVisibility(View.GONE);



                } else {
                    if (ledger_helpers.get(position).getDRCR().equals("Dr.")){
                        holder.transactionAmount.setVisibility(View.GONE);

                    }else {
                        holder.transactionAmount.setVisibility(View.GONE);

                    }
                  }
            }else {

                String fullName = ledger_helpers.get(position).getName();
                String[] name = fullName.split("#");
                holder.textDateView.setText(ledger_helpers.get(position).getDate());
                holder.txtVNoledger.setText(ledger_helpers.get(position).getCvNo());
                holder.txtVType.setText( ledger_helpers.get(position).getV_Type());
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
                    holder.txtAmmountbalance.setText("₹" + ledger_helpers.get(position).getBalance()+""+ledger_helpers.get(position).getDRCR());

                }else {
                    holder.txtAmmountbalance.setTextColor(Color.rgb(8,89,11));
                    holder.txtAmmountbalance.setText("₹" + ledger_helpers.get(position).getBalance()+""+ledger_helpers.get(position).getDRCR());

                }
                if (ledger_helpers.get(position).getCreditAmt().equals("-") && !ledger_helpers.get(position).getDebitAmt().equals("-")) {
                    holder.transactionAmount.setText("₹" + ledger_helpers.get(position).getDebitAmt());
                    holder.DrCrledger.setText("Dr.");
                    holder.DrCrledger.setTextColor(Color.rgb(8,89,11));

                    holder.transactionAmount.setTextColor(Color.rgb(8,89,11));
                } else if (ledger_helpers.get(position).getDebitAmt().equals("-") && !ledger_helpers.get(position).getCreditAmt().equals("-")) {
                    holder.transactionAmount.setText("₹" + ledger_helpers.get(position).getCreditAmt());
                    holder.DrCrledger.setText("Cr.  ");
                    holder.DrCrledger.setTextColor(Color.RED);
                    holder.transactionAmount.setTextColor(Color.RED);
                } else if(!ledger_helpers.get(position).getDebitAmt().equals("-") && !ledger_helpers.get(position).getCreditAmt().equals("-")){
                    holder.transactionAmount.setText(ledger_helpers.get(position).getDebitAmt()+"Dr."+"\n"+ledger_helpers.get(position).getCreditAmt()+"Cr.");
                    holder.DrCrledger.setVisibility(View.GONE);

                    holder.transactionAmount.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                }
                else {

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
        TextView txtVType,txtVNoledger,DrCrledger,textTransactionName,transactionAmountOpneCloseledger,txtOpeningdateTxt,txtOpeningBalance,textDateView,textTransactionDesc,txtAmmountbalance,transactionAmount,txtDateTranscation;
        LinearLayout layourBalance,VTypeLayout,openCloseLayout,transactionLayout;
        public LedgerViewHolder(@NonNull View itemView) {
            super(itemView);
            textTransactionName=itemView.findViewById(R.id.textTransactionNameledger);
            transactionLayout=itemView.findViewById(R.id.transactionLayout);
            openCloseLayout=itemView.findViewById(R.id.openCloseLayout);
            txtOpeningdateTxt=itemView.findViewById(R.id.txtOpeningdateTxt);
            transactionAmountOpneCloseledger=itemView.findViewById(R.id.transactionAmountOpneCloseledger);
            txtOpeningBalance=itemView.findViewById(R.id.txtOpeningBalance);
            VTypeLayout=itemView.findViewById(R.id.VTypeLayout);
            layourBalance=itemView.findViewById(R.id.layourBalance);
            DrCrledger=itemView.findViewById(R.id.DrCrledger);

            textTransactionDesc=itemView.findViewById(R.id.textTransactionDescledger);
            txtAmmountbalance=itemView.findViewById(R.id.txtAmmountbalanceledger);
            txtVType=itemView.findViewById(R.id.txtVTypeledger);
            txtVNoledger=itemView.findViewById(R.id.txtVNoledger);
            transactionAmount=itemView.findViewById(R.id.transactionAmountledger);
            textDateView=itemView.findViewById(R.id.textDateView);

        }
    }
}
