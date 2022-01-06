package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Log;

import com.bsninfotech.accountwell.Adapter.AccountSummaryAdapter;
import com.bsninfotech.accountwell.Const.CustomTypefaceSpan;
import com.bsninfotech.accountwell.Const.TypefaceUtil;
import com.bsninfotech.accountwell.Helper.Accounts_Helper;
import com.bsninfotech.accountwell.Model.ServerApi;
import com.bsninfotech.accountwell.RetrofitSetup.ApiService;
import com.bsninfotech.accountwell.RetrofitSetup.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountSummary extends AppCompatActivity {
    ApplicationControllerAdmin applicationControllerAdmin;
    List<Accounts_Helper> accounts_helpers=new ArrayList<>();
    RecyclerView AccountSummaryRecView;
    AccountSummaryAdapter adapter;
    String action,activityName;
    public static ProgressDialog mProgressDialog;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_summary);
        applicationControllerAdmin=(ApplicationControllerAdmin) getApplication();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i=getIntent();
        mProgressDialog = new ProgressDialog(AccountSummary.this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        mProgressDialog.setContentView(R.layout.progress_dialoge);
        mProgressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        action=i.getStringExtra("action");
        activityName=i.getStringExtra("name");
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        fontChanger.replaceFonts((ConstraintLayout)findViewById(R.id.AccountSummaryLayout));
        SpannableString str = new SpannableString(activityName);
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/Nunito-SemiBold.ttf");
        str.setSpan (new CustomTypefaceSpan("",font2), 0, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(str);
        AccountSummaryRecView=findViewById(R.id.AccountSummaryRecView);

        getAccountsList();
    }

    private void getAccountsList() {
        Log.d("TAG", "getAccountsList: "+paraAccounts(action,applicationControllerAdmin.getschoolCode(),applicationControllerAdmin.getBranchcode(),applicationControllerAdmin.getComp_code(),applicationControllerAdmin.getSite_Code(),applicationControllerAdmin.getFyID()));
        RestClient restClient=new RestClient();
        ApiService service=restClient.getApiService();
        Call<List<Accounts_Helper>> accounts_helper=service.getAccountsList(paraAccounts(action,applicationControllerAdmin.getComp_code(),applicationControllerAdmin.getBranchcode(),applicationControllerAdmin.getschoolCode(),applicationControllerAdmin.getSite_Code(),applicationControllerAdmin.getFyIdCode()));
        accounts_helper.enqueue(new Callback<List<Accounts_Helper>>() {
            @Override
            public void onResponse(Call<List<Accounts_Helper>> call, Response<List<Accounts_Helper>> response) {
                Log.d("TAG", "onResponse:response "+response.body());
                accounts_helpers=response.body();
                AccountSummaryRecView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                 adapter=new AccountSummaryAdapter(getApplicationContext(),R.layout.account_item_card,accounts_helpers);
                AccountSummaryRecView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Accounts_Helper>> call, Throwable t) {

            }
        });
    }

    private JsonObject paraAccounts(String i, String getschoolCode, String branchcode, String comp_code, String site_code, String fyID) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",i);
        jsonObject.addProperty("SchoolCode",getschoolCode);
        jsonObject.addProperty("BranchCode",branchcode);
        jsonObject.addProperty("CompCode",comp_code);
        jsonObject.addProperty("SiteCode",site_code);
        jsonObject.addProperty("FYId",fyID);

        return jsonObject;
    }


}