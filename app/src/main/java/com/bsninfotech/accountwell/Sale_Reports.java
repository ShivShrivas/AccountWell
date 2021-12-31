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

import com.bsninfotech.accountwell.Adapter.SaleReport_Adapter;
import com.bsninfotech.accountwell.Const.CustomTypefaceSpan;
import com.bsninfotech.accountwell.Const.TypefaceUtil;
import com.bsninfotech.accountwell.Helper.SaleReport_Helper;
import com.bsninfotech.accountwell.Model.ServerApi;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Sale_Reports extends AppCompatActivity {


    BarChart chart_sale;
    ArrayList dataSets = null;
    float[] value={110.000f,100.000f,60.000f,80.000f,130.000f};
    String[] bankname = {"Tobacco Leaves", "Unmanufactured Tobacco", "GT", "D.F.", "FADA"};
    final int[] MY_COLORS = {Color.rgb(255, 114, 63), Color.rgb(0, 155, 0), Color.rgb(0, 128, 155),
            Color.rgb(0, 204, 204), Color.rgb(153, 51, 155), Color.rgb(0,176,80), Color.rgb(79,129,189)};
    ImageView image_minus;
    LinearLayout layout_hide,layout_hidegraph;
    int hide=0;

    ListView list_salereport;
    String[] crdr = {"Cr.", "Dr.", "Cr.", "Dr."};
    String[] compname = {"CISCO PVT. LTD.", "MRF PVT. LTD.", "SRS PVT. LTD.", "BSN INFOTECH PVT. LTD.","CISCO PVT. LTD.", "MRF PVT. LTD.", "SRS PVT. LTD.", "BSN INFOTECH PVT. LTD."};
    String[] compnadd = {"Tobacco Leaves", "Unmanufactured Tobacco", "GT", "D.F.", "FADA","Tobacco Leaves", "Unmanufactured Tobacco", "GT", "D.F.", "FADA"};
    SaleReport_Adapter adapter;
    SaleReport_Helper helper;
    ArrayList<SaleReport_Helper> cashlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale__reports);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        fontChanger.replaceFonts((LinearLayout)findViewById(R.id.layout_salereport));
        SpannableString str = new SpannableString("Sale Report (2019-2020)");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        str.setSpan (new CustomTypefaceSpan("",font2), 0, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(str);
        layout_hide = findViewById(R.id.layout_hide);
        layout_hidegraph = findViewById(R.id.layout_hidegraph);
        image_minus = findViewById(R.id.image_minus);
        image_minus.setImageResource(R.drawable.ic_minus);
        layout_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hide==1){
                    image_minus.setImageResource(R.drawable.ic_minus);
                    layout_hidegraph.animate().alpha(1.0f);
                    layout_hidegraph.setVisibility(View.VISIBLE);
                    hide=0;
                }else{
                    image_minus.setImageResource(R.drawable.ic_plus);
                    layout_hidegraph.animate().alpha(0.0f);
                    layout_hidegraph.setVisibility(View.GONE);
                    hide=1;
                }
            }
        });

        chart_sale=findViewById(R.id.Chartsale);
        dataSets = new ArrayList();
        ArrayList xAxis = new ArrayList();
        for(int i=0;i<value.length;i++){
            ArrayList<BarEntry> valueSet = new ArrayList<>();
            BarEntry v1e = new BarEntry(value[i], 0); // Jan
            valueSet.add(v1e);
            BarDataSet barDataSet = new BarDataSet(valueSet,  bankname[i]);
            barDataSet.setColor(MY_COLORS[i]);
        // barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
            dataSets.add(barDataSet);
        }
        xAxis.add("2019-2020");
        BarData data = new BarData(xAxis,dataSets);
        chart_sale.setData(data);
        chart_sale.setDescription("");
        chart_sale.animateXY(2000, 2000);
        chart_sale.invalidate();

        list_salereport = findViewById(R.id.list_salereport);
        cashlist = new ArrayList<>();
        for (int i = 0; i < compname.length; i++) {
            helper = new SaleReport_Helper(compname[i], compnadd[i],"");
            cashlist.add(helper);
        }
        SaleReport_Adapter adapter = new SaleReport_Adapter(Sale_Reports.this, R.layout.item_salereport, cashlist);
        list_salereport.setAdapter(adapter);
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
