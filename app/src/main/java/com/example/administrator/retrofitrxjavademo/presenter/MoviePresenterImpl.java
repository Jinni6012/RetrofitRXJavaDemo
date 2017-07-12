package com.example.administrator.retrofitrxjavademo.presenter;

/**
 * Created by Jinni on 2017/7/12 0012.
 */

interface MoviePresenterImpl {
    /**
     * 进行网络请求获取电影信息
     *
     * @param start 起始位置
     * @param count 请求数量
     */
    void getMovies(int start, int count);
}
