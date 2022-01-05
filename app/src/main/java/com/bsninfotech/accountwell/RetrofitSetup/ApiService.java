package com.bsninfotech.accountwell.RetrofitSetup;

import com.bsninfotech.accountwell.Helper.Accounts_Helper;
import com.bsninfotech.accountwell.Helper.Company_Helper;
import com.bsninfotech.accountwell.Helper.FYID_Helper;
import com.bsninfotech.accountwell.Helper.Ledger_Helper;
import com.bsninfotech.accountwell.Helper.Site_Helper;
import com.bsninfotech.accountwell.Helper.User_Helper;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Array;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public  interface ApiService {
    //for getting main URL
    @POST("HandShake")
     Call<List<JsonObject>> getMainUrl(@Body JsonObject object);
    //for loging API

    @POST("Common.svc/mobile/MGetUserId")
    Call<List<JsonObject>> getLogin(@Body JsonObject object);

    @POST("CompanyMaster.svc/mobile/MSGetCompanyMaster")
    Call<List<Company_Helper>> getCompanyName(@Body JsonObject object);

 @POST("AccountMaster.svc/mobile/MSGetFYs")
    Call<List<FYID_Helper>> getFYName(@Body JsonObject object);

 @POST("CompanyMaster.svc/mobile/MSGetCompanyBranchMaster")
    Call<List<Site_Helper>> getSiteName(@Body JsonObject object);

 @POST("Common.svc/mobile/MGetUser")
    Call<List<User_Helper>> getDashBoard(@Body JsonObject object);

 @POST("ReportService.svc/mobile/MGetAccountName")
    Call<List<Accounts_Helper>> getAccountsList(@Body JsonObject object);

 @POST("ReportService.svc/mobile/MGetLedgerReport")
    Call<List<Ledger_Helper>> getLedgerData(@Body JsonObject object);

}
