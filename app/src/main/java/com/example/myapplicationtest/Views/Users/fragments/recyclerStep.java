package com.example.myapplicationtest.Views.Users.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.myapplicationtest.AppSession;
import com.example.myapplicationtest.Network.RemoteRepoOperations;
import com.example.myapplicationtest.R;
import com.example.myapplicationtest.Views.Users.adapter.usersAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;



public class recyclerStep extends Fragment {
    private RecyclerView usersRecycler;
    View view;

    private int page;

    public recyclerStep(int page) {
        this.page= page;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.recycler_step, null, false);

        usersRecycler = view.findViewById(R.id.usersRecycler);

        new RemoteRepoOperations().users(page,AppSession.perPage).subscribe(usersRes -> {
            AppSession.operations.hide();
            usersAdapter adapter = new usersAdapter(getContext(), usersRes.getData());
            usersRecycler.setAdapter(adapter);
        },throwable -> {
            AppSession.operations.showNetworkLost();
        });
        return view;
    }

}
