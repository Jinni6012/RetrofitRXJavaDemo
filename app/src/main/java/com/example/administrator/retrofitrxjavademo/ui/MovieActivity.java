package com.example.administrator.retrofitrxjavademo.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.retrofitrxjavademo.R;
import com.example.administrator.retrofitrxjavademo.adapter.MovieAdapter;
import com.example.administrator.retrofitrxjavademo.bean.MovieBean;
import com.example.administrator.retrofitrxjavademo.presenter.MoviePresenter;
import com.example.administrator.retrofitrxjavademo.view.MovieView;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends AppCompatActivity implements MovieView {

    private List<MovieBean.SubjectsBean>    mMovieList;

    private Context                         mContext;
    private MoviePresenter                  mPresenter;
    private MovieAdapter                    mAdapter;

    private RecyclerView                    movieList;
    private Toolbar                         mToolbar;
    private ProgressDialog                  mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        mContext = this;
        initView();
        initToolbar();
        initRecyclerView();
        initPresenter();
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        movieList = (RecyclerView) findViewById(R.id.movie_list);
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
        mToolbar.setTitle("电影");
        mToolbar.setNavigationIcon(R.mipmap.arrow_left);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 初始化RecyclerView并填充数据
     */
    private void initRecyclerView() {
        mMovieList = new ArrayList<>();
        mAdapter = new MovieAdapter(mContext, mMovieList);
        movieList.setAdapter(mAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        movieList.setNestedScrollingEnabled(false);
        movieList.setLayoutManager(linearLayoutManager);
        //绘制item间的分割线，
        movieList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(2, 2, 2, 2);
            }
        });
    }

    private void initPresenter() {
        mPresenter = new MoviePresenter(this);
        mPresenter.getMovies(0, 10);
    }

    @Override
    public void showProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.show();
        } else {
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setMessage("正在加载中，请稍后...");
            mProgressDialog.setCanceledOnTouchOutside(false);
            mProgressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void getMovies(MovieBean movieBean) {
        mMovieList.addAll(movieBean.getSubjects());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onUnsubscribe();
        hideProgress();
        super.onDestroy();
    }
}
