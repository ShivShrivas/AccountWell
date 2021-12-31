package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bsninfotech.accountwell.Adapter.Labour_Adapter;
import com.bsninfotech.accountwell.Const.CustomTypefaceSpan;
import com.bsninfotech.accountwell.Const.TypefaceUtil;
import com.bsninfotech.accountwell.Helper.Labour_Helper;
import com.bsninfotech.accountwell.Model.ServerApi;

import java.util.ArrayList;

public class Labourpayment_Report extends AppCompatActivity {

    ListView list_labourpayment;
    String[] crdr = {"8500", "9500", "5500", "3000","8500", "8000", "6500", "2500","8500", "9500", "5500", "3000","8500", "8000", "6500", "2500"};
    String[] compname = {"CISCO PVT. LTD.", "MRF PVT. LTD.", "SRS PVT. LTD.", "BSN INFOTECH PVT. LTD."};
    String[] compnadd = {"Northwest, Washington, DC, 20036.", "Gomti Nagar, Lucknow, Uttar Pradesh 226010","Vipin Khand, Gomti Nagar, Lucknow","Vipin Khand, Gomti Nagar, Lucknow"};
    Labour_Adapter adapter;
    Labour_Helper helper;
    ArrayList<Labour_Helper> cashlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_labourpayment__report);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        fontChanger.replaceFonts((LinearLayout)findViewById(R.id.layout_laboupayment));
        SpannableString str = new SpannableString("Labour Payment Report");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/Nunito-SemiBold.ttf");
        str.setSpan (new CustomTypefaceSpan("",font2), 0, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(str);

        list_labourpayment = findViewById(R.id.list_labourpayment);
        cashlist = new ArrayList<>();
        for (int i = 0; i < crdr.length; i++) {
            helper = new Labour_Helper("", crdr[i],"");
            cashlist.add(helper);
        }
        adapter = new Labour_Adapter(Labourpayment_Report.this, R.layout.item_labourlist, cashlist);
        list_labourpayment.setAdapter(adapter);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        switch (item.getItemId()) {
            case android.R.id.home:
                // this takes the user 'back', as if they pressed the left-facing triangle icon on the main android toolbar.
                // if this doesn't work as desired, another possibility is to call `finish()` here.
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
