package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
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
    Button shareAsPdf;
    List listName=new ArrayList();
    List listBalance= new ArrayList();
    CheckBox checkBoxLedger_Narration;
    public static ProgressDialog mProgressDialog;
    ImageView nodatafound;
    List<Ledger_Helper> ledger_helpers=new ArrayList<>();
    RecyclerView recyclerView;
    EditText searchViewLedger;
    ProgressDialog progressDialog;
    private boolean narrationStatus=true;

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
        shareAsPdf=findViewById(R.id.shareAsPdf);
        applicationControllerAdmin= (ApplicationControllerAdmin) getApplication();
        fromDateCalender=findViewById(R.id.formDateCalLedger);
        fromDateTxt=findViewById(R.id.fromDateTxtledger);
        progressDialog = new ProgressDialog(LedgerActivity.this);
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
        shareAsPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                mProgressDialog.setContentView(R.layout.progress_dialoge);
                mProgressDialog.getWindow().setBackgroundDrawableResource(
                        android.R.color.transparent);
                try {



                    createPdf();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
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

    private void createPdf() throws FileNotFoundException {

        Date c = Calendar.getInstance().getTime();
        String fieName=name.replace(" ","");
        String newFilename=fieName.replace("/","_");
        SimpleDateFormat df = new SimpleDateFormat("dd_mm_yyyy_hhmmss_s", Locale.getDefault());

        String pdfPath= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file=new File(pdfPath,newFilename+df.format(c).toString()+"my.pdf");
        OutputStream outputStream=new FileOutputStream(file);
        PdfWriter pdfWriter=new PdfWriter(file);
        PdfDocument pdfDocument=new PdfDocument(pdfWriter);
        Document document=new Document(pdfDocument);
        document.setHorizontalAlignment(HorizontalAlignment.CENTER);
        Paragraph companyName=new Paragraph("BSN Infotech Private limited"+"\n").setBold().setFontSize(20).setTextAlignment(TextAlignment.CENTER);
        Text location=new Text("Lucknow Branch"+"\n").setBold().setFontSize(15).setTextAlignment(TextAlignment.CENTER);
        Text phEmai=new Text("Ph. No.: 0522-4959891"+"\t"+"Fax No.: 0522-4005977"+"\n").setTextAlignment(TextAlignment.CENTER).setFontSize(15);
        Paragraph report=new Paragraph("Ledger Reports of "+name+"\n").setTextAlignment(TextAlignment.CENTER).setBold().setFontSize(16).setUnderline();
        Paragraph dates=new Paragraph("From Date :"+fromdate+" To Date : "+todayDate);

        companyName.add(location);
        companyName.add(phEmai);
        document.add(companyName);
      document.add(report);
      document.add(dates);
        float coloumnwidth[]={120f,140f,250f,148f,148f,148f,46f};
        Table table=new Table(coloumnwidth);
        table.addCell(new Cell(2,1).add(new Paragraph("Date").setBold().setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell(2,1).add(new Paragraph("V-type/no.").setBold().setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell(2,1).add(new Paragraph("Name").setBold().setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell(2,1).add(new Paragraph("Debit").setBold().setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell(2,1).add(new Paragraph("Credit").setBold().setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell(2,1).add(new Paragraph("Balance").setBold().setTextAlignment(TextAlignment.CENTER)));
        table.addCell(new Cell(2,1).add(new Paragraph("Dr/Cr").setBold().setTextAlignment(TextAlignment.CENTER)));

        for (int i=0;i<ledger_helpers.size();i++){
            String fullName = ledger_helpers.get(i).getName();
            String[] name = fullName.split("#");
            String balance=ledger_helpers.get(i).getBalance();
            String date=ledger_helpers.get(i).getDate();
            String vtype=ledger_helpers.get(i).getV_Type()+"/"+ledger_helpers.get(i).getCvNo();
            String debit=ledger_helpers.get(i).getDebitAmt();
            String credit=ledger_helpers.get(i).getCreditAmt();
            String dr_cr=ledger_helpers.get(i).getDRCR();;
            table.addCell(date);
            table.addCell(vtype);
            if (narrationStatus==true){
                try {
                    table.addCell(name[0]+"\n"+name[1]);
                }catch (Exception e){
                    table.addCell(name[0]);
                }
            }else {
                table.addCell(name[0]);
            }


            table.addCell(debit);
            table.addCell(credit);
            table.addCell(balance);
            table.addCell(dr_cr);


        }


        document.add( table);
        document.close();
        mProgressDialog.hide();
        Toast.makeText(getApplicationContext(), "pdf created", Toast.LENGTH_SHORT).show();
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
                for (int i=0;i<ledger_helpers.size();i++){
                    listName.add(ledger_helpers.get(i).getName());
                    listBalance.add(ledger_helpers.get(i).getBalance());
                }
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
           narrationStatus=true;
        }else {
            adapter=new LedgerAdapter(getApplicationContext(),R.layout.ledger_item_card,ledger_helpers,1);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            narrationStatus=false;
        }

    }
    private void filter(String text) {
        ArrayList<Ledger_Helper> filteredList = new ArrayList<>();


                for (Ledger_Helper item : ledger_helpers) {
                    if ( item.getName().contains(text)) {
                        filteredList.add(item);
                    }
        }
        adapter.filterList(filteredList);
        adapter.notifyDataSetChanged();
    }
}