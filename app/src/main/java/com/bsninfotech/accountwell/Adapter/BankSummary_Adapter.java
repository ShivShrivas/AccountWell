package com.bsninfotech.accountwell.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.bsninfotech.accountwell.Helper.BankSummary_Helper;
import com.bsninfotech.accountwell.Helper.CashSummary_Helper;
import com.bsninfotech.accountwell.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BankSummary_Adapter extends ArrayAdapter<BankSummary_Helper> {

    Context context;
    List<BankSummary_Helper> mr_doctor_helpers;
    private LayoutInflater inflater;
    ArrayList<BankSummary_Helper> arraylist;
    int like_click=0;
    Animation animBounce;




    public BankSummary_Adapter(Context context, int resource, List<BankSummary_Helper> mr_doctor_helpers) {
        super(context, resource, mr_doctor_helpers);
        this.context = context;
        this.mr_doctor_helpers=mr_doctor_helpers;
        this.arraylist= new ArrayList<BankSummary_Helper>();
        this.arraylist.addAll(mr_doctor_helpers);
    }


    @Override
    public int getCount() {
        return mr_doctor_helpers.size();
    }
    @Override
    public BankSummary_Helper getItem(int position) {
        return  mr_doctor_helpers.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder {

        TextView text_companyname,text_companyaddress,text_date,text_amount;
        TextView text_qtyinbag,text_qtyinbag_value;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent ) {
        BankSummary_Adapter.ViewHolder holder = null;
        BankSummary_Helper rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_banksummary, null);
            Typeface typeFace = Typeface.createFromAsset(context.getAssets(),
                    "fonts/Nunito-SemiBold.ttf");
            holder = new BankSummary_Adapter.ViewHolder();
            holder.text_companyname =convertView.findViewById(R.id.text_companyname);
            holder.text_companyaddress =convertView.findViewById(R.id.text_companyaddress);
            holder.text_date = convertView.findViewById(R.id.text_date);
            holder.text_amount =convertView.findViewById(R.id.text_amount);




            holder.text_companyname.setTypeface(typeFace);
            holder.text_companyaddress.setTypeface(typeFace);
            holder.text_amount.setTypeface(typeFace);
            holder.text_date.setTypeface(typeFace);


            convertView.setTag(holder);
        } else
        holder = (BankSummary_Adapter.ViewHolder) convertView.getTag();


        holder.text_companyname.setText(rowItem.getCompname());
        holder.text_companyaddress.setText(rowItem.getCompAddress());
        if(rowItem.getPurpose().equalsIgnoreCase("Cr.")){
            holder.text_amount.setTextColor(Color.parseColor("#07770C"));
            holder.text_amount.setText("₹ 50,000 "+rowItem.getPurpose());
        }else{
            holder.text_amount.setTextColor(Color.parseColor("#C00303"));
            holder.text_amount.setText("₹ 50,000 "+rowItem.getPurpose());
        }


        return convertView;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mr_doctor_helpers.clear();
        if (charText.length() == 0) {
            mr_doctor_helpers.addAll(arraylist);
        } else {
            for (BankSummary_Helper wp : arraylist) {
                if(charText.equalsIgnoreCase("0")){

                }else{
                    if (wp.getCompname().toLowerCase(Locale.getDefault() )
                            .contains(charText) || wp.getDoctorMobile().toLowerCase(Locale.getDefault()).contains(charText)) {
                        mr_doctor_helpers.add(wp);
                    }
                }

            }
        }
        notifyDataSetChanged();
    }

}
