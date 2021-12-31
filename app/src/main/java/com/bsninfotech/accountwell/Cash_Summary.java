package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;


import com.bsninfotech.accountwell.Adapter.CashSummary_Adapter;
import com.bsninfotech.accountwell.Adapter.FoldingCellListAdapter;
import com.bsninfotech.accountwell.Const.CustomTypefaceSpan;
import com.bsninfotech.accountwell.Const.TypefaceUtil;
import com.bsninfotech.accountwell.Helper.CashSummary_Helper;
import com.bsninfotech.accountwell.Model.ServerApi;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.ramotion.foldingcell.FoldingCell;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class Cash_Summary extends AppCompatActivity implements  SearchView.OnQueryTextListener{

    LineChart lineChart;
    LineData lineData;
    LineDataSet lineDataSet;
    ArrayList<Entry> lineEntries;
    LineChartView lineChartView;
    SearchView searchView;
    String[] axisData = {"Jan","Feb","Mar","Apr","May","June","July","Aug", "Sept","Oct","Nov","Dec"};
    int[] yAxisData = {50, 20, 15, 30, 20, 60, 15, 40, 45, 10, 90, 18};
    ListView list_cashsummary;
    String[] compname = {"CISCO PVT. LTD.", "MRF PVT. LTD.", "SRS PVT. LTD.", "BSN INFOTECH PVT. LTD."};
    String[] compnadd = {"2025 M Street, Northwest, Washington, DC, 20036.", "Passport Bhawan, Vipin Khand, Gomti Nagar, Lucknow, Uttar Pradesh 226010","Vipin Khand, Gomti Nagar, Lucknow","Vipin Khand, Gomti Nagar, Lucknow"};
    CashSummary_Adapter adapter;
    CashSummary_Helper helper;
    ArrayList<CashSummary_Helper> cashlist;
    LinearLayout layout_hide,layout_graph,layout_search;
    int hide=0;
    ImageView image_minus;
    View devider2,devider3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_summary);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        fontChanger.replaceFonts((LinearLayout)findViewById(R.id.layout_cashsummary));
        SpannableString str = new SpannableString("Cash Summary");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/Nunito-SemiBold.ttf");
        str.setSpan (new CustomTypefaceSpan("",font2), 0, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(str);
        devider2=findViewById(R.id.divider2);
        devider3=findViewById(R.id.divider3);
        lineChartView = findViewById(R.id.linechart);
        list_cashsummary = findViewById(R.id.list_cashsummary);
        layout_hide = findViewById(R.id.layout_hide);
        layout_graph = findViewById(R.id.layout_graph);
        searchView=findViewById(R.id.searchView);
        layout_search = findViewById(R.id.layout_search);
        layout_search.setVisibility(View.GONE);
        image_minus = findViewById(R.id.image_minus);
        image_minus.setImageResource(R.drawable.ic_plus);
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();
        Line line = new Line(yAxisValues).setColor(Color.parseColor("#059c41"));
        for (int i = 0; i < axisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }
        for (int i = 0; i < yAxisData.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }
        List lines = new ArrayList();
        lines.add(line);
        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(12);
        axis.setTextColor(Color.parseColor("#000000"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("In Lakh");
        yAxis.setTextColor(Color.parseColor("#000000"));
        yAxis.setTextSize(12);
        data.setAxisYLeft(yAxis);


        lineChartView.setLineChartData(data);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        viewport.top = 110;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);
        lineChartView.startDataAnimation(1000);

        final Button btn_credit=findViewById(R.id.button_credit);
        final Button button_debit=findViewById(R.id.button_debit);
        btn_credit.setTextColor(Color.parseColor("#ffffff"));
        btn_credit.setBackgroundResource(R.drawable.bg_button_green);
        devider2.setVisibility(View.VISIBLE);
        devider3.setVisibility(View.INVISIBLE);

        btn_credit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_credit.setTextColor(Color.parseColor("#ffffff"));
                btn_credit.setBackgroundResource(R.drawable.bg_button_green);
                button_debit.setTextColor(Color.parseColor("#000000"));
                button_debit.setBackgroundResource(R.drawable.bg_button_disable);
                devider2.setVisibility(View.VISIBLE);
                devider3.setVisibility(View.INVISIBLE);

            }
        });
        button_debit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_debit.setTextColor(Color.parseColor("#ffffff"));
                button_debit.setBackgroundResource(R.drawable.bg_button_green);
                btn_credit.setTextColor(Color.parseColor("#000000"));
                btn_credit.setBackgroundResource(R.drawable.bg_button_disable);
                devider2.setVisibility(View.INVISIBLE);
                devider3.setVisibility(View.VISIBLE);

            }
        });
        layout_graph.setVisibility(View.GONE);
        layout_search.setVisibility(View.VISIBLE);

        layout_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hide==0){
                    image_minus.setImageResource(R.drawable.ic_minus);
                    layout_graph.animate().alpha(1.0f);
                    layout_graph.setVisibility(View.VISIBLE);
                    layout_search.setVisibility(View.GONE);
                    hide=1;
                }else{
                    image_minus.setImageResource(R.drawable.ic_plus);
                    layout_graph.animate().alpha(0.0f);
                    layout_graph.setVisibility(View.GONE);
                    layout_search.setVisibility(View.VISIBLE);
                    hide=0;
                }
            }
        });

      /*  cashlist = new ArrayList<>();
        for (int i = 0; i < compname.length; i++) {
            helper = new CashSummary_Helper(compname[i], compnadd[i]);
            cashlist.add(helper);
        }
        final FoldingCellListAdapter adapter = new FoldingCellListAdapter(Cash_Summary.this, cashlist);
        list_cashsummary.setAdapter(adapter);
        list_cashsummary.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos);
            }
        });*/
        cashlist = new ArrayList<>();
        for (int i = 0; i < compname.length; i++) {
            helper = new CashSummary_Helper(compname[i], compnadd[i]);
            cashlist.add(helper);
        }
//        CashSummary_Adapter adapter = new CashSummary_Adapter(Cash_Summary.this, R.layout.item_cashsummary, cashlist);
//        list_cashsummary.setAdapter(adapter);
    }
    private void getEntries() {
        lineEntries = new ArrayList<>();
        lineEntries.add(new Entry(2f, 0));
        lineEntries.add(new Entry(4f, 1));
        lineEntries.add(new Entry(6f, 1));
        lineEntries.add(new Entry(8f, 3));
        lineEntries.add(new Entry(7f, 4));
        lineEntries.add(new Entry(3f, 3));
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

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        String text = s;
        adapter.filter(text);
        return false;
    }
}
