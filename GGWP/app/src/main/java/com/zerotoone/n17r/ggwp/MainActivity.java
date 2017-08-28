package com.zerotoone.n17r.ggwp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity implements NewsListAdapter.NewsListAdapterOnClickHandler{
    private ArrayList<String> ArrayTitles = new ArrayList<>();
    private ArrayList<String> ArrayURLs = new ArrayList<>();
    private ArrayList<String> ArrayImageURLs = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mInfoTextView;
    private TextView mErrorMsg;
    private NewsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setLogo(R.drawable.image);
        RecyclerView recyclerView = (RecyclerView)this.findViewById(R.id.all_news_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mQuery();
        mAdapter = new NewsListAdapter(this, ArrayTitles, ArrayURLs, ArrayImageURLs , this);
        recyclerView.setAdapter(mAdapter);
    }


    public void mQuery(){
        URL Url = NetworkUtils.buildUrl();
        new QueryTask().execute(Url);
    }

    @Override
    public void onClick(int clickedItemIndex) {
        String URL = ArrayURLs.get(clickedItemIndex);
        Log.e("otpravleno:", URL);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(URL));
        startActivity(intent);
    }

    /*@Override
    public void onRefresh() {
        Toast.makeText(this, R.string.refresh_started, Toast.LENGTH_SHORT).show();
        // начинаем показывать прогресс
        mSwipeRefreshLayout.setRefreshing(true);
        // ждем 3 секунды и прячем прогресс
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
                // говорим о том, что собираемся закончить
                Toast.makeText(MainActivity.this, R.string.refresh_finished, Toast.LENGTH_SHORT).show();
            }
        }, 3000);

    }*/

    /*@Override
    public void onClick(int clickedItemIndex) {
        String URL = ArrayURLs.get(clickedItemIndex);
        Log.e("otpravleno:", URL);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(URL));
        startActivity(intent);
    }*/

    public class QueryTask extends AsyncTask<URL,Void, String> {
        String Results=null;
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected String doInBackground(URL... params) {
            JSONObject perevod1 = null;
            URL url = params[0];
            try {
                Results = NetworkUtils.getResponseFromHttpUrl(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                perevod1 = new JSONObject(Results);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            JSONArray perevod = null;
            try {
                perevod = perevod1.getJSONArray("items");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String imageUrl = null;
            for(int i=0; i<perevod.length(); i++){
                String description = null;
                try {
                    description = perevod.getJSONObject(i).getString("description").toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ArrayTitles.add(i, description);
                Log.e("title:", description);
                String guid = null;
                try {
                    guid = perevod.getJSONObject(i).getString("guid").toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.e("guid", guid);


                Log.e("guid", guid);
                ArrayURLs.add(i, guid);
                try {
                    imageUrl = Jsoup.connect(guid).get().select("meta[property=og:image]").attr("content");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ArrayImageURLs.add(i, imageUrl);
            }

            return Results;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            if(s==null){
                showError();
            }else {
                super.onPostExecute(s);
            }
            mAdapter.setNewData(ArrayTitles, ArrayURLs, ArrayImageURLs);
        }
        public void showData(){
            mInfoTextView.setVisibility(View.VISIBLE);
            mErrorMsg.setVisibility(View.INVISIBLE);
        }
        public void showError(){
            mInfoTextView.setVisibility(View.INVISIBLE);
            mErrorMsg.setVisibility(View.VISIBLE);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);//Menu Resource, Menu
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int mid = item.getItemId();
        if(mid == R.id.scores){
                Intent intent = new Intent(getApplicationContext(), LiveGamesActivity.class);
                startActivity(intent);
        }
        return true;
    }
    }


