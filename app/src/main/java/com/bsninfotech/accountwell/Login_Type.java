package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bsninfotech.accountwell.Adapter.CompanyNameList_Adapter;
import com.bsninfotech.accountwell.Adapter.FinancialYearList_Adapter;
import com.bsninfotech.accountwell.Adapter.SiteNameList_Adapter;
import com.bsninfotech.accountwell.Const.TypefaceUtil;
import com.bsninfotech.accountwell.Helper.Company_Helper;
import com.bsninfotech.accountwell.Helper.FYID_Helper;
import com.bsninfotech.accountwell.Helper.Site_Helper;
import com.bsninfotech.accountwell.Helper.User_Helper;
import com.bsninfotech.accountwell.Model.ServerApi;
import com.bsninfotech.accountwell.RetrofitSetup.ApiService;
import com.bsninfotech.accountwell.RetrofitSetup.RestClient;
import com.bsninfotech.accountwell.Services.JsonParser;
import com.google.android.gms.common.api.Api;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Type extends AppCompatActivity {
    public String FY_ID;
    ApplicationControllerAdmin applicationController;
    Spinner spinnerSelectFYID ,spinnerSelectCompanyName,spinnerSelectSiteName;
    FinancialYearList_Adapter adapter;
    CompanyNameList_Adapter adapterCompany;
    SiteNameList_Adapter adapterSite;
    ConstraintLayout layout_loginpoint;

    Site_Helper site_helper;
    FYID_Helper fyid_helper;
    Company_Helper company_helper;
    public List<FYID_Helper> selectFY=new ArrayList<>();
    public List<Company_Helper> selectCompany=new ArrayList<>();
    public List<Site_Helper> selectSite=new ArrayList<>();
    String[] fYID={"2020-2021"};
    String[] companiesName={"BSN Infotech Pvt. Ltd."};
    String[] siteNane={"HEAD OFFICE","Basti","Kanpur","New Delhi","Varanasi"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__type);
        getSupportActionBar().hide();
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/"+ ServerApi.FONT_DASHBOARD);
        fontChanger.replaceFonts((ConstraintLayout)findViewById(R.id.layout_loginpoint));
        applicationController= (ApplicationControllerAdmin) getApplication();
        layout_loginpoint=findViewById(R.id.layout_loginpoint);
        spinnerSelectFYID=findViewById(R.id.spinnerFinancialYear);
        spinnerSelectFYID.setDropDownVerticalOffset(70);
        spinnerSelectCompanyName=findViewById(R.id.spinnerCompanyName);
        spinnerSelectCompanyName.setDropDownVerticalOffset(70);
        spinnerSelectSiteName=findViewById(R.id.spinnerSelectSite);
        spinnerSelectSiteName.setDropDownVerticalOffset(70);
        Button button_login=findViewById(R.id.button_login_point);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(Login_Type.this, MainActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.transition.fadein, R.transition.fadeout);
//                finish();
                getDashboardData();
            }
        });
        getCompanyName();
        getFYID();
        getBranchName();


    }

    private void getDashboardData() {
        if (!applicationController.getUserID().equals("") && !company_helper.getCom_Code().equals("") && !applicationController.getBranchcode().equals("") && !applicationController.getschoolCode().equals("") && !site_helper.getSite_Code().equals("")){
            RestClient restClient=new RestClient();
            ApiService service= restClient.getApiService();
            Call<List<User_Helper>> call=service.getDashBoard(ParaDashboard(applicationController.getUserID(),company_helper.getCom_Code(),applicationController.getBranchcode(),applicationController.getschoolCode(),site_helper.getSite_Code()));
            call.enqueue(new Callback<List<User_Helper>>() {
                @Override
                public void onResponse(Call<List<User_Helper>> call, Response<List<User_Helper>> response) {
                    List<User_Helper> user_helper=response.body();
                    applicationController.setuserName(user_helper.get(0).getUserName());
                    applicationController.setuserProfile_logo(user_helper.get(0).getPhotoPath());
                    applicationController.setappusermobile(user_helper.get(0).getContactNo());
                    applicationController.setUserMail(user_helper.get(0).getUserName());
                    applicationController.setDesignationId(user_helper.get(0).getDesignation());
                    applicationController.setCompanyName(user_helper.get(0).getCompanyName());
                    applicationController.setBranchName(user_helper.get(0).getCompanyBranchName());
                    applicationController.setAddress(user_helper.get(0).getAddress());
                    applicationController.setDepartmentId(user_helper.get(0).getDepartment());
                    Intent intent = new Intent(Login_Type.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.transition.fadein, R.transition.fadeout);

                }

                @Override
                public void onFailure(Call<List<User_Helper>> call, Throwable t) {

                }
            });

        }else{
            Snackbar snackbarb = Snackbar
                            .make(layout_loginpoint, "Your Id is Inactive. Please Contact to Administrator", Snackbar.LENGTH_LONG)
                            .setAction("Restart", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                 recreate();
                                }
                            });
            snackbarb.show();

        }

    }

    private void getBranchName() {
        RestClient restClient=new RestClient();
        ApiService service= restClient.getApiService();
        Call<List<Site_Helper>> call=service.getSiteName(ParaFYId(5,applicationController.getschoolCode(),applicationController.getBranchcode(),applicationController.getUserID()));
        call.enqueue(new Callback<List<Site_Helper>>() {
            @Override
            public void onResponse(Call<List<Site_Helper>> call, Response<List<Site_Helper>> response) {
                selectSite=response.body();
                Resources res=getResources();
                adapterSite = new SiteNameList_Adapter (getApplicationContext(), R.layout.spinner_class_item, selectSite,res);
                spinnerSelectSiteName.setAdapter(adapterSite);
                    spinnerSelectSiteName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                             site_helper= (Site_Helper) adapterView.getSelectedItem();
                             applicationController.setSite_Code(site_helper.getSite_Code());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
            }

            @Override
            public void onFailure(Call<List<Site_Helper>> call, Throwable t) {

            }
        });

    }

    private void getFYID() {
        RestClient restClient=new RestClient();
        ApiService service= restClient.getApiService();
        Call<List<FYID_Helper>> call=service.getFYName(ParaFYId(8,applicationController.getschoolCode(),applicationController.getBranchcode(),applicationController.getUserID()));
        call.enqueue(new Callback<List<FYID_Helper>>() {
            @Override
            public void onResponse(Call<List<FYID_Helper>> call, Response<List<FYID_Helper>> response) {
                Log.d("TAG", "onResponse: "+response);
                selectFY=response.body();
                Resources res = getResources();
                adapter = new FinancialYearList_Adapter(getApplicationContext(), R.layout.spinner_class_item, selectFY,res);
                spinnerSelectFYID.setAdapter(adapter);
                spinnerSelectFYID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                         fyid_helper= (FYID_Helper) adapterView.getSelectedItem();
                         applicationController.setFyID(fyid_helper.getFinancialYearName());
                         applicationController.setFyIdCode(fyid_helper.getFinancialYearId());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<FYID_Helper>> call, Throwable t) {

            }
        });
    }

    private void getCompanyName() {
        RestClient restClient=new RestClient();
        ApiService service= restClient.getApiService();
        Call<List<Company_Helper>> call=service.getCompanyName(ParaFYId(7,applicationController.getschoolCode(),applicationController.getBranchcode(),applicationController.getUserID()));
        call.enqueue(new Callback<List<Company_Helper>>() {
            @Override
            public void onResponse(Call<List<Company_Helper>> call, Response<List<Company_Helper>> response) {

              selectCompany=response.body();
                Resources res = getResources();
                adapterCompany = new CompanyNameList_Adapter(getApplicationContext(), R.layout.spinner_class_item, selectCompany,res);
                spinnerSelectCompanyName.setAdapter(adapterCompany);
                spinnerSelectCompanyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        company_helper= (Company_Helper) adapterView.getSelectedItem();
                        applicationController.setComp_code(company_helper.getCom_Code());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Company_Helper>> call, Throwable t) {

            }
        });

    }

    public JsonObject ParaDashboard(String userId, String getschoolCode, String branchcode, String com_code, String site_code) {
        JsonObject jsonObject=new JsonObject();
        try {
            jsonObject.addProperty("UserId",userId);
            jsonObject.addProperty("SchoolCode",getschoolCode);
            jsonObject.addProperty("BranchCode",branchcode);
            jsonObject.addProperty("CompCode",com_code);
            jsonObject.addProperty("SiteCode",site_code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    //////////////////////////////Get the financial year in spinner/////////////////////////////////////////////////
//    private class GetFYID extends AsyncTask<String , String, Integer> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(Integer integer) {
//            super.onPostExecute(integer);
//        switch (integer){
//            case 1:
//                Resources res = getResources();
//                adapter = new FinancialYearList_Adapter(getApplicationContext(), R.layout.spinner_class_item, selectFY,res);
//                spinnerSelectFYID.setAdapter(adapter);
//                spinnerSelectFYID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                    @Override
//                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                         fyid_helper= (FYID_Helper) adapterView.getSelectedItem();
//                    }
//
//                    @Override
//                    public void onNothingSelected(AdapterView<?> adapterView) {
//
//                    }
//                });
//                break;
//            case -1:
//                Snackbar snackbar=   Snackbar.make(layout_loginpoint,"Somthing went wrong.. Please restart the App...",Snackbar.LENGTH_LONG)
//                        .setAction("restart", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                recreate();
//                            }
//                        });
//                snackbar.show();
//                break;
//
//            case 2:
//                Snackbar.make(layout_loginpoint,"Please check your internet connection...",Snackbar.LENGTH_LONG).show();
//                break;
//
//
//        }
//        }
//
//        @Override
//        protected Integer doInBackground(String... strings) {
//            int status=-1;
//            JsonParser jsonParser=new JsonParser(getApplicationContext());
//            Log.d("TAG", "doInBackground: "+applicationController.getServicesapplication()+ServerApi.GET_FYID+"///"+ParaFYId(8,applicationController.getschoolCode(),applicationController.getBranchcode(),applicationController.getUserID()));
//            String response=jsonParser.executePost(applicationController.getServicesapplication()+ServerApi.GET_FYID,ParaFYId(8,applicationController.getschoolCode(),applicationController.getBranchcode(),applicationController.getUserID()),"1");
//            Log.d("TAG", "doInBackground: response: "+response);
//            if (response!="null"){
//                if (response.length()>2){
//                    selectFY=new ArrayList<FYID_Helper>();
//                    try {
//                        JSONArray jsonArray=new JSONArray(response);
//                       for (int i=0;i<jsonArray.length();i++){
//                           JSONObject object=jsonArray.getJSONObject(i);
//                           String FinancalYear=object.getString("FinancialYearName");
//                           String FinancalYearid=object.getString("FinancialYearId");
//                           FYID_Helper fyid_helper=new FYID_Helper();
//                           fyid_helper.setFinancialYearName(FinancalYear);
//                           fyid_helper.setFinancialYearId(FinancalYearid);
//                           selectFY.add(fyid_helper);
//                           status=1;
//                       }
//                    } catch (JSONException e) {
//                        status=-1;
//                        e.printStackTrace();
//                    }
//                }
//                else {
//                    status=-1;
//                }
//            }
//            else{
//                status=2;
//            }
//            return status;
//
//        }

//    }
    private JsonObject ParaFYId(int i, String getschoolCode, String branchcode, String userID) {
        JsonObject jsonObject=new JsonObject();
        try {
            jsonObject.addProperty("Action",i);
            jsonObject.addProperty("SchoolCode",getschoolCode);
            jsonObject.addProperty("BranchCode",branchcode);
            jsonObject.addProperty("CreatedBy",userID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    ////////////////////////////////Get the company name in spinner///////////////////////////////////////
    //    private class GetCompanyName extends AsyncTask<String , String, Integer> {
    //        @Override
    //        protected void onPreExecute() {
    //            super.onPreExecute();
    //        }
    //
    //        @Override
    //        protected void onPostExecute(Integer integer) {
    //            super.onPostExecute(integer);
    //            switch (integer){
    //                case 1:
    //                    Resources res = getResources();
    //                    adapterCompany = new CompanyNameList_Adapter(getApplicationContext(), R.layout.spinner_class_item, selectCompany,res);
    //                    spinnerSelectCompanyName.setAdapter(adapterCompany);
    //                    spinnerSelectCompanyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    //                        @Override
    //                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    //                             company_helper= (Company_Helper) adapterView.getSelectedItem();
    //                             applicationController.setComp_code(company_helper.getCom_Code());
    //                        }
    //
    //                        @Override
    //                        public void onNothingSelected(AdapterView<?> adapterView) {
    //
    //                        }
    //                    });
    //                    break;
    //                case -1:
    //                    Snackbar snackbar=   Snackbar.make(layout_loginpoint,"Somthing went wrong.. Please restart the App...",Snackbar.LENGTH_LONG)
    //                            .setAction("restart", new View.OnClickListener() {
    //                                @Override
    //                                public void onClick(View view) {
    //                                    recreate();
    //                                }
    //                            });
    //                    snackbar.show();
    //                    break;
    //                case 2:
    //                    Snackbar.make(layout_loginpoint,"Please check your internet connection.",Snackbar.LENGTH_LONG)
    //                            .show();
    //                    break;
    //            }
    //        }
    //
    //        @Override
    //        protected Integer doInBackground(String... strings) {
    //            int status=-1;
    //            JsonParser jsonParser=new JsonParser(getApplicationContext());
    //            Log.d("TAG", "doInBackground: "+applicationController.getServicesapplication()+ServerApi.GET_FYID+"///"+ParaFYId(8,applicationController.getschoolCode(),applicationController.getBranchcode(),applicationController.getUserID()));
    //            String response=jsonParser.executePost(applicationController.getServicesapplication()+ServerApi.GET_COMPANY,ParaFYId(7,applicationController.getschoolCode(),applicationController.getBranchcode(),applicationController.getUserID()),"1");
    //            Log.d("TAG", "doInBackground: response: "+response);
    //            if (response!=null){
    //                if (response.length()>2){
    //                    selectCompany=new ArrayList<Company_Helper>();
    //                    try {
    //                        JSONArray jsonArray=new JSONArray(response);
    //                        for (int i=0;i<jsonArray.length();i++){
    //                            JSONObject object=jsonArray.getJSONObject(i);
    //                            String Comp_Name=object.getString("Comp_Name");
    //                            String Com_Code=object.getString("Com_Code");
    //                            Company_Helper company_helper=new Company_Helper();
    //                          company_helper.setCom_Code(Com_Code);
    //                          company_helper.setComp_Name(Comp_Name);
    //                            selectCompany.add(company_helper);
    //                            status=1;
    //                        }
    //                    } catch (JSONException e) {
    //                        e.printStackTrace();
    //                        status=-1;
    //                    }
    //
    //
    //                }
    //                else {
    //                    status=-1;
    //                }
    //            }else{
    //                status=2;
    //            }
    //            return status;
    //
    //        }
    //    }
    //////////////////////////////////////get Site name for spinner////////////////////////////////////////////
    //    private class GetSiteName extends AsyncTask<String , String, Integer> {
    //        @Override
    //        protected void onPreExecute() {
    //            super.onPreExecute();
    //        }
    //
    //        @Override
    //        protected void onPostExecute(Integer integer) {
    //            super.onPostExecute(integer);
    //            switch (integer){
    //                case 1:
    //                    Resources res = getResources();
    //                    adapterSite = new SiteNameList_Adapter (getApplicationContext(), R.layout.spinner_class_item, selectSite,res);
    //                    spinnerSelectSiteName.setAdapter(adapterSite);
    //                    spinnerSelectSiteName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    //                        @Override
    //                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
    //                             site_helper= (Site_Helper) adapterView.getSelectedItem();
    //                             applicationController.setSite_Code(site_helper.getSite_Code());
    //                        }
    //
    //                        @Override
    //                        public void onNothingSelected(AdapterView<?> adapterView) {
    //
    //                        }
    //                    });
    //                    break;
    //                case -1:
    //                    Snackbar snackbar=   Snackbar.make(layout_loginpoint,"Somthing went wrong.. Please restart the App...",Snackbar.LENGTH_LONG)
    //                            .setAction("restart", new View.OnClickListener() {
    //                                @Override
    //                                public void onClick(View view) {
    //                                    recreate();
    //                                }
    //                            });
    //                    snackbar.show();
    //                    break;
    //                case 2:
    //                    Snackbar.make(layout_loginpoint,"Please check your internet connection.",Snackbar.LENGTH_LONG)
    //                            .show();
    //                    break;
    //            }
    //        }
    //
    //        @Override
    //        protected Integer doInBackground(String... strings) {
    //            int status=-1;
    //            JsonParser jsonParser=new JsonParser(getApplicationContext());
    //            Log.d("TAG", "doInBackground: "+applicationController.getServicesapplication()+ServerApi.GET_FYID+"///"+ParaFYId(8,applicationController.getschoolCode(),applicationController.getBranchcode(),applicationController.getUserID()));
    //            String response=jsonParser.executePost(applicationController.getServicesapplication()+ServerApi.GET_SITE,ParaFYId(5,applicationController.getschoolCode(),applicationController.getBranchcode(),applicationController.getUserID()),"1");
    //            Log.d("TAG", "doInBackground: response: "+response);
    //            if (response!=null){
    //                if (response.length()>2){
    //                    selectSite=new ArrayList<Site_Helper>();
    //                    try {
    //                        JSONArray jsonArray=new JSONArray(response);
    //                        for (int i=0;i<jsonArray.length();i++){
    //                            JSONObject object=jsonArray.getJSONObject(i);
    //                            String Branch_Name=object.getString("Branch_Name");
    //                            String Site_Code=object.getString("Site_Code");
    //                            String Com_Code=object.getString("Com_Code");
    //                            String IsBranchHQ=object.getString("IsBranchHQ");
    //                            String IsActive=object.getString("IsActive");
    //                            Site_Helper site_helper=new Site_Helper();
    //                           site_helper.setBranch_Name(Branch_Name);
    //                           site_helper.setCom_Code(Com_Code);
    //                           site_helper.setIsActive(IsActive);
    //                           site_helper.setIsBranchHQ(IsBranchHQ);
    //                           site_helper.setSite_Code(Site_Code);
    //                            selectSite.add(site_helper);
    //                            status=1;
    //                        }
    //                    } catch (JSONException e) {
    //                        e.printStackTrace();
    //                        status=-1;
    //                    }
    //                }else{
    //                    status=-1;
    //                }
    //            }else{
    //                status=2;
    //            }
    //            return status;
    //
    //        }
    //    }
    //////////////////////////////////////////////Get Dashboard API////////////////////
//    private class getDashboard extends AsyncTask<String,String,Integer> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(Integer integer) {
//            super.onPostExecute(integer);
//            switch (integer){
//                case 1:
//                    Intent intent = new Intent(Login_Type.this, MainActivity.class);
//                startActivity(intent);
//                overridePendingTransition(R.transition.fadein, R.transition.fadeout);
//                finish();
//                    break;
//                case -1:
//                    Snackbar snackbar=   Snackbar.make(layout_loginpoint,"Somthing went wrong, Restart the App...",Snackbar.LENGTH_LONG)
//                            .setAction("restart", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                    recreate();
//                                }
//                            });
//                    snackbar.show();
//                    break;
//                    case 2:
//                Snackbar.make(layout_loginpoint,"Please check your internet connection.",Snackbar.LENGTH_LONG)
//                          .show();
//                    break;
//
//            }
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected Integer doInBackground(String... strings) {
//            int status=0;
//            JsonParser jsonParser=new JsonParser(getApplicationContext());
//            Log.d("TAG", "doInBackground: paradashboard "+applicationController.getServicesapplication()+ServerApi.GET_DASHBOARD+"/////"+ParaDashboard(applicationController.getUserID(),company_helper.getCom_Code(),applicationController.getBranchcode(),applicationController.getschoolCode(),site_helper.getSite_Code()));
//            String response=jsonParser.executePost("http://"+applicationController.getServicesapplication()+ServerApi.GET_DASHBOARD,ParaDashboard(applicationController.getUserID(),company_helper.getCom_Code(),applicationController.getBranchcode(),applicationController.getschoolCode(),applicationController.getSite_Code()),"1");
//            Log.d("TAG", "doInBackground: onresponse "+ response);
//            if (response!=null){
//
//                    try {
//                        JSONArray jsonArray=new JSONArray(response);
//
//                        for (int i=0;i<jsonArray.length();i++){
//                            JSONObject object=jsonArray.getJSONObject(i);
//                            if(object.length()>11){
//                                applicationController.setuserName(object.getString("UserName"));
//                                applicationController.setuserProfile_logo(object.getString("PhotoPath"));
//                                applicationController.setappusermobile(object.getString("ContactNo"));
//                                applicationController.setUserMail(object.getString("Email"));
//                                applicationController.setDesignationId(object.getString("Designation"));
//                                applicationController.setCompanyName(object.getString("CompanyName"));
//                                applicationController.setBranchName(object.getString("CompanyBranchName"));
//                                applicationController.setAddress(object.getString("Address"));
//                                applicationController.setDepartmentId(object.getString("Department"));
//                                status=1;
//                            }else{
//                                status=-1;
//                            }
//
//
//                        }
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        status=-1;
//                    }
//
//                }else {
//                status=2;
//            }
//
//
//            return status;
//        }
//}
}
