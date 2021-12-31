package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bsninfotech.accountwell.Adapter.BankSummary_Adapter;
import com.bsninfotech.accountwell.Adapter.CashSummary_Adapter;
import com.bsninfotech.accountwell.Const.CustomTypefaceSpan;
import com.bsninfotech.accountwell.Const.TypefaceUtil;
import com.bsninfotech.accountwell.Helper.BankSummary_Helper;
import com.bsninfotech.accountwell.Helper.CashSummary_Helper;
import com.bsninfotech.accountwell.Model.ServerApi;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Bank_Summary extends AppCompatActivity {

    ArrayList dataSets = null;
    private BarChart chart;
    ListView list_cashsummary;
    String[] crdr = {"Cr.", "Dr.", "Cr.", "Dr."};
    String[] compname = {"CISCO PVT. LTD.", "MRF PVT. LTD.", "SRS PVT. LTD.", "BSN INFOTECH PVT. LTD."};
    String[] compnadd = {"Northwest, Washington, DC, 20036.", "Gomti Nagar, Lucknow, Uttar Pradesh 226010","Vipin Khand, Gomti Nagar, Lucknow","Vipin Khand, Gomti Nagar, Lucknow"};
    CashSummary_Adapter adapter;
    BankSummary_Helper helper;
    ArrayList<BankSummary_Helper> cashlist;
    LinearLayout layout_hide,layout_graph,layout_select;
    int hide=0;
    ImageView image_minus;
    float[] value={110.000f,100.000f,60.000f,80.000f,130.000f};
    String[] bankname = {"SBI Bank", "Corporate Bank", "Punjab", "Axis", "HDFC"};
    final int[] MY_COLORS = {Color.rgb(255, 114, 63), Color.rgb(0, 155, 0), Color.rgb(0, 128, 155),
            Color.rgb(0, 204, 204), Color.rgb(153, 51, 155), Color.rgb(0,176,80), Color.rgb(79,129,189)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank__summary);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        fontChanger.replaceFonts((LinearLayout)findViewById(R.id.layout_banksummary));
        SpannableString str = new SpannableString("Bank Summary");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/Nunito-SemiBold.ttf");
        str.setSpan (new CustomTypefaceSpan("",font2), 0, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(str);

        chart = findViewById(R.id.barChart);
        dataSets = new ArrayList();
        ArrayList xAxis = new ArrayList();
        for(int i=0;i<value.length;i++){
            ArrayList<BarEntry> valueSet = new ArrayList<>();
            BarEntry v1e = new BarEntry(value[i], 0); // Jan
            valueSet.add(v1e);
            BarDataSet barDataSet = new BarDataSet(valueSet,bankname[i]);
            barDataSet.setColor(MY_COLORS[i]);
         // barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
            dataSets.add(barDataSet);
        }

        xAxis.add("2019-2020");
        BarData data = new BarData(xAxis,dataSets);
        chart.setData(data);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.invalidate();

        ///////////////////////////////////
        list_cashsummary = findViewById(R.id.list_banksummary);
        layout_hide = findViewById(R.id.layout_hide);
        layout_graph = findViewById(R.id.layout_graph);
        layout_select = findViewById(R.id.layout_select);
        layout_select.setVisibility(View.GONE);
        image_minus = findViewById(R.id.image_minus);
        image_minus.setImageResource(R.drawable.ic_minus);

        cashlist = new ArrayList<>();
        for (int i = 0; i < compname.length; i++) {
            helper = new BankSummary_Helper(compname[i], compnadd[i],crdr[i]);
            cashlist.add(helper);
        }

        BankSummary_Adapter adapter = new BankSummary_Adapter(Bank_Summary.this, R.layout.item_banksummary, cashlist);
        list_cashsummary.setAdapter(adapter);
        layout_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hide==1){
                    image_minus.setImageResource(R.drawable.ic_minus);
                    layout_graph.animate().alpha(1.0f);
                    layout_graph.setVisibility(View.VISIBLE);
                    layout_select.setVisibility(View.GONE);
                    hide=0;
                }else{
                    image_minus.setImageResource(R.drawable.ic_plus);
                    layout_graph.animate().alpha(0.0f);
                    layout_graph.setVisibility(View.GONE);
                    layout_select.setVisibility(View.VISIBLE);
                    hide=1;
                }
            }
        });
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
