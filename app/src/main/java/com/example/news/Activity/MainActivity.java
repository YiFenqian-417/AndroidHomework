package com.example.news.Activity;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.news.Api.ApiClient;
import com.example.news.R;
import com.example.news.RecycleView.Adapter;
import com.example.news.JsonClass.News;
import com.example.news.JsonClass.Data;
import com.example.news.menu.BuilderManager;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarX;
import com.zackratos.ultimatebarx.ultimatebarx.bean.BarBackground;

import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private BoomMenuButton bmb;
    final  String API_KEY="3f54149bb0653a3061748d46ddf0c2dd";
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;
    Adapter adapter;
    List<Data> data = new ArrayList<>();
    String type="caijing";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UltimateBarX.with(this)
                .fitWindow(true)
                .light(true)
                .color(Color.TRANSPARENT)
                .drawableRes(R.drawable.bg3)
                .applyStatusBar();

        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout = findViewById(R.id.refresh);
        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.SimpleCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_9_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_9_1);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            addBuilder();
        }

        Intent intent = getIntent();
        type=intent.getStringExtra("type");
        SQLiteDatabase db = Connector.getDatabase();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                retrieveJson(type,API_KEY);
            }
        });
        retrieveJson(type,API_KEY);
    }

    public void retrieveJson(String type,String apiKey){
        swipeRefreshLayout.setRefreshing(true);
        Call<News> call = ApiClient.getInstance().getApi().getHeadLines(type,apiKey);
            Log.d(TAG,call.toString());
            call.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    swipeRefreshLayout.setRefreshing(false);
                    if(response.isSuccessful() && response.body().getResult()!=null){
                        data.clear();
                        data =response.body().getResult().getData();
                        adapter=new Adapter(MainActivity.this, data);
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    swipeRefreshLayout.setRefreshing(false);
                    Log.d(TAG,t.getLocalizedMessage());
                    Toast.makeText(MainActivity.this,t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            });
    }
    private void addBuilder() {
        bmb.addBuilder(new SimpleCircleButton.Builder()
                .normalImageRes(BuilderManager.getImageResource())
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        switch (index){
                            case 0:
                                Log.d(TAG, "onBoomButtonClick: 0");
                                Intent intent = getIntent();
                                intent.putExtra("type","top");
                                finish();
                                startActivity(intent);
                                break;
                            case 1:
                                Log.d(TAG, "onBoomButtonClick: 1");
                                Intent intent1 = new Intent(MainActivity.this,ClickedHistory.class);
                                startActivity(intent1);
                                break;
                            case 2:
                                Log.d(TAG, "onBoomButtonClick: 2");
                                Intent intent2 = new Intent(MainActivity.this, Person.class);
                                startActivity(intent2);
                                break;
                            case 3:
                                Log.d(TAG, "onBoomButtonClick: 3");
                                Intent intent3 = getIntent();
                                intent3.putExtra("type","guonei");
                                finish();
                                startActivity(intent3);
                                break;
                            case 4:
                                Log.d(TAG, "onBoomButtonClick: 4");
                                Intent intent4 = getIntent();
                                intent4.putExtra("type","guoji");
                                finish();
                                startActivity(intent4);
                                break;
                            case 5:
                                Log.d(TAG, "onBoomButtonClick: 5");
                                Intent intent5 = getIntent();
                                intent5.putExtra("type","caijing");
                                finish();
                                startActivity(intent5);
                                break;
                            case 6:
                                Log.d(TAG, "onBoomButtonClick: 6");
                                Intent intent6 = getIntent();
                                intent6.putExtra("type","tiyu");
                                finish();
                                startActivity(intent6);
                                break;
                            case 7:
                                Log.d(TAG, "onBoomButtonClick: 7");
                                Intent intent7 = getIntent();
                                intent7.putExtra("type","junshi");
                                finish();
                                startActivity(intent7);
                                break;
                            case 8:
                                Log.d(TAG, "onBoomButtonClick: 8");
                                Intent intent8 = getIntent();
                                intent8.putExtra("type","youxi");
                                finish();
                                startActivity(intent8);
                                break;
                        }
                    }
                }));
    }

}