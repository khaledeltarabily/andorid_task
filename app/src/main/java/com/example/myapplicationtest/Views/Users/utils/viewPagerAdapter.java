package com.example.myapplicationtest.Views.Users.utils;


import com.example.myapplicationtest.Views.Users.fragments.recyclerStep;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class viewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentList_title = new ArrayList<>();

    public viewPagerAdapter(FragmentManager manager) {
        super(manager);
    }
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentList_title.get(position);
    }
    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
    }
    public void replaceFragment(int i, recyclerStep recyclerStep) {
        mFragmentList.set(i,recyclerStep);
        notifyDataSetChanged();
    }
}