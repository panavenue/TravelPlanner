package com.laioffer.travelplanner.retrofit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //replace it with your API key
    private static final String API = "AIzaSyD-qkmojsCbUX5W1f-jnIphjS4L_fAVxa4";
    //base url
    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/";
    private static Retrofit instance;

    //singleton constructor
    public static Retrofit getInstance() {
        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build();
        }
        return instance;
    }


    private static OkHttpClient getHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new PlacesInterceptor())
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);
        return builder.build();
    }

    private static class PlacesInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request original = chain.request();
            Request request = original
                    .newBuilder()
                    .header("X-Api-Key", API)
                    .build();

            return chain.proceed(request);
        }
    }
}

