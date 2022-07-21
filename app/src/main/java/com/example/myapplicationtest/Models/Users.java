package com.example.myapplicationtest.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Users {
    @SerializedName("page")
    private long page;
    @SerializedName("per_page")
    private long perPage;
    @SerializedName("total")
    private long total;
    @SerializedName("total_pages")
    private long totalPages;
    @SerializedName("data")
    private List<User> data;
    @SerializedName("support")
    private Support support;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getPerPage() {
        return perPage;
    }

    public void setPerPage(long perPage) {
        this.perPage = perPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }
}
