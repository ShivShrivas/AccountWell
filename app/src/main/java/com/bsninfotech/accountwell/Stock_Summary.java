package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bsninfotech.accountwell.Const.CustomTypefaceSpan;
import com.bsninfotech.accountwell.Const.TypefaceUtil;
import com.bsninfotech.accountwell.Model.ServerApi;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Stock_Summary extends AppCompatActivity {

    private BarChart chart;
    ArrayList dataSets = null;
    float[] value={110.000f,100.000f,60.000f,80.000f,130.000f};
    String[] bankname = {"Stock In", "Stock Out"};
    String[] month = {"Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    final int[] MY_COLORS = { Color.rgb(0, 155, 0),Color.rgb(255, 114, 63), Color.rgb(0, 128, 155),
            Color.rgb(0, 204, 204), Color.rgb(153, 51, 155), Color.rgb(0,176,80), Color.rgb(79,129,189)};
    BarDataSet barDataSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock__summary);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        fontChanger.replaceFonts((LinearLayout)findViewById(R.id.layout_stocksummary));
        SpannableString str = new SpannableString("Stock Summary");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        str.setSpan (new CustomTypefaceSpan("",font2), 0, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(str);
        chart = findViewById(R.id.barChartstock);

        ArrayList valueSet1 = new ArrayList();
        BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);

        ArrayList valueSet2 = new ArrayList();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Stock In");
        barDataSet1.setColor(Color.rgb(0, 155, 0));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Stock Out");
        barDataSet2.setColor(Color.rgb(255, 114, 63));
        dataSets = new ArrayList();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);

        ArrayList xAxis = new ArrayList();
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        xAxis.add("JUL");
        xAxis.add("AUG");
        xAxis.add("SEP");
        xAxis.add("OCT");
        xAxis.add("NOV");

        BarData data = new BarData(xAxis,dataSets);
        chart.setData(data);
        chart.setDescription("Stock Graph");
        chart.animateXY(2000, 2000);
        chart.invalidate();

        TextView textstock_ledger=findViewById(R.id.textstock_ledger);
        textstock_ledger.setText(R.string.ledger);
        textstock_ledger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Stock_Summary.this,Stock_Ledger.class);
                startActivity(intent);
            }
        });


     /* dataSets = new ArrayList();
        ArrayList xAxis = new ArrayList();
        for(int i=0;i<bankname.length;i++){
                ArrayList<BarEntry> valueSet = new ArrayList<>();
                ArrayList<BarEntry> valueSet1 = new ArrayList<>();
                BarEntry v1e = new BarEntry(value[i], i);
                valueSet.add(v1e);
                barDataSet = new BarDataSet(valueSet,  bankname[i]);
                barDataSet.setColor(MY_COLORS[i]);
            BarEntry v1e1 = new BarEntry(value[i+1], i);
            valueSet1.add(v1e1);
            barDataSet = new BarDataSet(valueSet1,  bankname[i]);
            barDataSet.setColor(MY_COLORS[i+1]);
                // barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
                //dataSets.add(barDataSet);
                xAxis.add(month[i]);
                dataSets.add(barDataSet);
        }
        BarData data = new BarData(xAxis,dataSets);
        chart.setData(data);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.invalidate(); */


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
