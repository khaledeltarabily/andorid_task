package com.example.myapplicationtest.Network.remote;


import com.example.myapplicationtest.Models.Users;

import io.reactivex.Observable;


import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiEndpoints {

    @GET("users")
    @Headers({"Accept: application/json",
            "Content-Type: application/json"})
    Observable<Users> users(@Query("page") long page,
                            @Query("per_page") long per_page);

}