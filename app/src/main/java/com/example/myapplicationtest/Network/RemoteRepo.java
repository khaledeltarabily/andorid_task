package com.example.myapplicationtest.Network;


import com.example.myapplicationtest.AppSession;
import com.example.myapplicationtest.Network.remote.ApiEndpoints;
import com.example.myapplicationtest.Network.remote.LongTypeAdapter;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RemoteRepo {
    public static String Base_url = "https://reqres.in/api/";
    private static RemoteRepo RemoteRepo;
    public final ApiEndpoints endpoints;

    static Interceptor onlineInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            okhttp3.Response response = chain.proceed(chain.request());
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .build();
        }
    };

    static Interceptor offlineInterceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!AppSession.isNetworkAvailable_b() && !request.cacheControl().noCache()) {
                int maxStale = 60 * 60 * 24 * 30; // Offline cache available for 30 days
                request = request.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return chain.proceed(request);
        }
    };



    public RemoteRepo() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(offlineInterceptor)
                .addNetworkInterceptor(onlineInterceptor)
                .cache(AppSession.cache)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();
        ExclusionStrategy strategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes field) {
                return field.getAnnotation(Expose.class) != null;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        };

        Gson gson = new GsonBuilder()
                .setLenient()
                .registerTypeAdapter(Long.class, new LongTypeAdapter())
                .addSerializationExclusionStrategy(strategy)
                .create();

        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();


        endpoints = adapter.create(ApiEndpoints.class);

    }

    public static RemoteRepo RemoteRepoInst() {
        if (RemoteRepo == null) {
            RemoteRepo = new RemoteRepo();
        }
        return RemoteRepo;
    }
}
