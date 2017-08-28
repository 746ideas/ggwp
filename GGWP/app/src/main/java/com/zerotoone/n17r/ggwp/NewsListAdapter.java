package com.zerotoone.n17r.ggwp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Мадияр on 17.08.2017.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>{
    private ArrayList<String> deque;
    private ArrayList<String> deque2;
    private ArrayList<String> deque3;
    private Context mContext;
    private String imageURL;
    final private NewsListAdapterOnClickHandler mClickHandler;
    private boolean toggle = false;

    public NewsListAdapter(Context context,ArrayList<String> deque, ArrayList<String> deque2, ArrayList<String> deque3, NewsListAdapterOnClickHandler mClickHandler){
        this.mContext=context;
        this.deque=deque;
        this.deque2=deque2;
        this.deque3=deque3;
        this.mClickHandler = mClickHandler;
    }
    public interface NewsListAdapterOnClickHandler{
        void onClick(int clickedItemIndex);
    }
    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.news_list_view, parent, false);

        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder holder, int position) {
        Log.e("onbindview", String.valueOf(position));
        if(deque.isEmpty()){
            return;
        }
        String text = deque.get(position);
        Log.e("text:", text);
        String url = deque2.get(position);
        imageURL = deque3.get(position);
        Picasso.with(mContext).load(imageURL).into(holder.mImageView);
        holder.mTextView.setText(text);
        holder.mTextView.getBackground().setAlpha(150);
        holder.button.setHeight(holder.mTextView.getHeight());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Animation slide_down = AnimationUtils.loadAnimation(mContext,
                            R.anim.slide_down);

                    Animation slide_up = AnimationUtils.loadAnimation(mContext,
                            R.anim.slide_up);
                    if (toggle) {
                        holder.mTextView.startAnimation(slide_up);
                        holder.mTextView.setVisibility(View.VISIBLE);
                    } else {
                        holder.mTextView.startAnimation(slide_down);
                        holder.mTextView.setVisibility(View.GONE);
                    }
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.e("Size:", String.valueOf(deque.size()));
        return deque.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;
        private ImageView mImageView;
        private Button button;
        public NewsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTextView = (TextView) itemView.findViewById(R.id.news_title);
            mImageView = (ImageView) itemView.findViewById(R.id.news_image);
            button = (Button) itemView.findViewById(R.id.button1);
        }

        @Override
        public void onClick(View v) {
                int position = getAdapterPosition();
                mClickHandler.onClick(position);
        }
    }

    public void setNewData(ArrayList<String> d1, ArrayList<String> d2, ArrayList<String> d3){
        deque=d1;
        deque2=d2;
        deque3=d3;
        notifyDataSetChanged();
        Log.e("razmer", String.valueOf(d1.size()));
    }

}
