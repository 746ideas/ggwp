package com.zerotoone.n17r.ggwp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ImageTransformations.CircularTransformation;

public class LiveGamesAdapter extends RecyclerView.Adapter<LiveGamesAdapter.LiveGamesViewHolder>{
    private ArrayList<LiveGamesActivity.MatchInfo> mGamesID;
    private Context mContext;
    final private LiveGamesAdapterOnClickHandler mClickHandler;
    private boolean showingFirst = true;
    public LiveGamesAdapter(Context context, ArrayList<LiveGamesActivity.MatchInfo> GamesID, LiveGamesAdapterOnClickHandler mClickHandler){
        this.mContext = context;
        this.mGamesID = GamesID;
        this.mClickHandler = mClickHandler;
    }
    public interface LiveGamesAdapterOnClickHandler{
        void onClick(int clickedItemIndex);
    }
    @Override
    public LiveGamesAdapter.LiveGamesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.games_list_view, parent, false);

        return new LiveGamesAdapter.LiveGamesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LiveGamesAdapter.LiveGamesViewHolder holder, int position) {
        Log.e("onbindview", String.valueOf(position));
        if(mGamesID.isEmpty()){
            return;
        }
        String textname1 = mGamesID.get(position).TeamName1;
        String textname2 = mGamesID.get(position).TeamName2;
        String textscore = mGamesID.get(position).Score1 + ":" + mGamesID.get(position).Score2;
        String player1Name = mGamesID.get(position).Player1Name;
        String player2Name = mGamesID.get(position).Player2Name;
        String player3Name = mGamesID.get(position).Player3Name;
        String player4Name = mGamesID.get(position).Player4Name;
        String player5Name = mGamesID.get(position).Player5Name;
        String player6Name = mGamesID.get(position).Player6Name;
        String player7Name = mGamesID.get(position).Player7Name;
        String player8Name = mGamesID.get(position).Player8Name;
        String player9Name = mGamesID.get(position).Player9Name;
        String player10Name = mGamesID.get(position).Player10Name;

        String player1Stats = mGamesID.get(position).Player1Stats;
        String player2Stats = mGamesID.get(position).Player2Stats;
        String player3Stats = mGamesID.get(position).Player3Stats;
        String player4Stats = mGamesID.get(position).Player4Stats;
        String player5Stats = mGamesID.get(position).Player5Stats;
        String player6Stats = mGamesID.get(position).Player6Stats;
        String player7Stats = mGamesID.get(position).Player7Stats;
        String player8Stats = mGamesID.get(position).Player8Stats;
        String player9Stats = mGamesID.get(position).Player9Stats;
        String player10Stats = mGamesID.get(position).Player10Stats;

        String hero1 = mGamesID.get(position).Player1Hero;
        String hero2 = mGamesID.get(position).Player2Hero;
        String hero3 = mGamesID.get(position).Player3Hero;
        String hero4 = mGamesID.get(position).Player4Hero;
        String hero5 = mGamesID.get(position).Player5Hero;
        String hero6 = mGamesID.get(position).Player6Hero;
        String hero7 = mGamesID.get(position).Player7Hero;
        String hero8 = mGamesID.get(position).Player8Hero;
        String hero9 = mGamesID.get(position).Player9Hero;
        String hero10 = mGamesID.get(position).Player10Hero;

        String hero1imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + mGamesID.get(position).Player1Hero + "_sb.png";
        String hero2imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + mGamesID.get(position).Player2Hero + "_sb.png";
        String hero3imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + mGamesID.get(position).Player3Hero + "_sb.png";
        String hero4imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + mGamesID.get(position).Player4Hero + "_sb.png";
        String hero5imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + mGamesID.get(position).Player5Hero + "_sb.png";
        String hero6imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + mGamesID.get(position).Player6Hero + "_sb.png";
        String hero7imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + mGamesID.get(position).Player7Hero + "_sb.png";
        String hero8imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + mGamesID.get(position).Player8Hero + "_sb.png";
        String hero9imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + mGamesID.get(position).Player9Hero + "_sb.png";
        String hero10imageUrl = "http://cdn.dota2.com/apps/dota2/images/heroes/" + mGamesID.get(position).Player10Hero + "_sb.png";

        String networth1= mGamesID.get(position).networth1;
        String networth2= mGamesID.get(position).networth2;
        String networth3= mGamesID.get(position).networth3;
        String networth4= mGamesID.get(position).networth4;
        String networth5= mGamesID.get(position).networth5;
        String networth6= mGamesID.get(position).networth6;
        String networth7= mGamesID.get(position).networth7;
        String networth8= mGamesID.get(position).networth8;
        String networth9= mGamesID.get(position).networth9;
        String networth10= mGamesID.get(position).networth10;

        String level1 = mGamesID.get(position).level1;
        String level2 = mGamesID.get(position).level2;
        String level3 = mGamesID.get(position).level3;
        String level4 = mGamesID.get(position).level4;
        String level5 = mGamesID.get(position).level5;
        String level6 = mGamesID.get(position).level6;
        String level7 = mGamesID.get(position).level7;
        String level8 = mGamesID.get(position).level8;
        String level9 = mGamesID.get(position).level9;
        String level10 = mGamesID.get(position).level10;

        String gpmxpm1=mGamesID.get(position).gmpxpm1;
        String gpmxpm2=mGamesID.get(position).gmpxpm2;
        String gpmxpm3=mGamesID.get(position).gmpxpm3;
        String gpmxpm4=mGamesID.get(position).gmpxpm4;
        String gpmxpm5=mGamesID.get(position).gmpxpm5;
        String gpmxpm6=mGamesID.get(position).gmpxpm6;
        String gpmxpm7=mGamesID.get(position).gmpxpm7;
        String gpmxpm8=mGamesID.get(position).gmpxpm8;
        String gpmxpm9=mGamesID.get(position).gmpxpm9;
        String gpmxpm10=mGamesID.get(position).gmpxpm10;

        holder.TeamName1.setText(textname1);
        holder.TeamName2.setText(textname2);
        holder.Score.setText(textscore);

        holder.playerName1.setText(player1Name);
        holder.playerName2.setText(player2Name);
        holder.playerName3.setText(player3Name);
        holder.playerName4.setText(player4Name);
        holder.playerName5.setText(player5Name);
        holder.playerName6.setText(player6Name);
        holder.playerName7.setText(player7Name);
        holder.playerName8.setText(player8Name);
        holder.playerName9.setText(player9Name);

        holder.playerName10.setText(player10Name);
        holder.playerScore1.setText(player1Stats);
        holder.playerScore2.setText(player2Stats);
        holder.playerScore3.setText(player3Stats);
        holder.playerScore4.setText(player4Stats);
        holder.playerScore5.setText(player5Stats);
        holder.playerScore6.setText(player6Stats);
        holder.playerScore7.setText(player7Stats);
        holder.playerScore8.setText(player8Stats);
        holder.playerScore9.setText(player9Stats);
        holder.playerScore10.setText(player10Stats);

        holder.hero1.setText(hero1);
        holder.hero2.setText(hero2);
        holder.hero3.setText(hero3);
        holder.hero4.setText(hero4);
        holder.hero5.setText(hero5);
        holder.hero6.setText(hero6);
        holder.hero7.setText(hero7);
        holder.hero8.setText(hero8);
        holder.hero9.setText(hero9);
        holder.hero10.setText(hero10);

        holder.networth1.setText(networth1);
        holder.networth2.setText(networth2);
        holder.networth3.setText(networth3);
        holder.networth4.setText(networth4);
        holder.networth5.setText(networth5);
        holder.networth6.setText(networth6);
        holder.networth7.setText(networth7);
        holder.networth8.setText(networth8);
        holder.networth9.setText(networth9);
        holder.networth10.setText(networth10);

        holder.level1.setText(level1);
        holder.level2.setText(level2);
        holder.level3.setText(level3);
        holder.level4.setText(level4);
        holder.level5.setText(level5);
        holder.level6.setText(level6);
        holder.level7.setText(level7);
        holder.level8.setText(level8);
        holder.level9.setText(level9);
        holder.level10.setText(level10);

        holder.gpmxpm1.setText(gpmxpm1);
        holder.gpmxpm2.setText(gpmxpm2);
        holder.gpmxpm3.setText(gpmxpm3);
        holder.gpmxpm4.setText(gpmxpm4);
        holder.gpmxpm5.setText(gpmxpm5);
        holder.gpmxpm6.setText(gpmxpm6);
        holder.gpmxpm7.setText(gpmxpm7);
        holder.gpmxpm8.setText(gpmxpm8);
        holder.gpmxpm9.setText(gpmxpm9);
        holder.gpmxpm10.setText(gpmxpm10);

        Picasso.with(mContext).load(hero1imageUrl).transform(new CircularTransformation(30)).into(holder.player1Hero);
        Picasso.with(mContext).load(hero2imageUrl).transform(new CircularTransformation(30)).into(holder.player2Hero);
        Picasso.with(mContext).load(hero3imageUrl).transform(new CircularTransformation(30)).into(holder.player3Hero);
        Picasso.with(mContext).load(hero4imageUrl).transform(new CircularTransformation(30)).into(holder.player4Hero);
        Picasso.with(mContext).load(hero5imageUrl).transform(new CircularTransformation(30)).into(holder.player5Hero);
        Picasso.with(mContext).load(hero6imageUrl).transform(new CircularTransformation(30)).into(holder.player6Hero);
        Picasso.with(mContext).load(hero7imageUrl).transform(new CircularTransformation(30)).into(holder.player7Hero);
        Picasso.with(mContext).load(hero8imageUrl).transform(new CircularTransformation(30)).into(holder.player8Hero);
        Picasso.with(mContext).load(hero9imageUrl).transform(new CircularTransformation(30)).into(holder.player9Hero);
        Picasso.with(mContext).load(hero10imageUrl).transform(new CircularTransformation(30)).into(holder.player10Hero);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(showingFirst) {
                    holder.rel.setVisibility(View.VISIBLE);
                    showingFirst = false;
                }else{
                    holder.rel.setVisibility(View.GONE);
                    showingFirst = true;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGamesID.size();
    }

    public class LiveGamesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView TeamName1;
        private TextView TeamName2;
        private TextView Score;
        private TextView playerName10;
        private TextView playerName1;
        private TextView playerName2;
        private TextView playerName3;
        private TextView playerName4;
        private TextView playerName5;
        private TextView playerName6;
        private TextView playerName7;
        private TextView playerName8;
        private TextView playerName9;
        private TextView playerScore10;
        private TextView playerScore1;
        private TextView playerScore2;
        private TextView playerScore3;
        private TextView playerScore4;
        private TextView playerScore5;
        private TextView playerScore6;
        private TextView playerScore7;
        private TextView playerScore8;
        private TextView playerScore9;
        private ImageView player1Hero;
        private ImageView player2Hero;
        private ImageView player3Hero;
        private ImageView player4Hero;
        private ImageView player5Hero;
        private ImageView player6Hero;
        private ImageView player7Hero;
        private ImageView player8Hero;
        private ImageView player9Hero;
        private ImageView player10Hero;
        private  TextView hero1;
        private  TextView hero2;
        private  TextView hero3;
        private  TextView hero4;
        private  TextView hero5;
        private  TextView hero6;
        private  TextView hero7;
        private  TextView hero8;
        private  TextView hero9;
        private  TextView hero10;
        private TextView networth1;
        private TextView networth2;
        private TextView networth3;
        private TextView networth4;
        private TextView networth5;
        private TextView networth6;
        private TextView networth7;
        private TextView networth8;
        private TextView networth9;
        private TextView networth10;
        private TextView level1;
        private TextView level2;
        private TextView level3;
        private TextView level4;
        private TextView level5;
        private TextView level6;
        private TextView level7;
        private TextView level8;
        private TextView level9;
        private TextView level10;
        private TextView gpmxpm1;
        private TextView gpmxpm2;
        private TextView gpmxpm3;
        private TextView gpmxpm4;
        private TextView gpmxpm5;
        private TextView gpmxpm6;
        private TextView gpmxpm7;
        private TextView gpmxpm8;
        private TextView gpmxpm9;
        private TextView gpmxpm10;

        private Button button;
        private RelativeLayout rel;

        public LiveGamesViewHolder(View itemView) {
            super(itemView);
            TeamName1 = (TextView) itemView.findViewById(R.id.team1_name);
            TeamName2 = (TextView) itemView.findViewById(R.id.team2_name);
            Score = (TextView) itemView.findViewById(R.id.score);
            playerName1 = (TextView) itemView.findViewById(R.id.radiant_hero1_name);
            playerName2 = (TextView) itemView.findViewById(R.id.radiant_hero2_name);
            playerName3 = (TextView) itemView.findViewById(R.id.radiant_hero3_name);
            playerName4 = (TextView) itemView.findViewById(R.id.radiant_hero4_name);
            playerName5 = (TextView) itemView.findViewById(R.id.radiant_hero5_name);
            playerName6 = (TextView) itemView.findViewById(R.id.radiant_hero6_name);
            playerName7 = (TextView) itemView.findViewById(R.id.radiant_hero7_name);
            playerName8 = (TextView) itemView.findViewById(R.id.radiant_hero8_name);
            playerName9 = (TextView) itemView.findViewById(R.id.radiant_hero9_name);
            playerName10 = (TextView) itemView.findViewById(R.id.radiant_hero10_name);
            playerScore1 = (TextView) itemView.findViewById(R.id.kda_hero_1);
            playerScore2 = (TextView) itemView.findViewById(R.id.kda_hero_2);
            playerScore3 = (TextView) itemView.findViewById(R.id.kda_hero_3);
            playerScore4 = (TextView) itemView.findViewById(R.id.kda_hero_4);
            playerScore5 = (TextView) itemView.findViewById(R.id.kda_hero_5);
            playerScore6 = (TextView) itemView.findViewById(R.id.kda_hero_6);
            playerScore7 = (TextView) itemView.findViewById(R.id.kda_hero_7);
            playerScore8 = (TextView) itemView.findViewById(R.id.kda_hero_8);
            playerScore9 = (TextView) itemView.findViewById(R.id.kda_hero_9);
            playerScore10 = (TextView) itemView.findViewById(R.id.kda_hero_10);
            player1Hero = (ImageView) itemView.findViewById(R.id.radiant_hero1_image);
            player2Hero = (ImageView) itemView.findViewById(R.id.radiant_hero2_image);
            player3Hero = (ImageView) itemView.findViewById(R.id.radiant_hero3_image);
            player4Hero = (ImageView) itemView.findViewById(R.id.radiant_hero4_image);
            player5Hero = (ImageView) itemView.findViewById(R.id.radiant_hero5_image);
            player6Hero = (ImageView) itemView.findViewById(R.id.radiant_hero6_image);
            player7Hero = (ImageView) itemView.findViewById(R.id.radiant_hero7_image);
            player8Hero = (ImageView) itemView.findViewById(R.id.radiant_hero8_image);
            player9Hero = (ImageView) itemView.findViewById(R.id.radiant_hero9_image);
            player10Hero = (ImageView) itemView.findViewById(R.id.radiant_hero10_image);

            hero1 = (TextView) itemView.findViewById(R.id.radiant_hero1);
            hero2 = (TextView) itemView.findViewById(R.id.radiant_hero2);
            hero3 = (TextView) itemView.findViewById(R.id.radiant_hero3);
            hero4 = (TextView) itemView.findViewById(R.id.radiant_hero4);
            hero5 = (TextView) itemView.findViewById(R.id.radiant_hero5);
            hero6 = (TextView) itemView.findViewById(R.id.radiant_hero6);
            hero7 = (TextView) itemView.findViewById(R.id.radiant_hero7);
            hero8 = (TextView) itemView.findViewById(R.id.radiant_hero8);
            hero9 = (TextView) itemView.findViewById(R.id.radiant_hero9);
            hero10 = (TextView) itemView.findViewById(R.id.radiant_hero10);

            networth1 = (TextView) itemView.findViewById(R.id.networth_hero_1);
            networth2 = (TextView) itemView.findViewById(R.id.networth_hero_2);
            networth3 = (TextView) itemView.findViewById(R.id.networth_hero_3);
            networth4 = (TextView) itemView.findViewById(R.id.networth_hero_4);
            networth5 = (TextView) itemView.findViewById(R.id.networth_hero_5);
            networth6 = (TextView) itemView.findViewById(R.id.networth_hero_6);
            networth7 = (TextView) itemView.findViewById(R.id.networth_hero_7);
            networth8 = (TextView) itemView.findViewById(R.id.networth_hero_8);
            networth9 = (TextView) itemView.findViewById(R.id.networth_hero_9);
            networth10 = (TextView) itemView.findViewById(R.id.networth_hero_10);

            level1 = (TextView) itemView.findViewById(R.id.level_hero_1);
            level2 = (TextView) itemView.findViewById(R.id.level_hero_2);
            level3 = (TextView) itemView.findViewById(R.id.level_hero_3);
            level4 = (TextView) itemView.findViewById(R.id.level_hero_4);
            level5 = (TextView) itemView.findViewById(R.id.level_hero_5);
            level6 = (TextView) itemView.findViewById(R.id.level_hero_6);
            level7 = (TextView) itemView.findViewById(R.id.level_hero_7);
            level8 = (TextView) itemView.findViewById(R.id.level_hero_8);
            level9 = (TextView) itemView.findViewById(R.id.level_hero_9);
            level10 = (TextView) itemView.findViewById(R.id.level_hero_10);

            gpmxpm1 = (TextView) itemView.findViewById(R.id.gpm_hero_1);
            gpmxpm2 = (TextView) itemView.findViewById(R.id.gpm_hero_2);
            gpmxpm3 = (TextView) itemView.findViewById(R.id.gpm_hero_3);
            gpmxpm4 = (TextView) itemView.findViewById(R.id.gpm_hero_4);
            gpmxpm5 = (TextView) itemView.findViewById(R.id.gpm_hero_5);
            gpmxpm6 = (TextView) itemView.findViewById(R.id.gpm_hero_6);
            gpmxpm7 = (TextView) itemView.findViewById(R.id.gpm_hero_7);
            gpmxpm8 = (TextView) itemView.findViewById(R.id.gpm_hero_8);
            gpmxpm9 = (TextView) itemView.findViewById(R.id.gpm_hero_9);
            gpmxpm10 = (TextView) itemView.findViewById(R.id.gpm_hero_10);

            button = (Button) itemView.findViewById(R.id.button);
            rel = (RelativeLayout) itemView.findViewById(R.id.slider);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mClickHandler.onClick(position);
        }
    }

    public void setNewData(ArrayList<LiveGamesActivity.MatchInfo> GamesID){
        mGamesID = GamesID;
        notifyDataSetChanged();
    }


}
