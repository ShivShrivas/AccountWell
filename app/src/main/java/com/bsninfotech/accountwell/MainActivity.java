package com.bsninfotech.accountwell;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bsninfotech.accountwell.Const.TypefaceUtil;
import com.bsninfotech.accountwell.Model.ServerApi;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.1F);
    BottomSheetDialog dialog;
    ApplicationControllerAdmin applicationController;
    Typeface typeFace;
    Animation slideUp;
    CircleImageView profileImage,notification;
    TextView lastLogin,designationTxt,textUserName,companyNameTxt,currentDate_in_mainActivity;
    SharedPreferences sharedpreferences;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.accounwell_ic)
                .setTitle("Closing AccountWell")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applicationController= (ApplicationControllerAdmin) getApplication();
        try {
            if (applicationController.getUserID() == null) {
                finish();
            }
        }catch (Exception e){
            finish();
        }

        getSupportActionBar().hide();
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(0x00000000);  // transparent
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            window.addFlags(flags);
        }
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//        TypefaceUtil fontChanger = new TypefaceUtil(getAssets(), "fonts/" + ServerApi.FONT_DASHBOARD);
//        fontChanger.replaceFonts((RelativeLayout)findViewById(R.id.layout_dashboard));
//        typeFace = Typeface.createFromAsset(getAssets(),
//                "fonts/FuturaBookfont.ttf");

        calendar = Calendar.getInstance();
        profileImage=findViewById(R.id.image_student_dashboard);
        notification=findViewById(R.id.notification);
        sharedpreferences = getSharedPreferences("APPDATA", Context.MODE_PRIVATE);
        slideUp = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up);
        lastLogin=findViewById(R.id.lastLogin);
        designationTxt=findViewById(R.id.designationTxt);
        textUserName=findViewById(R.id.textUserName);
        companyNameTxt=findViewById(R.id.companyNameTxt);
        currentDate_in_mainActivity=findViewById(R.id.currentDate_in_mainActivity);
        LinearLayout layout_turnover = findViewById(R.id.layout_turnover);
        textUserName.setText(applicationController.getuserName());
        lastLogin.setText("Last Login: "+sharedpreferences.getString("lastLogin","").toUpperCase());
        designationTxt.setText("Designation: "+applicationController.getDesignationId());
        dateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa" );
        currentDate_in_mainActivity.setText(dateFormat.format(calendar.getTime()).toUpperCase());
        String imagerplace= "http://accountwellwebtest.bsninfotech.org"+applicationController.getuserProfile_logo();
        imagerplace=imagerplace.replace("..","");
        profileImage.setImageURI(Uri.parse(imagerplace));
        Glide.get(getApplicationContext()).clearMemory();
        Glide
                .with(getApplicationContext())
                .load(imagerplace)
                .placeholder(R.drawable.ic_dummyimage)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(profileImage);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Notifications.class));
            }
        });
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,UserProfile.class));
            }
        });
        layout_turnover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Transactions_Summary.class);
                startActivity(intent);
            }
        });
        LinearLayout layout_cashsummary = findViewById(R.id.layout_cashsummary);
        layout_cashsummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccountSummary.class);
                intent.putExtra("action","10");
                intent.putExtra("name","Account Summary");
                startActivity(intent);
            }
        });
        LinearLayout creditor_summury = findViewById(R.id.creditor_summury);
        creditor_summury.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Creditor_Summary.class);
                startActivity(intent);
            }
        });
        LinearLayout layout_banksummar = findViewById(R.id.layout_banksummar);
        layout_banksummar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AccountSummary.class);
                intent.putExtra("action","11");
                intent.putExtra("name","Bank Summary");

                startActivity(intent);
            }
        });
        LinearLayout layout_stocksummar = findViewById(R.id.layout_stocksummar);
        layout_stocksummar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Stock_Summary_new.class);
                startActivity(intent);
            }
        });
        LinearLayout layout_analyticreport = findViewById(R.id.layout_analyticreport);
        layout_analyticreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Analytic_Report.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Sorry!, this is not working now...", Toast.LENGTH_SHORT).show();

            }
        });
        LinearLayout layout_salereport = findViewById(R.id.layout_salereportdashboard);
        layout_salereport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Sale_Reports.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Sorry!, this is not working now...", Toast.LENGTH_SHORT).show();

            }
        });
        LinearLayout layout_purchase = findViewById(R.id.layout_purchase);
        layout_purchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Purchase_Reports.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Sorry!, this is not working now...", Toast.LENGTH_SHORT).show();

            }
        });
        LinearLayout layout_labpayment = findViewById(R.id.layout_labpayment);
        layout_labpayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Labourpayment_Report.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Sorry!, this is not working now...", Toast.LENGTH_SHORT).show();

            }
        });
        LinearLayout Account_Reports = findViewById(R.id.layout_AccountReports);
        Account_Reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, Account_Reports.class);
//                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Sorry!, this is not working now...", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayout more_setting = findViewById(R.id.layout_more_setting);
        more_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoreDialog();
            }
        });
    }
    private void MoreDialog(){
        View view = getLayoutInflater().inflate(R.layout.referral_dialoglayout, null);
        dialog = new BottomSheetDialog(this);
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.show();
        TextView tv_ref = view.findViewById(R.id.tv_ref);
        TextView tv_ref1 =view.findViewById(R.id.tv_ref1);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
       // Button submit_code = (Button) view.findViewById(R.id.button_refcodesubmit);
        tv_ref.setTypeface(typeFace);
        tv_ref1.setTypeface(typeFace);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
