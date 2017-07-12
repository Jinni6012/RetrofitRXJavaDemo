package com.example.administrator.retrofitrxjavademo.model;

import android.util.Log;

import com.example.administrator.retrofitrxjavademo.bean.MovieBean;
import com.example.administrator.retrofitrxjavademo.retrofit.ApiServiceManager;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jinni on 2017/7/11 0011.
 */

public class MovieModel implements MovieModelImpl {

    private static final String     BASE_MOVIES     = "https://api.douban.com/";
    private static final String     TAG             = "MovieModel";

    private OnMoviesListener        onMoviesListener;

    public MovieModel(OnMoviesListener onMoviesListener) {
        this.onMoviesListener = onMoviesListener;
    }

    @Override
    public Subscription getMovies(int start, int count) {
        Observable<MovieBean> movies = ApiServiceManager.getMovies(BASE_MOVIES, start, count);
        return movies.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieBean>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG, "------------------ onCompleted ------------------");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (onMoviesListener != null) {
                            onMoviesListener.onFailure(e);
                        }
                    }

                    @Override
                    public void onNext(MovieBean movieBean) {
                        if (onMoviesListener != null) {
                            onMoviesListener.onSuccess(movieBean);
                        }
                    }
                });
    }

    public interface OnMoviesListener {
        void onSuccess(MovieBean movieBean);
        void onFailure(Throwable throwable);
    }
}
