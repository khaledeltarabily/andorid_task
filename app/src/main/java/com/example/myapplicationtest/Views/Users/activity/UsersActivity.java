package com.example.myapplicationtest.Views.Users.activity;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapplicationtest.AppSession;
import com.example.myapplicationtest.Network.RemoteRepoOperations;
import com.example.myapplicationtest.R;
import com.example.myapplicationtest.Views.Users.fragments.recyclerStep;
import com.example.myapplicationtest.Views.Users.utils.viewPagerAdapter;
import com.example.myapplicationtest.Views.interfaces.progressBarOperations;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import static com.example.myapplicationtest.AppSession.perPage;


public class UsersActivity extends AppCompatActivity implements progressBarOperations {
    private RelativeLayout progress;
    private DotsIndicator dots_indicator;
    private ViewPager viewpager;

    private viewPagerAdapter adapter;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users);

        progress = findViewById(R.id.progress);
        viewpager=findViewById(R.id.viewpager);
        dots_indicator=findViewById(R.id.dots_indicator);

        AppSession.setProgressBarOperations(this);

        new RemoteRepoOperations().users(1,perPage).subscribe(usersRes -> {
            hide();
            adapter = new viewPagerAdapter(getSupportFragmentManager());
            for (int i = 1; i <= usersRes.getTotalPages(); i++) {
                adapter.addFragment(new recyclerStep(i));
            }
            viewpager.setAdapter(adapter);
            dots_indicator.setViewPager(viewpager);
        },throwable -> {
            showNetworkLost();
        });

    }

    public void showDialog() {
        progress.setVisibility(View.VISIBLE);
    }

    public void hideDialog() {
        progress.setVisibility(View.GONE);
    }

    @Override
    public void show() {
        showDialog();
    }

    @Override
    public void hide() {
        hideDialog();
    }

    @Override
    public void showNetworkLost() {
        Toast.makeText(UsersActivity.this, "Some Thing Went Wrong.", Toast.LENGTH_SHORT).show();
        hideDialog();
    }

}