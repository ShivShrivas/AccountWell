package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Log;

import com.bsninfotech.accountwell.Adapter.CashSummary_Adapter_New;
import com.bsninfotech.accountwell.Adapter.CreditorAdapter;
import com.bsninfotech.accountwell.Const.CustomTypefaceSpan;
import com.bsninfotech.accountwell.Const.TypefaceUtil;
import com.bsninfotech.accountwell.Helper.CashsummaryHelper;
import com.bsninfotech.accountwell.Model.ServerApi;
import com.bsninfotech.accountwell.Services.JsonParser;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Creditor_Summary extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem tabCreditor, tabDebitor;
    ViewPager viewPager;
    CreditorAdapter adapter;
    ApplicationControllerAdmin applicationControllerAdmin;
    RecyclerView recyclerViewCreditorSummary;
    ArrayList<CashsummaryHelper> cashsummaryHelpers=new ArrayList<>();

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditor_summary);
        applicationControllerAdmin=(ApplicationControllerAdmin) getApplication();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        fontChanger.replaceFonts((ConstraintLayout)findViewById(R.id.layout_cashsummarynew));
        SpannableString str = new SpannableString("Cash Summary");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/Nunito-SemiBold.ttf");
        str.setSpan (new CustomTypefaceSpan("",font2), 0, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(str);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tabLayout=findViewById(R.id.tablayout);
        tabCreditor=findViewById(R.id.tabCreditor);
        tabDebitor=findViewById(R.id.tabDebitor);
        viewPager=findViewById(R.id.viewPageCreditor);
        adapter=new CreditorAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition()==0 || tab.getPosition()==1){
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//
//        recyclerViewCreditorSummary=findViewById(R.id.recView_CreditorSumarry);
//        new getCreditor_Summary().execute();
//        recyclerViewCreditorSummary.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//
//    }
//

    }
}