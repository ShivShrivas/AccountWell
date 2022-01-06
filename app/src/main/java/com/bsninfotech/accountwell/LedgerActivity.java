package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bsninfotech.accountwell.Adapter.LedgerAdapter;
import com.bsninfotech.accountwell.Helper.Ledger_Helper;
import com.bsninfotech.accountwell.Helper.Stock_Helper;
import com.bsninfotech.accountwell.RetrofitSetup.ApiService;
import com.bsninfotech.accountwell.RetrofitSetup.RestClient;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LedgerActivity extends AppCompatActivity {
    ImageView fromDateCalender,toDateCalender;
    DatePickerDialog datePickerDialog;
    TextView fromDateTxt,ToDateTxt;
    ApplicationControllerAdmin applicationControllerAdmin;
    String todayDate,fromdate;
    public  static String openingBAlance;
    public  static String closingBalance;
    TextView closingBalanceTxt,openBalanceTxt;
    String subcode,name;
    LedgerAdapter adapter;
    CheckBox checkBoxLedger_Narration;
    public static ProgressDialog mProgressDialog;
    ImageView nodatafound;
    List<Ledger_Helper> ledger_helpers=new ArrayList<>();
    RecyclerView recyclerView;
    EditText searchViewLedger;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ledger);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        applicationControllerAdmin= (ApplicationControllerAdmin) getApplication();
        fromDateCalender=findViewById(R.id.formDateCalLedger);
        fromDateTxt=findViewById(R.id.fromDateTxtledger);
        toDateCalender=findViewById(R.id.toDateCalLedger);
        ToDateTxt=findViewById(R.id.toDateTxtledger);
        searchViewLedger=findViewById(R.id.searchViewLedger);
        recyclerView=findViewById(R.id.RecView_cashsummary);
        checkBoxLedger_Narration=findViewById(R.id.checkBoxLedger_Narration);
        openBalanceTxt=findViewById(R.id.openBalanceTxt);
        nodatafound=findViewById(R.id.nodatafound);
        closingBalanceTxt=findViewById(R.id.closingBalanceTxt);
        mProgressDialog = new ProgressDialog(LedgerActivity.this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        mProgressDialog.setContentView(R.layout.progress_dialoge);
        mProgressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);
        getFromDate();
        getTodate();
        Intent i=getIntent();
        subcode=i.getStringExtra("SubCode");
        name=i.getStringExtra("Name");
        getSupportActionBar().setTitle(name);
        Log.d("TAG", "onCreate: "+subcode);
        searchViewLedger.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString().trim());
            }
        });
        fromDateCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final java.util.Calendar c = java.util.Calendar.getInstance();
                final int mYear = c.get(java.util.Calendar.YEAR);
                final int mDay = c.get(Calendar.MONTH);
                final int cDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(LedgerActivity.this,
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
                                    fromdate= spf.format(newDate);
                                    fromDateTxt.setText( spf1.format(newDate1));

                            }
                        }, mYear, mDay, cDay);
                datePickerDialog.show();
            }
        });
        toDateCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final java.util.Calendar c = java.util.Calendar.getInstance();
                final int mYear = c.get(java.util.Calendar.YEAR);
                final int mDay = c.get(Calendar.MONTH);
                final int cDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(LedgerActivity.this,
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

                                    todayDate= spf.format(newDate);
                                    ToDateTxt.setText(spf1.format(newDate1));

                            }
                        }, mYear, mDay, cDay);
                datePickerDialog.show();
            }
        });
        fromDateTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getLedgerData(subcode,todayDate,fromdate,applicationControllerAdmin.getComp_code(),applicationControllerAdmin.getschoolCode(),applicationControllerAdmin.getSite_Code(),applicationControllerAdmin.getBranchcode(),applicationControllerAdmin.getFyIdCode());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ToDateTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getLedgerData(subcode,todayDate,fromdate,applicationControllerAdmin.getComp_code(),applicationControllerAdmin.getschoolCode(),applicationControllerAdmin.getSite_Code(),applicationControllerAdmin.getBranchcode(),applicationControllerAdmin.getFyIdCode());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        getLedgerData(subcode,todayDate,fromdate,applicationControllerAdmin.getComp_code(),applicationControllerAdmin.getschoolCode(),applicationControllerAdmin.getSite_Code(),applicationControllerAdmin.getBranchcode(),applicationControllerAdmin.getFyIdCode());


    }

    private void getLedgerData(String subcode, String todayDate, String fromdate, String comp_code, String getschoolCode, String site_code, String branchcode, String fyIdCode) {
        RestClient restClient=new RestClient();
        ApiService service=restClient.getApiService();
        Log.d("TAG", "getLedgerData: "+paraLedger("09",comp_code,branchcode,getschoolCode,site_code,fyIdCode,fromdate,todayDate,subcode,"1","1"));
        Call<List<Ledger_Helper>> call=service.getLedgerData(paraLedger("9",comp_code,branchcode,getschoolCode,site_code,fyIdCode,fromdate,todayDate,subcode,"1","1"));
        call.enqueue(new Callback<List<Ledger_Helper>>() {
            @Override
            public void onResponse(Call<List<Ledger_Helper>> call, Response<List<Ledger_Helper>> response) {
                Log.d("TAG", "onResponse:ledger "+response.body());
                ledger_helpers=response.body();

                openBalanceTxt.setText("₹"+ledger_helpers.get(0).getBalance());
                closingBalanceTxt.setText("₹"+ledger_helpers.get(ledger_helpers.size()-1).getBalance());
                if (ledger_helpers.size()==2){
                    nodatafound.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                    LedgerActivity.mProgressDialog.dismiss();
                }else {
                    nodatafound.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    adapter=new LedgerAdapter(getApplicationContext(),R.layout.ledger_item_card,ledger_helpers,0);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<Ledger_Helper>> call, Throwable t) {

            }
        });

    }

    private JsonObject paraLedger(String i, String comp_code, String branchcode, String getschoolCode, String site_code, String fyIdCode, String fromdate, String todayDate, String subcode, String i1, String i2) {
        JsonObject jsonObject=new JsonObject();
        jsonObject.addProperty("Action",i);
        jsonObject.addProperty("SchoolCode",comp_code);
        jsonObject.addProperty("BranchCode",branchcode);
        jsonObject.addProperty("CompCode",getschoolCode);
        jsonObject.addProperty("SiteCode",site_code);
        jsonObject.addProperty("FYId",fyIdCode);
        jsonObject.addProperty("FromDate",fromdate);
        jsonObject.addProperty("ToDate",todayDate);
        jsonObject.addProperty("SubCode",subcode);
        jsonObject.addProperty("EntryNarration",i1);
        jsonObject.addProperty("VoucherNarration",i2);
        return jsonObject;
    }

    private void getTodate() {
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MMM-dd", Locale.getDefault());
        ToDateTxt.setText(df.format(c));
        todayDate=df1.format(c);
    }

    private void getFromDate() {

        fromDateTxt.setText("01-04-2020");
        fromdate=("2020-Apr-01");

    }

    public void narrationStatus(View view) {
        if (checkBoxLedger_Narration.isChecked()){
            adapter=new LedgerAdapter(getApplicationContext(),R.layout.ledger_item_card,ledger_helpers,0);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else {
            adapter=new LedgerAdapter(getApplicationContext(),R.layout.ledger_item_card,ledger_helpers,1);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

    }
    private void filter(String text) {
        ArrayList<Ledger_Helper> filteredList = new ArrayList<>();


                for (Ledger_Helper item : ledger_helpers) {
                    if ( item.getName().contains(text)) {
                        filteredList.add(item);
                    }
        } adapter.notifyDataSetChanged();
        adapter.filterList(filteredList);

    }
}