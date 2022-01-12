package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bsninfotech.accountwell.Adapter.CashSummary_Adapter;
import com.bsninfotech.accountwell.Adapter.StockLedger_Adapter;
import com.bsninfotech.accountwell.Const.CustomTypefaceSpan;
import com.bsninfotech.accountwell.Const.TypefaceUtil;
import com.bsninfotech.accountwell.Helper.BankSummary_Helper;
import com.bsninfotech.accountwell.Model.ServerApi;
import com.bsninfotech.accountwell.RetrofitSetup.ApiService;
import com.bsninfotech.accountwell.RetrofitSetup.RestClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Ledger_Account extends AppCompatActivity {
    ApplicationControllerAdmin applicationControllerAdmin;

  TextView fromdateTxtLedgerAccounts,todateTxtLedgerAccounts;
  ImageView fromdateCalenderLedgerAccounts,todateCalenderLedgerAccounts;
    DatePickerDialog datePickerDialog;
    String fromdate,todate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ledger__account);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        fontChanger.replaceFonts((LinearLayout) findViewById(R.id.layout_ledger__account));
        SpannableString str = new SpannableString("Ledger (2019-2020)");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        str.setSpan(new CustomTypefaceSpan("", font2), 0, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(str);
        fromdateCalenderLedgerAccounts=findViewById(R.id.fromdateCalenderLedgerAccounts);
        fromdateTxtLedgerAccounts=findViewById(R.id.fromdateTxtLedgerAccounts);
        todateCalenderLedgerAccounts=findViewById(R.id.todateCalenderLedgerAccounts);
        todateTxtLedgerAccounts=findViewById(R.id.todateTxtLedgerAccounts);
        fromdateCalenderLedgerAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final java.util.Calendar c = java.util.Calendar.getInstance();
                final int mYear = c.get(java.util.Calendar.YEAR);
                final int mDay = c.get(Calendar.MONTH);
                final int cDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Ledger_Account.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                String todaysDate=dayOfMonth+ "-"+ (monthOfYear + 1) + "-" + year;
                                SimpleDateFormat spf=new SimpleDateFormat("dd-MM-yyyy");
                                SimpleDateFormat spf1=new SimpleDateFormat("dd-MM-yyyy");
                                Date newDate= null;
                                Date newDate1= null;
                                try {
                                    newDate = spf.parse(todaysDate);
                                    newDate1 = spf.parse(todaysDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                spf= new SimpleDateFormat("yyyy-MMM-dd");
                                spf1= new SimpleDateFormat("dd-MM-yyyy");
                                 fromdate = spf.format(newDate);
                                fromdateTxtLedgerAccounts.setText( spf1.format(newDate1));

                            }
                        }, mYear, mDay, cDay);
                datePickerDialog.show();
            }
        });

        todateCalenderLedgerAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final java.util.Calendar c = java.util.Calendar.getInstance();
                final int mYear = c.get(java.util.Calendar.YEAR);
                final int mDay = c.get(Calendar.MONTH);
                final int cDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(Ledger_Account.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                String todaysDate=dayOfMonth+ "-"+ (monthOfYear + 1) + "-" + year;
                                SimpleDateFormat spf=new SimpleDateFormat("dd-MM-yyyy");
                                SimpleDateFormat spf1=new SimpleDateFormat("dd-MM-yyyy");
                                Date newDate= null;
                                Date newDate1= null;
                                try {
                                    newDate = spf.parse(todaysDate);
                                    newDate1 = spf.parse(todaysDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                spf= new SimpleDateFormat("yyyy-MMM-dd");
                                spf1= new SimpleDateFormat("dd-MM-yyyy");
                                 todate = spf.format(newDate);
                                todateTxtLedgerAccounts.setText( spf1.format(newDate1));

                            }
                        }, mYear, mDay, cDay);
                datePickerDialog.show();
            }
        });
        getLedgerAccountsData("9",todate,fromdate,applicationControllerAdmin.getComp_code(),applicationControllerAdmin.getschoolCode(),applicationControllerAdmin.getSite_Code(),applicationControllerAdmin.getBranchcode(),applicationControllerAdmin.getFyIdCode());

    }

    private void getLedgerAccountsData(String s, String todate, String fromdate, String comp_code, String getschoolCode, String site_code, String branchcode, String fyIdCode) {

    }
}
