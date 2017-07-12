package com.example.administrator.retrofitrxjavademo.retrofit;

import com.example.administrator.retrofitrxjavademo.bean.MovieBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jinni on 2017/7/12 0012.
 */

interface ApiService {
    /**
     * 获取movie的数据
     *
     * @param start 起始位置
     * @param count 请求数量
     * @return movie数据
     */
    @GET("v2/movie/top250")
    Observable<MovieBean> getMovies(@Query("start") int start, @Query("count") int count);

}
