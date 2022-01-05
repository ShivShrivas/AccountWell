package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.ArrayMap;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bsninfotech.accountwell.Const.Constant;
import com.bsninfotech.accountwell.Const.NotificationUtils;
import com.bsninfotech.accountwell.Const.TypefaceUtil;
import com.bsninfotech.accountwell.Model.ServerApi;
import com.bsninfotech.accountwell.RetrofitSetup.ApiService;
import com.bsninfotech.accountwell.RetrofitSetup.RestClient;
import com.bsninfotech.accountwell.Services.Config;
import com.bsninfotech.accountwell.Services.JsonParser;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login_Actitivty extends AppCompatActivity {
    EditText username,password;
    Button login;
    Retrofit retrofit=null;
    TextView forgetpassword,txtCompanyCode,changeCompCode;
    String userid,pasword,school_code,theme_code,schoolcode_code="",sch_code="";
    String userId,loginTypeId,sessionId,fyId,last_login;
    Typeface typeface;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    ApplicationControllerAdmin applicationController;
    LinearLayout loginlayout,compCodelayout;
    Context context;
    String regId,InstitutionType,BranchWebsite,AVer;
    CheckBox check_remember;
    String user_status,school_logo,school_name;
    ImageView imageView_logologin;
    CheckBox check_showpassword;
    Animation animMoveToTop;
    Animation animright,animfadein,animfadeout;
    ImageView imageView_help;
    LinearLayout layout_animtion,layout_aminationicon;
    RelativeLayout relative_fullmessage;
    String BranchWebsiteservices;
    int count=0;

    private static final String TAG = Login_Actitivty.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_actitivty);
        getSupportActionBar().hide();
        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        fontChanger.replaceFonts((LinearLayout) findViewById(R.id.login_layout));

        applicationController = (ApplicationControllerAdmin) getApplication();

        typeface = Typeface.createFromAsset(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
        sharedpreferences = getSharedPreferences("APPDATA", Context.MODE_PRIVATE);
        editor=sharedpreferences.edit();
        loginlayout = (LinearLayout) findViewById(R.id.login_layout);
        username = (EditText) findViewById(R.id.username);
        // username.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(30)});
        password = (EditText) findViewById(R.id.password);
        forgetpassword = (TextView) findViewById(R.id.forgetpassword);
        login = (Button) findViewById(R.id.button_login);
        txtCompanyCode=findViewById(R.id.txtCompanyCode);
        compCodelayout=findViewById(R.id.compCodeLayout);
        changeCompCode=findViewById(R.id.changeCompCode);
        check_remember = (CheckBox) findViewById(R.id.check_remember);
        String sch_code = sharedpreferences.getString("sch_code", "");
        String branch_code = sharedpreferences.getString("branch_code", "");
        String school_logo = sharedpreferences.getString("school_logo", "");
        changeCompCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
        school_code = sch_code + branch_code;
        getMainUrl(school_code);
        txtCompanyCode.setText(school_code);
        if (sch_code.equals("")) {
            compCodelayout.setVisibility(View.GONE);
            showdialog();
        } else {
            compCodelayout.setVisibility(View.VISIBLE);
            applicationController.setschoolCode(sch_code);
            applicationController.setBranchcode(branch_code);
        }
        username.setInputType(
                InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_FLAG_CAP_WORDS
        );
        //username.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
       /* imageView_logologin=findViewById(R.id.imageView_logologin);
        String imagerplace= ServerApi.LOGO_API+applicationController.getschool_logo();
        String api = applicationController.getschool_logo();
        imagerplace=imagerplace.replace("..","");
        imageView_logologin.setImageURI(Uri.parse(imagerplace));
        Glide.get(getApplicationContext()).clearMemory();
        Glide
                .with(getApplicationContext())
                .load(imagerplace)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .apply(
                        new RequestOptions()
                                .error(R.drawable.logo)
                                .fitCenter()
                )
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }
                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView_logologin);*/
        username.addTextChangedListener(textWatcher);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sch_code = sharedpreferences.getString("sch_code", "");
                String branch_code = sharedpreferences.getString("branch_code", "");
                applicationController.setschoolCode(sch_code);
                applicationController.setBranchcode(branch_code);
                schoolcode_code = sch_code;
                userid = username.getText().toString().trim();
                pasword = password.getText().toString().trim();
                if (userid.equals("")) {
                    Snackbar snackbar = Snackbar
                            .make(loginlayout, "Enter User ID.", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Snackbar snackbar1 = Snackbar.make(loginlayout, "Message is restored!", Snackbar.LENGTH_SHORT);
                                    snackbar1.show();
                                }
                            });
                    snackbar.setActionTextColor(Color.RED);
                    snackbar.show();
                } else if (pasword.equals("")) {
                    Snackbar snackbar = Snackbar
                            .make(loginlayout, "Enter Password.", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });
                    snackbar.setActionTextColor(Color.RED);
                    snackbar.show();
                } else if (sch_code.equals("")) {
                    showdialog();
                } else if (!Constant.isConnection(Login_Actitivty.this)) {
                    Snackbar snackbar = Snackbar
                            .make(loginlayout, "Check Internet Connection.", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                }
                            });
                    snackbar.setActionTextColor(Color.RED);
                    snackbar.show();
                } else {
                    loginProcess();
                }
            }
        });
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    displayFirebaseRegId();
                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received
                    String message = intent.getStringExtra("message");
                    // Toast.makeText(getApplicationContext(), "Push notification: " + message, Toast.LENGTH_LONG).show();
                    //txtMessage.setText(message);  android:text="BSN/4003/1718"  android:text="20100000005"
                }
            }
        };

        displayFirebaseRegId();
        String userId_save = sharedpreferences.getString("userId_save", "");
        String Password_save = sharedpreferences.getString("Password_save", "");
        boolean check_box = sharedpreferences.getBoolean("checkbox", false);
        if (userId_save.length() > 2) {
            username.setText(userId_save);
            password.setText(Password_save);
            check_remember.setChecked(check_box);
        }
        check_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("userId_save", username.getText().toString().trim());
                    editor.putString("Password_save", password.getText().toString().trim());
                    editor.putBoolean("checkbox", true);
                    editor.commit();
                    String userId_save = sharedpreferences.getString("userId_save", "");
                    if (userId_save.length() < 1) {
                        check_remember.setChecked(false);
                        Snackbar snackbar = Snackbar
                                .make(loginlayout, "Enter User Id", Snackbar.LENGTH_LONG)
                                .setAction("RETRY", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                    }
                                });
                        snackbar.setActionTextColor(Color.RED);
                        snackbar.show();
                    }
                } else {
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("userId_save", "");
                    editor.putString("Password_save", "");
                    editor.putBoolean("checkbox", false);
                    editor.commit();
                    username.setText("");
                    password.setText("");
                }
            }
        });
        check_showpassword = findViewById(R.id.check_showpassword);
        check_showpassword.setText("Show password");
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        check_showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    check_showpassword.setText("Hide password");
                    // edtx_password.setInputType(InputType.TYPE_CLASS_TEXT);
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    check_showpassword.setText("Show password");
                    // edtx_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent=new Intent(Login_Actitivty.this,ForgotPassword.class);
                // startActivity(intent);
                try {
                    Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                            + "://" + getApplicationContext().getPackageName()+"/"+ R.raw.notification);
                    Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), alarmSound);
                    r.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });

    }

    private void loginProcess() {
        RestClient restClient=new RestClient();
        ApiService service=restClient.getApiService();
        Call<List<JsonObject>> call=service.getLogin(Para(userid,pasword,applicationController.getschoolCode()+applicationController.getBranchcode()));
       call.enqueue(new Callback<List<JsonObject>>() {
           @Override
           public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {


               if (response!=null && !response.body().get(0).get("userid").toString().equals("null")){
                   Log.d("TAG", "onResponse:uid "+response.body().get(0).get("userid").toString());

                       Log.d("TAG", "onResponse: "+response.body().get(0).get("userid"));
                       applicationController.setUserID(response.body().get(0).get("userid").toString());
                       applicationController.setLoginType(response.body().get(0).get("loginTypeId").toString());
                       applicationController.setFyID(response.body().get(0).get("financialYear").toString());

                                    editor.putString("lastLogin",response.body().get(0).get("LastLogin").getAsString());
                                    editor.commit();
                       startActivity(new Intent(Login_Actitivty.this,Login_Type.class));

                    }else{
                   Toast.makeText(getApplicationContext(), "Please enter correct credential ", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<List<JsonObject>> call, Throwable t) {
               Toast.makeText(getApplicationContext(), "Please enter School code", Toast.LENGTH_SHORT).show();
    showdialog();
           }
       });


    }

    private void getMainUrl(String sch_code) {
        Log.d("TAG", "getMainUrl:prm "+Paraser(sch_code));
         retrofit=new Retrofit.Builder()
              .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://masterservices.bsninfotech.org/ConHandShake.svc/mobile/")
                .build();
        ApiService service=retrofit.create(ApiService.class);

      Call<List<JsonObject>> call=service.getMainUrl(Paraser(sch_code));
     call.enqueue(new Callback<List<JsonObject>>() {
         @Override
         public void onResponse(Call<List<JsonObject>> call, Response<List<JsonObject>> response) {
                if (response!=null){
                   if (response.body().size()>1){

                       Log.d("TAG", "onResponse:res "+response.body());
                       applicationController.setServicesapplication(String.valueOf(response.body().get(1).get("SDKUrl").getAsString()));

                       Log.d(TAG, "onResponse: ++ "+ ApplicationControllerAdmin.getServicesapplication());
                   }else {
                       Toast.makeText(getApplicationContext(), "Please enter School code", Toast.LENGTH_SHORT).show();
                       showdialog();
                   }

                }else {
                    Toast.makeText(getApplicationContext(), "Somthing went wrong!, Please retry", Toast.LENGTH_SHORT).show();
                    showdialog();
                }

         }

         @Override
         public void onFailure(Call<List<JsonObject>> call, Throwable t) {
             Toast.makeText(getApplicationContext(), "Somthing went wrong!, Please retry", Toast.LENGTH_SHORT).show();
             showdialog();

         }
     });



    }


    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(check_remember.isChecked()==true){
                check_remember.setChecked(false);
            }
        }
        @Override
        public void afterTextChanged(Editable s) {
        }
    };
