package com.bsninfotech.accountwell.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bsninfotech.accountwell.Helper.Company_Helper;
import com.bsninfotech.accountwell.Helper.Site_Helper;
import com.bsninfotech.accountwell.R;

import java.util.ArrayList;

public class SiteNameList_Adapter extends ArrayAdapter<String> {
private Context applicationContext;
private  int spinner_class_item;
private ArrayList selectSite;
private Resources res;
        LayoutInflater inflater;
        Site_Helper tempValues=null;
public SiteNameList_Adapter(Context applicationContext, int spinner_class_item, ArrayList selectSite, Resources res) {
        super(applicationContext,spinner_class_item,selectSite);
        this.applicationContext=applicationContext;
        this.res=res;
        this.selectSite=selectSite;
        this.spinner_class_item=spinner_class_item;
        inflater= (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
@NonNull
@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
        }

@Override
public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
        }

private View getCustomView(int position, View convertView, ViewGroup parent) {
        View row=inflater.inflate(R.layout.spinner_class_item,parent,false);
        tempValues= (Site_Helper) selectSite.get(position);
        TextView text_cname        = (TextView)row.findViewById(R.id.text_cname);
        TextView text_cID          = (TextView)row.findViewById(R.id.text_cID);
        text_cname.setText(tempValues.getBranch_Name());
        text_cID.setText(tempValues.getSite_Code());

        return row;
        }
        }

