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
import android.widget.ImageView;
import android.widget.TextView;

import com.bsninfotech.accountwell.Helper.Labour_Helper;
import com.bsninfotech.accountwell.R;

import java.util.ArrayList;
import java.util.List;

public class Labour_Adapter extends ArrayAdapter<Labour_Helper> {

    Context context;
    List<Labour_Helper> mr_doctor_helpers;
    private LayoutInflater inflater;
    ArrayList<Labour_Helper> arraylist;
    int like_click=0;
    Animation animBounce;




    public Labour_Adapter(Context context, int resource, List<Labour_Helper> mr_doctor_helpers) {
        super(context, resource, mr_doctor_helpers);
        this.context = context;
        this.mr_doctor_helpers=mr_doctor_helpers;
        this.arraylist= new ArrayList<Labour_Helper>();
        this.arraylist.addAll(mr_doctor_helpers);
    }


    @Override
    public int getCount() {
        return mr_doctor_helpers.size();
    }
    @Override
    public Labour_Helper getItem(int position) {
        return  mr_doctor_helpers.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder {
        TextView text_amount,text_number,text_date;
        TextView text_qtyinbag,text_qtyinbag_value;
        ImageView image_down;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent ) {
        Labour_Adapter.ViewHolder holder = null;
        Labour_Helper rowItem = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_labourlist, null);
            Typeface typeFace = Typeface.createFromAsset(context.getAssets(),
                    "fonts/Nunito-SemiBold.ttf");
            holder = new Labour_Adapter.ViewHolder();
            holder.text_amount =convertView.findViewById(R.id.text_amount);
            holder.text_number =convertView.findViewById(R.id.text_number);
            holder.text_date = convertView.findViewById(R.id.text_date);

            holder.text_amount.setTypeface(typeFace);
            holder.text_number.setTypeface(typeFace);
            holder.text_date.setTypeface(typeFace);


            convertView.setTag(holder);
        } else
        holder = (Labour_Adapter.ViewHolder) convertView.getTag();

        holder.text_amount.setText("₹ "+rowItem.getamount());
   if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.WHITE);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#E4E4E4"));
        }


        return convertView;
    }
   /* public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        mr_doctor_helpers.clear();
        if (charText.length() == 0) {
            mr_doctor_helpers.addAll(arraylist);
        } else {
            for (Labour_Helper wp : arraylist) {
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
    }*/

}
