package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.bsninfotech.accountwell.Const.CustomTypefaceSpan;
import com.bsninfotech.accountwell.Const.TypefaceUtil;
import com.bsninfotech.accountwell.Model.ServerApi;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Analytic_Report extends AppCompatActivity {


    private BarChart chart_sale,chart_purchase;
    float[] value={110.000f,100.000f,60.000f,80.000f,130.000f};
    String[] bankname = {"Stock In", "Stock Out"};
    String[] month = {"Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    final int[] MY_COLORS = { Color.rgb(0, 155, 0),Color.rgb(255, 114, 63), Color.rgb(0, 128, 155),
            Color.rgb(0, 204, 204), Color.rgb(153, 51, 155), Color.rgb(0,176,80), Color.rgb(79,129,189)};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anyalatic__report);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        fontChanger.replaceFonts((LinearLayout)findViewById(R.id.layout_anayreport));
        SpannableString str = new SpannableString("Analytic Report (Sale/Purchase)");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        str.setSpan (new CustomTypefaceSpan("",font2), 0, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(str);
        chart_sale=findViewById(R.id.Chartsale);
        chart_purchase=findViewById(R.id.Chartspurshase);

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


        ArrayList valueSet3 = new ArrayList();
        BarEntry v3e1 = new BarEntry(10.000f, 0); // Jan
        valueSet2.add(v3e1);
        BarEntry v3e2 = new BarEntry(10.000f, 1); // Feb
        valueSet3.add(v3e2);
        BarEntry v3e3 = new BarEntry(50.000f, 2); // Mar
        valueSet3.add(v3e3);
        BarEntry v3e4 = new BarEntry(20.000f, 3); // Apr
        valueSet3.add(v3e4);
        BarEntry v3e5 = new BarEntry(10.000f, 4); // May
        valueSet3.add(v3e5);
        BarEntry v3e6 = new BarEntry(20.000f, 5); // Jun
        valueSet3.add(v3e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Sale");
        barDataSet1.setColor(Color.rgb(243,147,3));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Paid");
        barDataSet2.setColor(Color.rgb(76, 175, 80));
        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "UnPaid");
        barDataSet3.setColor(Color.rgb(156, 156, 156));

        ArrayList dataSets = new ArrayList();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);

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
        chart_sale.setData(data);
        chart_sale.setDescription("Sale Graph");
        chart_sale.animateXY(2000, 2000);
        chart_sale.invalidate();
        secondgraph();
    }

    private void secondgraph() {
        ArrayList valueSet1 = new ArrayList();
        BarEntry v1e1 = new BarEntry(150.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(120.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(80.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);

        ArrayList valueSet2 = new ArrayList();
        BarEntry v2e1 = new BarEntry(130.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(30.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(100.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);


        ArrayList valueSet3 = new ArrayList();
        BarEntry v3e1 = new BarEntry(10.000f, 0); // Jan
        valueSet2.add(v3e1);
        BarEntry v3e2 = new BarEntry(10.000f, 1); // Feb
        valueSet3.add(v3e2);
        BarEntry v3e3 = new BarEntry(50.000f, 2); // Mar
        valueSet3.add(v3e3);
        BarEntry v3e4 = new BarEntry(20.000f, 3); // Apr
        valueSet3.add(v3e4);
        BarEntry v3e5 = new BarEntry(10.000f, 4); // May
        valueSet3.add(v3e5);
        BarEntry v3e6 = new BarEntry(20.000f, 5); // Jun
        valueSet3.add(v3e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Purchase");
        barDataSet1.setColor(Color.rgb(17,143,243));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Paid");
        barDataSet2.setColor(Color.rgb(76, 175, 80));
        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "UnPaid");
        barDataSet3.setColor(Color.rgb(156, 156, 156));

        ArrayList dataSets = new ArrayList();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);

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
        chart_purchase.setData(data);
        chart_purchase.setDescription("Purchase Graph");
        chart_purchase.animateXY(3000, 3000);
        chart_purchase.invalidate();
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
