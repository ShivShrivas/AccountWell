package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bsninfotech.accountwell.Adapter.CashSummary_Adapter;
import com.bsninfotech.accountwell.Adapter.CashSummary_Adapter_New;
import com.bsninfotech.accountwell.Const.CustomTypefaceSpan;
import com.bsninfotech.accountwell.Const.TypefaceUtil;
import com.bsninfotech.accountwell.Helper.CashsummaryHelper;
import com.bsninfotech.accountwell.Helper.Company_Helper;
import com.bsninfotech.accountwell.Model.ServerApi;
import com.bsninfotech.accountwell.Services.JsonParser;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CashSummary extends AppCompatActivity {
    ApplicationControllerAdmin applicationControllerAdmin;
    RecyclerView recView_cashSummary;
    ImageView noResult;
    EditText searchView;
    CashSummary_Adapter_New cashSummary_adapter;
    ArrayList<CashsummaryHelper> cashsummaryHelpers=new ArrayList<>();


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_summary2);
        applicationControllerAdmin=(ApplicationControllerAdmin) getApplication();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        fontChanger.replaceFonts((ConstraintLayout)findViewById(R.id.layout_cashsummarynew));
        SpannableString str = new SpannableString("Cash Summary");
        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/Nunito-SemiBold.ttf");
        str.setSpan (new CustomTypefaceSpan("",font2), 0, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        getSupportActionBar().setTitle(str);


        recView_cashSummary=findViewById(R.id.recView_cashSummary);
        searchView=findViewById(R.id.searchViewCashSumary);
        new getCashSummary().execute();
        recView_cashSummary.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        Log.d("TAG", "onCreate:list size "+cashsummaryHelpers.size());
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });


    }

    private void filter(String text) {
        ArrayList<CashsummaryHelper> filteredList = new ArrayList<>();

        for (CashsummaryHelper item : cashsummaryHelpers) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

            cashSummary_adapter.filterList(filteredList);
            noResult.setVisibility(View.GONE);


    }


    private class getCashSummary extends AsyncTask<String, String, Integer> {
        public getCashSummary() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            switch (integer){
                case 1:

                        cashSummary_adapter=new CashSummary_Adapter_New(getApplicationContext(),R.layout.item_vash_summary_new,cashsummaryHelpers);
                        recView_cashSummary.setAdapter(cashSummary_adapter);




            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected Integer doInBackground(String... strings) {
            int status=0;
            JsonParser jsonParser=new JsonParser(getApplicationContext());
            Log.d("TAG", "doInBackground:cahs summary  "+applicationControllerAdmin.getServicesapplication()+ServerApi.GET_CASHSUMMARY+"///"+paraCashSummary("1",applicationControllerAdmin.getComp_code(),applicationControllerAdmin.getBranchcode(),applicationControllerAdmin.getschoolCode(),applicationControllerAdmin.getSite_Code(),"1"));
            String response=jsonParser.executePost(applicationControllerAdmin.getServicesapplication()+ServerApi.GET_CASHSUMMARY,paraCashSummary("1",applicationControllerAdmin.getComp_code(),applicationControllerAdmin.getBranchcode(),applicationControllerAdmin.getschoolCode(),applicationControllerAdmin.getSite_Code(),"1"),"1");
            Log.d("TAG", "doInBackground: "+response);
            if (response!=null)
            {
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    cashsummaryHelpers=new ArrayList<>();
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                        CashsummaryHelper summary=new CashsummaryHelper();
                        summary.setBalance(jsonObject.getDouble("Balance"));
                        summary.setName(jsonObject.getString("Name"));
                        summary.setNotation(jsonObject.getString("Notation"));

                        cashsummaryHelpers.add(summary);

                    status=1;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return status;
        }

        private String paraCashSummary(String s, String comp_code, String branchcode,String schoolCode,String site_code, String s1) {
            JSONObject jsonObject=new JSONObject();
            try {
                jsonObject.put("Action",s);
                jsonObject.put("SchoolCode",comp_code);
                jsonObject.put("BranchCode",branchcode);
                jsonObject.put("CompCode",schoolCode);
                jsonObject.put("SiteCode",site_code);
                jsonObject.put("FYId",s1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject.toString();
        }
    }
}