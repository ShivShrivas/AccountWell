package com.bsninfotech.accountwell.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.bsninfotech.accountwell.Helper.CashSummary_Helper;
import com.bsninfotech.accountwell.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CashSummary_Adapter extends ArrayAdapter<CashSummary_Helper> {

    Context context;
    List<CashSummary_Helper> mr_doctor_helpers;
    private LayoutInflater inflater;
    ArrayList<CashSummary_Helper> arraylist;
    int like_click=0;
    Animation animBounce;


    public CashSummary_Adapter(Context context, int resource, List<CashSummary_Helper> mr_doctor_helpers) {
        super(context, resource, mr_doctor_helpers);
        this.context = context;
        this.mr_doctor_helpers=mr_doctor_helpers;
        this.arraylist= new ArrayList<CashSummary_Helper>();
        this.arraylist.addAll(mr_doctor_helpers);
    }

    @Override
    public int getCount() {
        return mr_doctor_helpers.size();
    }
    @Override
    public CashSummary_Helper getItem(int position) {
        return  mr_doctor_helpers.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder {

        TextView text_companyname,text_companyaddress,text_item,text_item_value,text_billdate,text_billdate_value,text_amount,text_amount_value;
        TextView text_qtyinbag,text_qtyinbag_value;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent ) {
        CashSummary_Adapter.ViewHolder holder = null;
        CashSummary_Helper rowItem = getItem(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_cashsummary, null);
            Typeface typeFace = Typeface.createFromAsset(context.getAssets(),
                    "fonts/Nunito-SemiBold.ttf");
            holder = new CashSummary_Adapter.ViewHolder();

            holder.text_companyname =convertView.findViewById(R.id.text_companyname);
            holder.text_companyaddress =convertView.findViewById(R.id.text_companyaddress);
            holder.text_item = convertView.findViewById(R.id.text_item);
            holder.text_item_value =convertView.findViewById(R.id.text_item_value);
            holder.text_billdate =convertView.findViewById(R.id.text_billdate);
            holder.text_billdate_value =convertView.findViewById(R.id.text_billdate_value);
            holder.text_amount =convertView.findViewById(R.id.text_amount);
            holder.text_amount_value =convertView.findViewById(R.id.text_amount_value);
            holder.text_qtyinbag =convertView.findViewById(R.id.text_qtyinbag);
            holder.text_qtyinbag_value =convertView.findViewById(R.id.text_qtyinbag_value);


            holder.text_companyname.setTypeface(typeFace);
            holder.text_companyaddress.setTypeface(typeFace);
            holder.text_item.setTypeface(typeFace);
            holder.text_item_value.setTypeface(typeFace);
            holder.text_billdate.setTypeface(typeFace);
            holder.text_billdate_value.setTypeface(typeFace);
            holder.text_amount_value.setTypeface(typeFace);
            holder.text_qtyinbag.setTypeface(typeFace);
            holder.text_qtyinbag_value.setTypeface(typeFace);
            holder.text_amount.setTypeface(typeFace);

            convertView.setTag(holder);
        } else
        holder = (CashSummary_Adapter.ViewHolder) convertView.getTag();


        holder.text_companyname.setText(rowItem.getCompname());
        holder.text_companyaddress.setText(rowItem.getCompAddress());
        return convertView;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mr_doctor_helpers.clear();
        if (charText.length() == 0) {
            mr_doctor_helpers.addAll(arraylist);
        } else {
            for (CashSummary_Helper wp : arraylist) {
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
