package com.example.administrator.retrofitrxjavademo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.retrofitrxjavademo.R;
import com.example.administrator.retrofitrxjavademo.bean.MovieBean;

import java.util.List;

/**
 * Created by Jinni on 2017/7/12 0012.
 */

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieBean.SubjectsBean>        mList;
    private Context                             mContext;

    public MovieAdapter(Context mContext, List<MovieBean.SubjectsBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_movie_info, null);
        return new itemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MovieBean.SubjectsBean subjectsBean = mList.get(position);
        Glide.with(mContext)
                .load(subjectsBean.getImages().getLarge())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(300, 450)
                .centerCrop()
                .into(((itemViewHolder) holder).movieImage);
        ((itemViewHolder) holder).movieTitle.setText(subjectsBean.getTitle());
        ((itemViewHolder) holder).movieType.setText(String.format("类型：%s", subjectsBean.getGenres().toString()));
        StringBuilder casts = new StringBuilder();
        casts.append("主演：");
        for (MovieBean.SubjectsBean.CastsBean castsBean : subjectsBean.getCasts()) {
            casts.append(castsBean.getName()).append("/");
        }
        casts.append("...");
        ((itemViewHolder) holder).movieCasts.setText(casts);
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        } else {
            return 0;
        }
    }

    private class itemViewHolder extends RecyclerView.ViewHolder {

        private ImageView movieImage;
        private TextView movieTitle;
        private TextView movieType;
        private TextView movieCasts;

        private itemViewHolder(View itemView) {
            super(itemView);
            movieImage = itemView.findViewById(R.id.movie_pic);
            movieTitle = itemView.findViewById(R.id.movie_title);
            movieType = itemView.findViewById(R.id.movie_type);
            movieCasts = itemView.findViewById(R.id.movie_casts);
        }
    }
}
