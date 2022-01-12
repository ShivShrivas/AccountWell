package com.bsninfotech.accountwell;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.bsninfotech.accountwell.Const.CustomTypefaceSpan;
import com.bsninfotech.accountwell.Const.TypefaceUtil;
import com.bsninfotech.accountwell.Model.ServerApi;

public class Account_Reports extends AppCompatActivity {
    Toolbar toolbarAccountsReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account__reports);
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarAccountsReports=findViewById(R.id.toolbarAccountsReports);
        toolbarAccountsReports.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        fontChanger.replaceFonts((ConstraintLayout)findViewById(R.id.layout_Account_Reports));
        SpannableString str = new SpannableString("Account Reports");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        str.setSpan (new CustomTypefaceSpan("",font2), 0, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);


        LinearLayout layout_ledger=findViewById(R.id.layout_ledger);
        layout_ledger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account_Reports.this, Ledger_Account.class);
                startActivity(intent);
            }
        });

        LinearLayout layout_multiple_ledger=findViewById(R.id.layout_multipleledger);
        layout_multiple_ledger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account_Reports.this, Multiple_Ledger_Account.class);
                startActivity(intent);
            }
        });

        LinearLayout layout_general_bal=findViewById(R.id.layout_generalbal);
        layout_general_bal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Account_Reports.this, General_Trial_Balance.class);
                startActivity(intent);
            }
        });



        LinearLayout layout_group_trial=findViewById(R.id.layout_group_trial);
        layout_group_trial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        LinearLayout layout_details_trial=findViewById(R.id.layout_details_trial);
        layout_details_trial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
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
