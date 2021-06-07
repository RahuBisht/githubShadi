package com.example.networking.apiendpoints;

import androidx.databinding.library.BuildConfig;


import com.example.networking.HttpProfiler;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestConfig {

    private static Retrofit Retrofit = null;

    public static final String BASE_URL = "https://randomuser.me/";

    public static Retrofit getClient() {
        Retrofit = null;
        if (Retrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            if (BuildConfig.DEBUG)
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            else
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

            if (HttpProfiler.getInstance().needHttpProfiler()) {
                if (BuildConfig.DEBUG) {
                    httpClient.addInterceptor(HttpProfiler.getInstance().getOkHttpProfilerInterceptor());
                }
            }

            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder();
                       // .addHeader("APP-KEY", APP_KEY);
                Request request = requestBuilder.build();

                return chain.proceed(request);
            });

            httpClient.readTimeout(120, TimeUnit.SECONDS)
                    .writeTimeout(120, TimeUnit.SECONDS)
                    .connectTimeout(120, TimeUnit.SECONDS)
                    .cache(null)
                    .addInterceptor(loggingInterceptor);

            OkHttpClient client = httpClient.build();


            Retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
        return Retrofit;
    }}


