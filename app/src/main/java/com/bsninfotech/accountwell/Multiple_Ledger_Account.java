package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.bsninfotech.accountwell.Adapter.CashSummary_Adapter;
import com.bsninfotech.accountwell.Adapter.StockLedger_Adapter;
import com.bsninfotech.accountwell.Const.CustomTypefaceSpan;
import com.bsninfotech.accountwell.Const.TypefaceUtil;
import com.bsninfotech.accountwell.Helper.BankSummary_Helper;
import com.bsninfotech.accountwell.Model.ServerApi;

import java.util.ArrayList;

public class Multiple_Ledger_Account extends AppCompatActivity {


    ListView list_cashsummary;
    String[] crdr = {"Cr.", "Dr.", "Cr.", "Dr.","Cr.", "Dr.", "Dr.", "Dr."};
    String[] compname = {"CISCO PVT. LTD.", "MRF PVT. LTD.", "SRS PVT. LTD.", "BSN INFOTECH PVT. LTD.","CISCO PVT. LTD.", "MRF PVT. LTD.", "SRS PVT. LTD.", "BSN INFOTECH PVT. LTD."};
    String[] compnadd = {"Northwest, Washington, DC, 20036.", "Gomti Nagar, Lucknow, Uttar Pradesh 226010","Vipin Khand, Gomti Nagar, Lucknow","Vipin Khand, Gomti Nagar, Lucknow","Northwest, Washington, DC, 20036.", "Gomti Nagar, Lucknow, Uttar Pradesh 226010","Vipin Khand, Gomti Nagar, Lucknow","Vipin Khand, Gomti Nagar, Lucknow"};
    CashSummary_Adapter adapter;
    BankSummary_Helper helper;
    ArrayList<BankSummary_Helper> cashlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple__ledger__account);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        fontChanger.replaceFonts((LinearLayout)findViewById(R.id.layout_multiledger__account));
        SpannableString str = new SpannableString("Multiple Ledger(2019-2020)");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        str.setSpan (new CustomTypefaceSpan("",font2), 0, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(str);

        list_cashsummary = findViewById(R.id.list_ledger_account);
        cashlist = new ArrayList<>();
        for (int i = 0; i < compname.length; i++) {
            helper = new BankSummary_Helper(compname[i], compnadd[i],crdr[i]);
            cashlist.add(helper);
        }
        StockLedger_Adapter adapter = new StockLedger_Adapter(Multiple_Ledger_Account.this, R.layout.item_stockledger, cashlist);
        list_cashsummary.setAdapter(adapter);
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
