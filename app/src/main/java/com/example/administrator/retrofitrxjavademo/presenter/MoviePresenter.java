package com.example.administrator.retrofitrxjavademo.presenter;

import com.example.administrator.retrofitrxjavademo.bean.MovieBean;
import com.example.administrator.retrofitrxjavademo.model.MovieModel;
import com.example.administrator.retrofitrxjavademo.model.MovieModelImpl;
import com.example.administrator.retrofitrxjavademo.view.MovieView;

/**
 * Created by Jinni on 2017/7/12 0012.
 */

public class MoviePresenter extends BasePresenter implements MoviePresenterImpl, MovieModel.OnMoviesListener {

    private MovieView               movieView;
    private MovieModelImpl          movieModel;

    public MoviePresenter(MovieView movieView) {
        this.movieView = movieView;
        movieModel = new MovieModel(this);
    }

    @Override
    public void getMovies(int start, int count) {
        movieView.showProgress();
        addSubscription(movieModel.getMovies(start, count));
    }

    @Override
    public void onSuccess(MovieBean movieBean) {
        movieView.hideProgress();
        movieView.getMovies(movieBean);
    }

    @Override
    public void onFailure(Throwable throwable) {
        movieView.hideProgress();
    }
}
