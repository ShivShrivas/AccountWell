package com.bsninfotech.accountwell.Adapter;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bsninfotech.accountwell.Credit_Frag;
import com.bsninfotech.accountwell.Debit_Frag;

public class CreditorAdapter extends FragmentPagerAdapter {
    int tabCount;

    public CreditorAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new Credit_Frag();
            case 1: return new Debit_Frag();
            default:return null;

        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
