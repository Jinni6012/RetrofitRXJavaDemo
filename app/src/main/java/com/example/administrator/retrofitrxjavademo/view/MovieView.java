package com.example.administrator.retrofitrxjavademo.view;

import com.example.administrator.retrofitrxjavademo.bean.MovieBean;

/**
 * Created by Jinni on 2017/7/12 0012.
 */

public interface MovieView {
    /**
     * 网络请求开始时显示ProgressDialog
     */
    void showProgress();

    /**
     * 网络请求结束时隐藏ProgressDialog
     */
    void hideProgress();

    /**
     * 回调网络请求结果，解析实体类填充数据到View
     *
     * @param movieBean 电影信息实体
     */
    void getMovies(MovieBean movieBean);
}
