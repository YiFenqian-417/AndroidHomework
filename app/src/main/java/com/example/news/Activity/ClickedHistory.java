package com.example.news.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.Toast;

import com.example.news.R;
import com.example.news.RecycleView.HistoryAdapter;
import com.example.news.litepal.History;
import com.example.news.menu.BuilderManager;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.SimpleCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.zackratos.ultimatebarx.ultimatebarx.UltimateBarX;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ClickedHistory extends AppCompatActivity {

    private static final String TAG = "ClickedHistoryActivity";
    RecyclerView recyclerView;
    HistoryAdapter historyAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    List<History> historyList = new ArrayList<>();
    private BoomMenuButton bmb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicked_history);

        UltimateBarX.with(this)
                .fitWindow(true)
                .light(true)
                .color(Color.TRANSPARENT)
                .drawableRes(R.drawable.bg3)
                .applyStatusBar();

        bmb = (BoomMenuButton) findViewById(R.id.bmb);
        assert bmb != null;
        bmb.setButtonEnum(ButtonEnum.SimpleCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_9_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_9_1);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            addBuilder();
        }


        swipeRefreshLayout =findViewById(R.id.refresh);
        Button button = (Button) findViewById(R.id.Button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int flag = LitePal.deleteAll(History.class);
                if(flag>=1)Toast.makeText(ClickedHistory.this,"恭喜你，删除成功",Toast.LENGTH_SHORT).show();
                else Toast.makeText(ClickedHistory.this,"很遗憾，删除失败",Toast.LENGTH_SHORT).show();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                RecyclerShow();
            }
        });
        RecyclerShow();

    }

    public void RecyclerShow(){
        recyclerView = findViewById(R.id.historyrecycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyList = LitePal.findAll(History.class);
        Collections.reverse(historyList);
        for(History model: historyList){
            Log.d(TAG, "onCreate: "+model.getTitle());
        }

        historyAdapter = new HistoryAdapter(ClickedHistory.this,historyList);
        recyclerView.setAdapter(historyAdapter);
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
                                Intent intent = new Intent(ClickedHistory.this,MainActivity.class);
                                intent.putExtra("type","top");
                                finish();
                                startActivity(intent);
                                break;
                            case 1:
                                Log.d(TAG, "onBoomButtonClick: 1");
                                Intent intent1 = getIntent();
                                startActivity(intent1);
                                finish();
                                break;
                            case 2:
                                Log.d(TAG, "onBoomButtonClick: 2");
                                Intent intent2 = new Intent(ClickedHistory.this, Person.class);
                                startActivity(intent2);
                                break;
                            case 3:
                                Log.d(TAG, "onBoomButtonClick: 3");
                                Intent intent3 = new Intent(ClickedHistory.this,MainActivity.class);
                                intent3.putExtra("type","guonei");
                                finish();
                                startActivity(intent3);
                                break;
                            case 4:
                                Log.d(TAG, "onBoomButtonClick: 4");
                                Intent intent4 = new Intent(ClickedHistory.this,MainActivity.class);
                                intent4.putExtra("type","guoji");
                                finish();
                                startActivity(intent4);
                                break;
                            case 5:
                                Log.d(TAG, "onBoomButtonClick: 5");
                                Intent intent5 = new Intent(ClickedHistory.this,MainActivity.class);
                                intent5.putExtra("type","caijing");
                                finish();
                                startActivity(intent5);
                                break;
                            case 6:
                                Log.d(TAG, "onBoomButtonClick: 6");
                                Intent intent6 = new Intent(ClickedHistory.this,MainActivity.class);
                                intent6.putExtra("type","tiyu");
                                finish();
                                startActivity(intent6);
                                break;
                            case 7:
                                Log.d(TAG, "onBoomButtonClick: 7");
                                Intent intent7 = new Intent(ClickedHistory.this,MainActivity.class);
                                intent7.putExtra("type","junshi");
                                finish();
                                startActivity(intent7);
                                break;
                            case 8:
                                Log.d(TAG, "onBoomButtonClick: 8");
                                Intent intent8 = new Intent(ClickedHistory.this,MainActivity.class);
                                intent8.putExtra("type","youxi");
                                finish();
                                startActivity(intent8);
                                break;
                        }
                    }
                }));
    }

}