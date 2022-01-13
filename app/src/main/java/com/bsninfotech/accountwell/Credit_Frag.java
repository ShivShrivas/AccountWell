package com.bsninfotech.accountwell;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.bsninfotech.accountwell.Adapter.AccountSummaryAdapter;
import com.bsninfotech.accountwell.Adapter.CashSummary_Adapter_New;
import com.bsninfotech.accountwell.Adapter.LedgerAdapter;
import com.bsninfotech.accountwell.Helper.Accounts_Helper;
import com.bsninfotech.accountwell.Helper.CashsummaryHelper;
import com.bsninfotech.accountwell.Model.ServerApi;
import com.bsninfotech.accountwell.RetrofitSetup.ApiService;
import com.bsninfotech.accountwell.RetrofitSetup.RestClient;
import com.bsninfotech.accountwell.Services.JsonParser;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Credit_Frag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Credit_Frag extends Fragment {
    RecyclerView recyclerView;
    ApplicationControllerAdmin applicationControllerAdmin;
    EditText searchView;
    CashSummary_Adapter_New cashSummary_adapter;
    ArrayList<CashsummaryHelper> cashsummaryHelpers = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<Accounts_Helper> accounts_helpers=new ArrayList<>();
    List<Accounts_Helper> customers=new ArrayList<>();
    AccountSummaryAdapter adapter;
    String action,activityName;
    TextView totalEntriesTxtCredit;
    public Credit_Frag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Credit_Frag.
     */
    // TODO: Rename and change types and number of parameters
    public static Credit_Frag newInstance(String param1, String param2) {
        Credit_Frag fragment = new Credit_Frag();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_credit_, container, false);
        applicationControllerAdmin=(ApplicationControllerAdmin) getActivity().getApplication();
        recyclerView = view.findViewById(R.id.recViewCredit);
        searchView=view.findViewById(R.id.searchViewCredit);
        totalEntriesTxtCredit=view.findViewById(R.id.totalEntriesTxtCredit);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayout=((LinearLayoutManager)recyclerView.getLayoutManager());
                int size=0;

                totalEntriesTxtCredit.setText("Leaving "+linearLayout.findFirstCompletelyVisibleItemPosition()+" in "+(customers.size())+" entries");
                Log.d("TAG", "onCreate: scroll "+linearLayout.findFirstCompletelyVisibleItemPosition());

            }
        });
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
        getCreditor_Summary();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    private void getCreditor_Summary() {
        RestClient restClient=new RestClient();
        ApiService service=restClient.getApiService();
        Call<List<Accounts_Helper>> accounts_helper=service.getAccountsList(paraAccounts("12",applicationControllerAdmin.getComp_code(),applicationControllerAdmin.getBranchcode(),applicationControllerAdmin.getschoolCode(),applicationControllerAdmin.getSite_Code(),applicationControllerAdmin.getFyIdCode()));
        accounts_helper.enqueue(new Callback<List<Accounts_Helper>>() {
            @Override
            public void onResponse(Call<List<Accounts_Helper>> call, Response<List<Accounts_Helper>> response) {
                Log.d("TAG", "onResponse:response "+response.body());
                accounts_helpers=response.body();
                for (int i=0;i<accounts_helpers.size();i++){
                    String fullname=accounts_helpers.get(i).getName();
                    String[] name=fullname.split("-");
                    Log.d("TAG", "onResponse: "+name[0]);
                    if (name[0].equals("Customer")){
                        Accounts_Helper accounts_helper1=new Accounts_Helper();
                        accounts_helper1.setName(accounts_helpers.get(i).getName());
                        accounts_helper1.setSubCode(accounts_helpers.get(i).getSubCode());
                        customers.add(accounts_helper1);
                    }

                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                adapter=new AccountSummaryAdapter(getContext(),R.layout.account_item_card,customers);
                recyclerView.setAdapter(adapter);


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
    private void filter(String text) {
        ArrayList<CashsummaryHelper> filteredList = new ArrayList<>();

        for (CashsummaryHelper item : cashsummaryHelpers) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        cashSummary_adapter.filterList(filteredList);
    }

//    private class getCreditor_Summary extends AsyncTask<String, String, Integer> {
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        @Override
//        protected void onPostExecute(Integer integer) {
//            super.onPostExecute(integer);
//            switch (integer) {
//                case 1:
//                     cashSummary_adapter = new CashSummary_Adapter_New(getContext(), R.layout.item_vash_summary_new, cashsummaryHelpers);
//                    recyclerView.setAdapter(cashSummary_adapter);
//
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
//            int status = 0;
//            JsonParser jsonParser = new JsonParser(getContext());
//            Log.d("TAG", "doInBackground:cahs summary  " + applicationControllerAdmin.getServicesapplication() + ServerApi.GET_CREDITORSUMMARY + "///" + paraCreditorSummary("4", applicationControllerAdmin.getComp_code(), applicationControllerAdmin.getBranchcode(), applicationControllerAdmin.getschoolCode(), applicationControllerAdmin.getSite_Code(), "1"));
//            String response = jsonParser.executePost(applicationControllerAdmin.getServicesapplication() + ServerApi.GET_CREDITORSUMMARY, paraCreditorSummary("4", applicationControllerAdmin.getComp_code(), applicationControllerAdmin.getBranchcode(), applicationControllerAdmin.getschoolCode(), applicationControllerAdmin.getSite_Code(), "1"), "1");
//            Log.d("TAG", "doInBackground: " + response);
//            if (response != null) {
//                try {
//                    JSONArray jsonArray = new JSONArray(response);
//                    cashsummaryHelpers = new ArrayList<>();
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject jsonObject = jsonArray.getJSONObject(i);
//                        CashsummaryHelper summary = new CashsummaryHelper();
//                        summary.setBalance(jsonObject.getDouble("Balance"));
//                        summary.setName(jsonObject.getString("Name"));
//                        summary.setNotation(jsonObject.getString("Notation"));
//
//                        cashsummaryHelpers.add(summary);
//
//                        status = 1;
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            return status;
//        }
//
//        private String paraCreditorSummary(String s, String comp_code, String branchcode, String getschoolCode, String site_code, String s1) {
//            JSONObject jsonObject = new JSONObject();
//            try {
//                jsonObject.put("Action", s);
//                jsonObject.put("SchoolCode", comp_code);
//                jsonObject.put("BranchCode", branchcode);
//                jsonObject.put("CompCode", getschoolCode);
//                jsonObject.put("SiteCode", site_code);
//                jsonObject.put("FYId", s1);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return jsonObject.toString();
//        }
//    }


}