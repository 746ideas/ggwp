package com.zerotoone.n17r.ggwp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Мадияр on 17.08.2017.
 */

public class LiveGamesActivity extends AppCompatActivity implements LiveGamesAdapter.LiveGamesAdapterOnClickHandler{
    private ViewGroup mContainerView;
    private ArrayList<MatchInfo> GameID = new ArrayList<>();
    HashMap<Integer, String> HeroNameID = new HashMap<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LiveGamesAdapter mLiveAdapter;
    private TextView mInfoTextView;
    private TextView mErrorMsg;

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livegames);

        RecyclerView recyclerView = (RecyclerView) this.findViewById(R.id.all_games_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setLogo(R.drawable.image);
        mLiveAdapter = new LiveGamesAdapter(this, GameID, this);
        recyclerView.setAdapter(mLiveAdapter);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String matchUrl = "http://api.steampowered.com/IDOTA2Match_570/GetLiveLeagueGames/v1/?key=C2206A4558AD929913C2DFA15E982A79";
        final String heroesImgUrl = "https://raw.githubusercontent.com/kronusme/dota2-api/master/data/heroes.json";

        JsonObjectRequest jsObjRequest1 = new JsonObjectRequest(Request.Method.GET, heroesImgUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray heroes = response.getJSONArray("heroes");
                    for(int i=0; i<heroes.length(); i++){
                        HeroNameID.put(heroes.getJSONObject(i).getInt("id"), heroes.getJSONObject(i).getString("name").toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        queue.add(jsObjRequest1);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, matchUrl, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                JSONArray perevod = null;

                try {
                    perevod = response.getJSONObject("result").getJSONArray("games");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < perevod.length(); i++) {
                    String[] playerID = new String[10];
                    String[] playerName = new String[10];
                    String[] playerScore = new String[10];
                    String[] heroName = new String[10];
                    String[] networth = new String[10];
                    String[] level = new String[10];
                    String[] gpmxpm = new String[10];
                    try {
                        if( perevod.getJSONObject(i).has("scoreboard") && perevod.getJSONObject(i).getJSONObject("scoreboard").
                                has("radiant") && perevod.getJSONObject(i).getJSONObject("scoreboard").
                                has("dire") && perevod.getJSONObject(i).
                                getJSONObject("scoreboard").getInt("duration")>0) {

                            for(int j=0; j<10; j++){
                                if(j<5) {
                                    playerScore[j] = String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("kills"))+ "/" +
                                            String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                                    getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("death")) + "/" +
                                            String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                                    getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("assists"));
                                    playerID[j] = String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("account_id"));
                                    heroName[j]= HeroNameID.get(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("hero_id"));
                                    networth[j]= String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("net_worth"));
                                    level[j]= String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("level"));
                                    gpmxpm[j] = String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("gold_per_min")) + "/" +
                                            String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                                    getJSONObject("radiant").getJSONArray("players").getJSONObject(j).getInt("xp_per_min"));
                                }else{
                                    playerScore[j] = String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("dire").getJSONArray("players").getJSONObject(j-5).getInt("kills"))+ "/" +
                                            String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                                    getJSONObject("dire").getJSONArray("players").getJSONObject(j-5).getInt("death")) + "/" +
                                            String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                                    getJSONObject("dire").getJSONArray("players").getJSONObject(j-5).getInt("assists"));
                                    playerID[j] = String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("dire").getJSONArray("players").getJSONObject(j-5).getInt("account_id"));
                                    heroName[j]= HeroNameID.get(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("dire").getJSONArray("players").getJSONObject(j-5).getInt("hero_id"));
                                    networth[j]= String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("dire").getJSONArray("players").getJSONObject(j-5).getInt("net_worth"));
                                    level[j]= String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("dire").getJSONArray("players").getJSONObject(j-5).getInt("level"));
                                    gpmxpm[j] = String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("dire").getJSONArray("players").getJSONObject(j-5).getInt("gold_per_min")) + "/" +
                                            String.valueOf(perevod.getJSONObject(i).getJSONObject("scoreboard").
                                                    getJSONObject("dire").getJSONArray("players").getJSONObject(j-5).getInt("xp_per_min"));
                                }
                            }

                            for(int k=0; k<perevod.getJSONObject(i).getJSONArray("players").length(); k++){
                                for(int j=0; j<10; j++){
                                    if(playerID[j].equals(String.valueOf(perevod.getJSONObject(i).getJSONArray("players").getJSONObject(k).getInt("account_id")))){
                                        playerName[j]=perevod.getJSONObject(i).getJSONArray("players").getJSONObject(k).getString("name").toString();
                                    }
                                }
                            }
                            if(perevod.getJSONObject(i).has("radiant_team")&& perevod.getJSONObject(i).has("dire_team")) {
                                    GameID.add(new MatchInfo(perevod.getJSONObject(i).getJSONObject("radiant_team")
                                            .getString("team_name").toString(), perevod.getJSONObject(i).getJSONObject("dire_team")
                                            .getString("team_name").toString(), perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("radiant").getString("score").toString(), perevod.getJSONObject(i).
                                            getJSONObject("scoreboard").getJSONObject("dire").getString("score").toString(),
                                            playerName[0], playerScore[0], playerName[1], playerScore[1], playerName[2], playerScore[2]
                                            , playerName[3], playerScore[3], playerName[4], playerScore[4], playerName[5], playerScore[5]
                                            , playerName[6], playerScore[6], playerName[7], playerScore[7], playerName[8], playerScore[8]
                                            , playerName[9], playerScore[9], heroName[0], heroName[1], heroName[2], heroName[3], heroName[4]
                                            , heroName[5], heroName[6], heroName[7], heroName[8], heroName[9]
                                            , networth[0], networth[1], networth[2], networth[3], networth[4], networth[5],
                                            networth[6], networth[7], networth[8], networth[9], level[0], level[1],
                                            level[2], level[3], level[4], level[5], level[6], level[7], level[8], level[9],
                                            gpmxpm[0], gpmxpm[1], gpmxpm[2], gpmxpm[3], gpmxpm[4], gpmxpm[5], gpmxpm[6], gpmxpm[7],
                                            gpmxpm[8], gpmxpm[9]));
                                }else if(perevod.getJSONObject(i).has("radiant_team")&& !perevod.getJSONObject(i).has("dire_team")) {
                                    GameID.add(new MatchInfo(perevod.getJSONObject(i).getJSONObject("radiant_team")
                                            .getString("team_name").toString(),"No Team Name", perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("radiant").getString("score").toString(), perevod.getJSONObject(i).
                                            getJSONObject("scoreboard").getJSONObject("dire").getString("score").toString(),
                                            playerName[0], playerScore[0], playerName[1], playerScore[1], playerName[2], playerScore[2]
                                            , playerName[3], playerScore[3], playerName[4], playerScore[4], playerName[5], playerScore[5]
                                            , playerName[6], playerScore[6], playerName[7], playerScore[7], playerName[8], playerScore[8]
                                            , playerName[9], playerScore[9], heroName[0], heroName[1], heroName[2], heroName[3], heroName[4]
                                            , heroName[5], heroName[6], heroName[7], heroName[8], heroName[9]
                                            , networth[0], networth[1], networth[2], networth[3], networth[4], networth[5],
                                            networth[6], networth[7], networth[8], networth[9], level[0], level[1],
                                            level[2], level[3], level[4], level[5], level[6], level[7], level[8], level[9],
                                            gpmxpm[0], gpmxpm[1], gpmxpm[2], gpmxpm[3], gpmxpm[4], gpmxpm[5], gpmxpm[6], gpmxpm[7],
                                            gpmxpm[8], gpmxpm[9]));
                                }else if(!perevod.getJSONObject(i).has("radiant_team")&& perevod.getJSONObject(i).has("dire_team")) {
                                    GameID.add(new MatchInfo("No Team Name", perevod.getJSONObject(i).getJSONObject("dire_team")
                                            .getString("team_name").toString(), perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("radiant").getString("score").toString(), perevod.getJSONObject(i).
                                            getJSONObject("scoreboard").getJSONObject("dire").getString("score").toString(),
                                            playerName[0], playerScore[0], playerName[1], playerScore[1], playerName[2], playerScore[2]
                                            , playerName[3], playerScore[3], playerName[4], playerScore[4], playerName[5], playerScore[5]
                                            , playerName[6], playerScore[6], playerName[7], playerScore[7], playerName[8], playerScore[8]
                                            , playerName[9], playerScore[9], heroName[0], heroName[1], heroName[2], heroName[3], heroName[4]
                                            , heroName[5], heroName[6], heroName[7], heroName[8], heroName[9]
                                            , networth[0], networth[1], networth[2], networth[3], networth[4], networth[5],
                                            networth[6], networth[7], networth[8], networth[9], level[0], level[1],
                                            level[2], level[3], level[4], level[5], level[6], level[7], level[8], level[9],
                                            gpmxpm[0], gpmxpm[1], gpmxpm[2], gpmxpm[3], gpmxpm[4], gpmxpm[5], gpmxpm[6], gpmxpm[7],
                                            gpmxpm[8], gpmxpm[9]));
                                }else{
                                    GameID.add(new MatchInfo("No Team Name", "No Team Name", perevod.getJSONObject(i).getJSONObject("scoreboard").
                                            getJSONObject("radiant").getString("score").toString(), perevod.getJSONObject(i).
                                            getJSONObject("scoreboard").getJSONObject("dire").getString("score").toString(),
                                            playerName[0], playerScore[0], playerName[1], playerScore[1], playerName[2], playerScore[2]
                                            , playerName[3], playerScore[3], playerName[4], playerScore[4], playerName[5], playerScore[5]
                                            , playerName[6], playerScore[6], playerName[7], playerScore[7], playerName[8], playerScore[8]
                                            , playerName[9], playerScore[9], heroName[0], heroName[1], heroName[2], heroName[3], heroName[4]
                                            , heroName[5], heroName[6], heroName[7], heroName[8], heroName[9]
                                            , networth[0], networth[1], networth[2], networth[3], networth[4], networth[5],
                                            networth[6], networth[7], networth[8], networth[9], level[0], level[1],
                                            level[2], level[3], level[4], level[5], level[6], level[7], level[8], level[9],
                                            gpmxpm[0], gpmxpm[1], gpmxpm[2], gpmxpm[3], gpmxpm[4], gpmxpm[5], gpmxpm[6], gpmxpm[7],
                                            gpmxpm[8], gpmxpm[9]));
                                }



                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mLiveAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        queue.add(jsObjRequest);

    }


    @Override
    public void onClick(int clickedItemIndex) {

    }

    public static class MatchInfo {
        String TeamName1;
        String TeamName2;
        String Score1;
        String Score2;
        String Player1Name;
        String Player2Name;
        String Player3Name;
        String Player4Name;
        String Player5Name;
        String Player6Name;
        String Player7Name;
        String Player8Name;
        String Player9Name;
        String Player10Name;
        String Player1Stats;
        String Player2Stats;
        String Player3Stats;
        String Player4Stats;
        String Player5Stats;
        String Player6Stats;
        String Player7Stats;
        String Player8Stats;
        String Player9Stats;
        String Player10Stats;
        String Player1Hero;
        String Player2Hero;
        String Player3Hero;
        String Player4Hero;
        String Player5Hero;
        String Player6Hero;
        String Player7Hero;
        String Player8Hero;
        String Player9Hero;
        String Player10Hero;
        String networth1;
        String networth2;
        String networth3;
        String networth4;
        String networth5;
        String networth6;
        String networth7;
        String networth8;
        String networth9;
        String networth10;
        String level1;
        String level2;
        String level3;
        String level4;
        String level5;
        String level6;
        String level7;
        String level8;
        String level9;
        String level10;
        String gmpxpm1;
        String gmpxpm2;
        String gmpxpm3;
        String gmpxpm4;
        String gmpxpm5;
        String gmpxpm6;
        String gmpxpm7;
        String gmpxpm8;
        String gmpxpm9;
        String gmpxpm10;
        public MatchInfo(String TeamName1,
                         String TeamName2,
                         String Score1,
                         String Score2,
                         String Player1Name,
                         String Player1Stats,
                         String Player2Name,
                         String Player2Stats,
                         String Player3Name,
                         String Player3Stats,
                         String Player4Name,
                         String Player4Stats,
                         String Player5Name,
                         String Player5Stats,
                         String Player6Name,
                         String Player6Stats,
                         String Player7Name,
                         String Player7Stats,
                         String Player8Name,
                         String Player8Stats,
                         String Player9Name,
                         String Player9Stats,
                         String Player10Name,
                         String Player10Stats,
                         String Player1Hero,
                         String Player2Hero,
                         String Player3Hero,
                         String Player4Hero,
                         String Player5Hero,
                         String Player6Hero,
                         String Player7Hero,
                         String Player8Hero,
                         String Player9Hero,
                         String Player10Hero,
                         String networth1,
                         String networth2,
                         String networth3,
                         String networth4,
                         String networth5,
                         String networth6,
                         String networth7,
                         String networth8,
                         String networth9,
                         String networth10,
                         String level1,
                         String level2,
                         String level3,
                         String level4,
                         String level5,
                         String level6,
                         String level7,
                         String level8,
                         String level9,
                         String level10,
                         String gmpxpm1,
                         String gmpxpm2,
                         String gmpxpm3,
                         String gmpxpm4,
                         String gmpxpm5,
                         String gmpxpm6,
                         String gmpxpm7,
                         String gmpxpm8,
                         String gmpxpm9,
                         String gmpxpm10) {
            this.TeamName1 = TeamName1;
            this.TeamName2 = TeamName2;
            this.Score1 = Score1;
            this.Score2 = Score2;
            this.Player1Name = Player1Name;
            this.Player2Name = Player2Name;
            this.Player3Name = Player3Name;
            this.Player4Name = Player4Name;
            this.Player5Name = Player5Name;
            this.Player6Name = Player6Name;
            this.Player7Name = Player7Name;
            this.Player8Name = Player8Name;
            this.Player9Name = Player9Name;
            this.Player10Name = Player10Name;
            this.Player1Stats = Player1Stats;
            this.Player2Stats = Player2Stats;
            this.Player3Stats = Player3Stats;
            this.Player4Stats = Player4Stats;
            this.Player5Stats = Player5Stats;
            this.Player6Stats = Player6Stats;
            this.Player7Stats = Player7Stats;
            this.Player8Stats = Player8Stats;
            this.Player9Stats = Player9Stats;
            this.Player10Stats = Player10Stats;
            this.Player1Hero = Player1Hero;
            this.Player2Hero = Player2Hero;
            this.Player3Hero = Player3Hero;
            this.Player4Hero = Player4Hero;
            this.Player5Hero = Player5Hero;
            this.Player6Hero = Player6Hero;
            this.Player7Hero = Player7Hero;
            this.Player8Hero = Player8Hero;
            this.Player9Hero = Player9Hero;
            this.Player10Hero = Player10Hero;
            this.networth1 = networth1;
            this.networth2 = networth2;
            this.networth3 = networth3;
            this.networth4 = networth4;
            this.networth5 = networth5;
            this.networth6 = networth6;
            this.networth7 = networth7;
            this.networth8 = networth8;
            this.networth9 = networth9;
            this.networth10 = networth10;
            this.level1 = level1;
            this.level2 = level2;
            this.level3 = level3;
            this.level4 = level4;
            this.level5 = level5;
            this.level6 = level6;
            this.level7 = level7;
            this.level8 = level8;
            this.level9 = level9;
            this.level10 = level10;
            this.gmpxpm1 = gmpxpm1;
            this.gmpxpm2 = gmpxpm2;
            this.gmpxpm3 = gmpxpm3;
            this.gmpxpm4 = gmpxpm4;
            this.gmpxpm5 = gmpxpm5;
            this.gmpxpm6 = gmpxpm6;
            this.gmpxpm7 = gmpxpm7;
            this.gmpxpm8 = gmpxpm8;
            this.gmpxpm9 = gmpxpm9;
            this.gmpxpm10 = gmpxpm10;
        }

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu2, menu);//Menu Resource, Menu
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int mid = item.getItemId();
        if(mid == R.id.news){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        return true;
    }
}
