package com.bsninfotech.accountwell;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {

    TextView userDesignationTxt,userNameProfileTxt,userCompanyTxt,branchnameTxt,emailTxt,mobTxt,lastLoginTxt,addressTxt,departmentTxt;
    SharedPreferences sharedpreferences;
    CircleImageView image_student_dashboard;
    CircleImageView logoutBtn;
    ApplicationControllerAdmin applicationController;
    Toolbar toolbarUserProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        applicationController= (ApplicationControllerAdmin) getApplication();
        getSupportActionBar().hide();
        sharedpreferences = getSharedPreferences("APPDATA", Context.MODE_PRIVATE);
        userDesignationTxt=findViewById(R.id.userDesignationTxt);
        userNameProfileTxt=findViewById(R.id.userNameProfileTxt);
        branchnameTxt=findViewById(R.id.branchnameTxt);
        userCompanyTxt=findViewById(R.id.mobNumberTxt);
        addressTxt=findViewById(R.id.addressTxt);
        departmentTxt=findViewById(R.id.departmentTxt);
        lastLoginTxt=findViewById(R.id.textView10);
        emailTxt=findViewById(R.id.emailTxt);
        mobTxt=findViewById(R.id.mobTxt);
        image_student_dashboard=findViewById(R.id.image_student_dashboard);
        userCompanyTxt.setText(applicationController.getCompanyName());
        userNameProfileTxt.setText(applicationController.getuserName());
        userDesignationTxt.setText("Designation: "+applicationController.getDesignationId());
        emailTxt.setText(applicationController.getUserMail());
        mobTxt.setText(applicationController.getappusermobile());
        branchnameTxt.setText(applicationController.getBranchName());
        logoutBtn=findViewById(R.id.logoutBtn);
        toolbarUserProfile=findViewById(R.id.toolbarUserProfile);
        addressTxt.setText(applicationController.getAddress());
        departmentTxt.setText(applicationController.getDepartmentId());
        lastLoginTxt.setText("Last Login: "+sharedpreferences.getString("lastLogin","").toUpperCase());
        String imagerplace= "http://accountwellwebtest.bsninfotech.org"+applicationController.getuserProfile_logo();

        imagerplace=imagerplace.replace("..","");
        image_student_dashboard.setImageURI(Uri.parse(imagerplace));
        Glide.get(getApplicationContext()).clearMemory();
        Glide
                .with(getApplicationContext())
                .load(imagerplace)
                .placeholder(R.drawable.ic_dummyimage)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true))
                .into(image_student_dashboard);
        toolbarUserProfile.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(UserProfile.this)
                        .setIcon(R.drawable.accounwell_ic)
                        .setTitle("Logout AccountWell")
                        .setMessage("Are you sure you want to logout from this application?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences.Editor editor = sharedpreferences.edit();
                                editor.putString("userId_save", "");
                                editor.putString("Password_save","");
                                editor.putBoolean("checkbox",false);
                                editor.commit();
                                startActivity(new Intent(UserProfile.this,Login_Actitivty.class));
                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });


    }
}