package com.example.administrator.retrofitrxjavademo.model;

import rx.Subscription;

/**
 * Created by Jinni on 2017/7/11 0011.
 */

public interface MovieModelImpl {
    /**
     * @param start 查询起始页
     * @param count 查询数量
     * @return
     */
    Subscription getMovies(int start, int count);
}
