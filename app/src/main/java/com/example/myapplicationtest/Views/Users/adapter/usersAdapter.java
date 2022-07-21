package com.example.myapplicationtest.Views.Users.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplicationtest.Models.User;
import com.example.myapplicationtest.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class usersAdapter extends RecyclerView.Adapter<usersAdapter.MyViewHolder> {
    private final Context context;
    private final List<User> list;

    public usersAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User data = list.get(position);
        Glide.with(context).load(data.getAvatar()).into(holder.avatar);
        holder.firstName.setText(data.getFirstName());
        holder.lastName.setText(data.getLastName());
        holder.email.setText(data.getEmail());
        holder.bottomView.setVisibility(position==list.size()-1?View.VISIBLE:View.GONE);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addPage(List<User> data) {
        int preSize = this.list.size();
        this.list.addAll(data);
        notifyItemRangeInserted(preSize + 1, list.size());
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView firstName,lastName,email;
        public ImageView avatar;
        public View bottomView;

        public MyViewHolder(View itemView) {
            super(itemView);
            firstName=itemView.findViewById(R.id.firstName);
            lastName=itemView.findViewById(R.id.lastName);
            email=itemView.findViewById(R.id.email);
            avatar=itemView.findViewById(R.id.avatar);
            bottomView=itemView.findViewById(R.id.bottomView);
        }
    }
}