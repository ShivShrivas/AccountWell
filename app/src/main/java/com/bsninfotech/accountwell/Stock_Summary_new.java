package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.bsninfotech.accountwell.Adapter.Stock_Adapter;
import com.bsninfotech.accountwell.Adapter.Transaction_Adapter;
import com.bsninfotech.accountwell.Helper.Stock_Helper;
import com.bsninfotech.accountwell.Helper.Transactions_Helper;
import com.bsninfotech.accountwell.Model.ServerApi;
import com.bsninfotech.accountwell.Services.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Stock_Summary_new extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText searchView;
    ApplicationControllerAdmin applicationControllerAdmin;
    ArrayList<Stock_Helper> stock_helpers=new ArrayList<>();
    Stock_Adapter adapter;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_summary_new);
        applicationControllerAdmin=(ApplicationControllerAdmin) getApplication();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
//        fontChanger.replaceFonts((ConstraintLayout)findViewById(R.id.layout_cashsummarynew));
//        SpannableString str = new SpannableString("Cash Summary");
//        Typeface font2 = Typeface.createFromAsset(getAssets(), "fonts/Nunito-SemiBold.ttf");
//        str.setSpan (new CustomTypefaceSpan("",font2), 0, str.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
//        getSupportActionBar().setTitle(str);
        recyclerView =findViewById(R.id.recViewStockSummary);
        searchView=findViewById(R.id.searchViewStock);

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

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        new getStockSummary().execute();
    }
    private class getStockSummary extends AsyncTask<String ,String ,Integer> {
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            switch (integer){
                case 1:

                    adapter =new Stock_Adapter(getApplicationContext(),R.layout.stock_summary_item,stock_helpers);
                    recyclerView.setAdapter(adapter);
            }


        }

        @Override
        protected Integer doInBackground(String... strings) {
            int status=0;
            JsonParser jsonParser=new JsonParser(getApplicationContext());
            String response=jsonParser.executePost(applicationControllerAdmin.getServicesapplication()+ ServerApi.GET_TRNASACTIONSUMMARY,paraTransaction("3", applicationControllerAdmin.getComp_code(), applicationControllerAdmin.getBranchcode(), applicationControllerAdmin.getschoolCode(), applicationControllerAdmin.getSite_Code(), "1"), "1");
            Log.d("TAG", "doInBackground:resonse "+response);
            if (response!=null){
                try {
                    JSONArray jsonArray=new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject object=jsonArray.getJSONObject(i);
                        Stock_Helper stock_helper=new Stock_Helper();
                        stock_helper.setName(object.getString("Name"));
                        stock_helper.setNoOfBag(object.getString("NoOfBag"));
                        stock_helper.setTotalBag(object.getString("TotalBag"));
                        stock_helper.setQtyPerBag(object.getString("QtyPerBag"));
                        stock_helpers.add(stock_helper);
                        status=1;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return status;
        }

        private String paraTransaction(String s, String comp_code, String branchcode, String getschoolCode, String site_code, String s1) {
            JSONObject jsonObject=new JSONObject();
            try {
                jsonObject.put("Action",s);
                jsonObject.put("SchoolCode", comp_code);
                jsonObject.put("BranchCode", branchcode);
                jsonObject.put("CompCode", getschoolCode);
                jsonObject.put("SiteCode", site_code);
                jsonObject.put("FYId", s1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jsonObject.toString();
        }
    }
    private void filter(String text) {
        ArrayList<Stock_Helper> filteredList = new ArrayList<>();

        for (Stock_Helper item : stock_helpers) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        adapter.filterList(filteredList);
    }

    }



