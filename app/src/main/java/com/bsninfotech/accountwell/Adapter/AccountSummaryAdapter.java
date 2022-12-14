package com.bsninfotech.accountwell.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bsninfotech.accountwell.AccountSummary;
import com.bsninfotech.accountwell.Bank_Summary;
import com.bsninfotech.accountwell.Helper.Accounts_Helper;
import com.bsninfotech.accountwell.LedgerActivity;
import com.bsninfotech.accountwell.R;

import java.util.ArrayList;
import java.util.List;

public class AccountSummaryAdapter extends RecyclerView.Adapter<AccountSummaryAdapter.AccountViewHolder> {
    Context applicationContext;
    int account_item_card;
    List<Accounts_Helper> accounts_helpers;
    public AccountSummaryAdapter(Context applicationContext, int account_item_card, List<Accounts_Helper> accounts_helpers) {
        this.account_item_card=account_item_card;
        this.applicationContext=applicationContext;
        this.accounts_helpers=accounts_helpers;
    }

    @NonNull
    @Override
    public AccountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item_card,parent,false);
       return new AccountViewHolder(view);
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
    public void onBindViewHolder(@NonNull AccountViewHolder holder, @SuppressLint("RecyclerView") int position) {
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent i=new Intent(applicationContext, LedgerActivity.class);
              i.putExtra("Name",accounts_helpers.get(position).getName());
              i.putExtra("SubCode",accounts_helpers.get(position).getSubCode());
              i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
              applicationContext.startActivity(i);
          }
      });

      if (position%2==0){
          SpannableString content = new SpannableString(accounts_helpers.get(position).getName());
          content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
          holder.accountCardtxt.setText(content);

      }else {
          SpannableString content = new SpannableString(accounts_helpers.get(position).getName());
          content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
          holder.accountCardtxt.setText(content);
            holder.cardLayout.setBackgroundColor(Color.rgb(234, 234, 234));
      }


    }



    @Override
    public int getItemCount() {
        return accounts_helpers.size();
    }

    public void filterList(ArrayList<Accounts_Helper> filteredList) {
        accounts_helpers = filteredList;
        notifyDataSetChanged();
    }

    public class AccountViewHolder extends RecyclerView.ViewHolder {
        TextView accountCardtxt,srNoId;
        ConstraintLayout cardLayout;
        public AccountViewHolder(@NonNull View itemView) {
            super(itemView);
            accountCardtxt=itemView.findViewById(R.id.accountCardtxt);

            cardLayout=itemView.findViewById(R.id.cardLayout);
        }
    }
}