//    private class LoginProcess extends AsyncTask<String, String, Integer> {
//        ProgressDialog progressDialog = new ProgressDialog(Login_Actitivty.this);
//        @Override
//        protected void onPreExecute() {
//            progressDialog = ProgressDialog.show(Login_Actitivty.this, "", "Please Wait...", true);
//            super.onPreExecute();
//        }
//        @Override
//        protected Integer doInBackground(String... strings) {
//            int status=0;
//            JsonParser josnparser=new JsonParser(getApplicationContext());
//            Log.d("TAG", "doInBackground: loginProcess :"+applicationController.getServicesapplication()+ServerApi.LOGIN_API+"////"+Para(userid,pasword,applicationController.getschoolCode()+applicationController.getBranchcode()));
//            String response=josnparser.executePost(applicationController.getServicesapplication()+ServerApi.LOGIN_API,Para(userid,pasword,applicationController.getschoolCode()+applicationController.getBranchcode()),"1");
//          //  String response=josnparser.executePost(applicationController.getServicesapplication()+ServerApi.LOGIN_API,Para(userid,pasword,applicationController.getschoolCode()+applicationController.getBranchcode()),"1");
//            String api =applicationController.getServicesapplication()+ServerApi.LOGIN_API;
//            if(response!=null){
//                if (response.length()>5){
//                    try {
//                        SharedPreferences.Editor editor = sharedpreferences.edit();
//
//                        JSONArray jsonArray= new JSONArray(response);
//                        JSONObject jsonobject = jsonArray.getJSONObject(0);
//                        userId=jsonobject.getString("userid");
//                        applicationController.setUserID(userid);
//                        fyId=jsonobject.getString("fyId");
//                       // sessionId=jsonobject.getString("sessionId");
//                        user_status=jsonobject.getString("Active");
//                        editor.putString("userid", userId);
//                        editor.commit();
//                        if(userId.equalsIgnoreCase("null") || userId.equals("")){
//                            status=-2;
//                        }else{
//                            if(user_status.equals("0")){
//                                status=-3;
//                            }else{
//                                loginTypeId=jsonobject.getString("loginTypeId");
//                                if(loginTypeId.equals("4") || loginTypeId.equals("2") ){
//                                   // sessionId=jsonobject.getString("sessionId");
//                                    last_login=jsonobject.getString("LastLogin");
//                                    editor.putString("userName",userid);
//                                    editor.putString("lastLogin",last_login);
//                                    editor.commit();
//                                    fyId=jsonobject.getString("fyId");
//                                    school_logo=jsonobject.getString("BranchLogo");
//                                    school_name=jsonobject.getString("BranchName");
//                                    InstitutionType=jsonobject.getString("InstitutionType");
//                                    status=1;
//                                }else{
//                                    status=-4;
//                                }
//                            }
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        status=-1;
//                    }
//                }else{
//                    status=-2;
//                }
//            }else{
//                status=-2;
//            }
//
//            return status;
//        }
//        @Override
//        protected void onPostExecute(Integer s) {
//            super.onPostExecute(s);
//            switch (s){
//                case 1:
//                    SharedPreferences.Editor editor = sharedpreferences.edit();
//                    editor.putString("userId", userid);
//                    editor.putString("Password", pasword);
//                    editor.putString("loginTypeId", loginTypeId);
//                    //editor.putString("sessionId", sessionId);
//                    editor.putString("fyId", fyId);
//                    editor.putString("school_logo", school_logo);
//                    editor.commit();
//                    applicationController.setUserID(userId);
//                    applicationController.setLoginType(loginTypeId);
//                   // applicationController.setSeesionID(sessionId);
//                    applicationController.setFyID(fyId);
//                    applicationController.setschool_name(school_name);
//
//                    applicationController.setschooltype(InstitutionType);
//
//                    Intent intent= new Intent(Login_Actitivty.this,Login_Type.class);
//                    startActivity(intent);
//                    overridePendingTransition(R.transition.fadein, R.transition.fadeout);
//
//                    finish();
//                    progressDialog.dismiss();
//                    break;
//                case -2:
//                    progressDialog.dismiss();
//                    Snackbar snackbar1 = Snackbar
//                            .make(loginlayout, "User Credentials are not Valid. Please Try Again.", Snackbar.LENGTH_LONG)
//                            .setAction("RETRY", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                   /* Snackbar snackbar1 = Snackbar.make(loginlayout, "Message is restored!", Snackbar.LENGTH_SHORT);
//                                    snackbar1.show();*/
//                                }
//                            });
//                    snackbar1.setActionTextColor(Color.RED);
//                    snackbar1.show();
//                    sharedpreferences = getSharedPreferences("APPDATA", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor1 = sharedpreferences.edit();
//                    editor1.clear();
//                    editor1.commit();
//                    break;
//                case -1:
//                    progressDialog.dismiss();
//                    Snackbar snackbar = Snackbar
//                            .make(loginlayout, "Network Congestion! Please try Again", Snackbar.LENGTH_LONG)
//                            .setAction("RETRY", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                   /* Snackbar snackbar1 = Snackbar.make(loginlayout, "Message is restored!", Snackbar.LENGTH_SHORT);
//                                    snackbar1.show();*/
//                                }
//                            });
//                    snackbar.setActionTextColor(Color.RED);
//                    snackbar.show();
//                    break;
//                case -3:
//                    progressDialog.dismiss();
//                    Snackbar snackbarb = Snackbar
//                            .make(loginlayout, "Your Id is Inactive. Please Contact to Administrator", Snackbar.LENGTH_LONG)
//                            .setAction("Done", new View.OnClickListener() {
//                                @Override
//                                public void onClick(View view) {
//                                   /* Snackbar snackbar1 = Snackbar.make(loginlayout, "Message is restored!", Snackbar.LENGTH_SHORT);
//                                    snackbar1.show();*/
//                                }
//                            });
//                    snackbarb.setActionTextColor(Color.RED);
//                    snackbarb.show();
//                    break;
//                case -4:
//                    progressDialog.dismiss();
//                    errorDialog();
//                    break;
//
//            }
//        }
//    }

    public JsonObject Para(String userid,String pasword,String school_code){
        JsonObject jsonParam = new JsonObject();
        try {
            jsonParam.addProperty("username", userid);
            jsonParam.addProperty("password", pasword);
            jsonParam.addProperty("bid", school_code);
            jsonParam.addProperty("AccessMode", "M");
            jsonParam.addProperty("GSMID", regId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParam;
    }

    private void showdialog() {
        final Dialog dialog = new Dialog(Login_Actitivty.this);
        dialog.setContentView(R.layout.dialog_company_code);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCanceledOnTouchOutside(false);
        final EditText text_schoolcode = (EditText) dialog.findViewById(R.id.editText_schcode);
        text_schoolcode.setFilters(new InputFilter[]{new InputFilter.AllCaps(),new InputFilter.LengthFilter(6)});
        TextView tagline = dialog.findViewById(R.id.textView_scode);
        TextView submitButton = dialog.findViewById(R.id.button_submitcode);
        TextView cancelButton =  dialog.findViewById(R.id.button_cancel);
        dialog.show();
        tagline.setTypeface(typeface);
        cancelButton.setTypeface(typeface);
        submitButton.setTypeface(typeface);
        text_schoolcode.setTypeface(typeface);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                school_code=text_schoolcode.getText().toString().trim();
                if(school_code.equals("")){
                    Snackbar.make(v, "Enter Company Code", Snackbar.LENGTH_LONG).show();
                }else if(school_code.length()==4){
                    sch_code=school_code.substring(0,2);
                    String branch_code=school_code.substring(2,4);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("sch_code", sch_code);
                    editor.putString("branch_code", branch_code);
                    editor.commit();

                    applicationController.setschoolCode(sch_code);
                    applicationController.setBranchcode(branch_code);
//                    new GetULR().execute();
                    getMainUrl(school_code);
//                    new Getservices().execute();
                    dialog.dismiss();
                }else if(school_code.length()==6){
                    sch_code=school_code.substring(0,4);
                    String branch_code=school_code.substring(4,6);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("sch_code", sch_code);
                    editor.putString("branch_code", branch_code);
                    editor.commit();
                    txtCompanyCode.setText(school_code);
                    compCodelayout.setVisibility(View.VISIBLE);
                    applicationController.setschoolCode(sch_code);
                    applicationController.setBranchcode(branch_code);
                     //new GetULR().execute();
                     getMainUrl(school_code);
                    // new Getservices().execute();
                    dialog.dismiss();
                }else{
                    Snackbar.make(v, "Enter Correct Company Code", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        regId = pref.getString("regId", null);
        Log.e(TAG, "Firebase reg id: " + regId);
    }
    @Override
    protected void onResume() {
        super.onResume();
        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));
        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));
        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }
    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
    private void errorDialog() {

        final Dialog dialog = new Dialog(Login_Actitivty.this,R.style.Theme_AppCompat_Dialog_Alert);
        dialog.setContentView(R.layout.error_dialog);
        dialog.setTitle("");
        // set the custom dialog components - text, image and button
        TextView text_toplabel = (TextView) dialog.findViewById(R.id.text_toplabel);
        TextView text_label = (TextView) dialog.findViewById(R.id.text_label);
        text_label.setText("Only for Admin Department");
        TextView text_message = (TextView) dialog.findViewById(R.id.text_message);
        text_message.setText("User Credentials are not Valid. Please Try Again.");
        text_toplabel.setTypeface(typeface);
        Button dialogButton = (Button) dialog.findViewById(R.id.button_closed);
        text_toplabel.setTypeface(typeface);
        text_label.setTypeface(typeface);
        text_message.setTypeface(typeface);
        dialogButton.setTypeface(typeface);
        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();


    }




    private class GetULR extends AsyncTask<String, String, Integer> {
        //   ProgressDialog progressDialog = new ProgressDialog(Login.this);
        @Override
        protected void onPreExecute() {
            // progressDialog = ProgressDialog.show(Login.this, "", "Please Wait...", true);
            super.onPreExecute();
        }
        @Override
        protected Integer doInBackground(String... strings) {
            int status=0;
            JsonParser josnparser=new JsonParser(getApplicationContext());
            Log.d("TAG", "doInBackground: "+applicationController.getServicesapplication()+ServerApi.GETURL_API+"////"+Paracode(school_code));
            String response=josnparser.executePost(applicationController.getServicesapplication()+ServerApi.GETURL_API,Paracode(school_code),"1");
            if(response!=null){
                if (response.length()>5){
                    try {
                        JSONArray jsonArray= new JSONArray(response);
                        JSONObject jsonobject = jsonArray.getJSONObject(0);
                        BranchWebsite=jsonobject.getString("BranchWebsite");
                        AVer=jsonobject.getString("AVer");
                        String BranchLat=jsonobject.getString("BranchLat");
                        String BranchLog=jsonobject.getString("BranchLog");
                        String BranchRedius=jsonobject.getString("BranchRedius");
                        applicationController.setBranchLat(BranchLat);
                        applicationController.setBranchLon(BranchLog);
                        applicationController.setBranchRedius(BranchRedius);
                        applicationController.setAppversion(AVer);
                        status=1;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        status=-1;
                    }
                }else{
                    status=-2;
                }
            }else{
                status=-2;
            }
            return status;
        }

        @Override
        protected void onPostExecute(Integer s) {
            super.onPostExecute(s);
            // progressDialog.dismiss();
            switch (s){
                case 1:
                    ServerApi.seturl("http://"+BranchWebsite);
                    applicationController.setAppversion(AVer);

                    break;
                case -2:

                    break;
                case -1:
                    Toast.makeText(getApplicationContext(),"Network Congestion! Please try Again",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }
    public String Paracode(String school_code){
        JSONObject jsonParam = new JSONObject();
        try {
            jsonParam.put("Action", "1");
            jsonParam.put("BranchCode", school_code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonParam.toString();
    }


//    private class Getservices extends AsyncTask<String, String, Integer> {
//        ProgressDialog progressDialog = new ProgressDialog(Login_Actitivty.this);
//        @Override
//        protected void onPreExecute() {
//            progressDialog = ProgressDialog.show(Login_Actitivty.this, "", "Please Wait...", true);
//            super.onPreExecute();
//        }
//        @Override
//        protected Integer doInBackground(String... strings) {
//        int status=0;
//            JsonParser josnparser=new JsonParser(getApplicationContext());
//            Log.d("TAG", "doInBackground: GetService"+ServerApi.API_SERV+"///"+Paraser(school_code));
//            String response=josnparser.executePost(ServerApi.API_SERV,Paraser(school_code),"1");
//            if(response!=null){
//                if (response.length()>5){
//                    try {
//                        JSONArray jsonArray= new JSONArray(response);
//                        JSONObject jsonobject = jsonArray.getJSONObject(1);
//                        Log.d("TAG", "BranchWebsiteservices :"+jsonobject.getString(ServerApi.API_SDKUrl));
//                        BranchWebsiteservices=jsonobject.getString(ServerApi.API_SDKUrl);
//                        status=1;
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        status=-1;
//                    }
//                }else{
//                    status=-1;
//                }
//            }else{
//                status=-1;
//            }
//            return status;
//        }
//
//        @Override
//        protected void onPostExecute(Integer s) {
//            super.onPostExecute(s);
//            progressDialog.dismiss();
//            switch (s){
//                case 1:
//                    applicationController.setServicesapplication("http://"+BranchWebsiteservices);
//                    new GetULR().execute();
//
//                    break;
//                case -2:
//
//                    break;
//                case -1:
//                    //  Toast.makeText(getApplicationContext(),"Network Congestion! Please try Again",Toast.LENGTH_LONG).show();
//                    break;
//            }
//        }
//    }
    public JsonObject Paraser(String school_code){
        JsonObject jsonParam1 = new JsonObject();
        JsonObject jsonParam = new JsonObject();
        try {
            jsonParam.addProperty(ServerApi.API_CONURL, school_code);
            jsonParam1.add("obj", jsonParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonParam1;
    }





}
