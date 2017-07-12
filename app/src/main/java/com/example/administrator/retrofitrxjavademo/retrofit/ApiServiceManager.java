package com.example.administrator.retrofitrxjavademo.retrofit;

import com.example.administrator.retrofitrxjavademo.bean.MovieBean;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Jinni on 2017/7/11 0011.
 */

public class ApiServiceManager {

    private static Retrofit createRetrofit(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public static Observable<MovieBean> getMovies(String baseUrl, int start, int count) {
        Retrofit retrofit = createRetrofit(baseUrl);
        ApiService apiService = retrofit.create(ApiService.class);
        return apiService.getMovies(start, count);
    }
}
