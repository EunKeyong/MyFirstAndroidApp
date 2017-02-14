package com.example.byg.exam_0120.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.byg.exam_0120.R;

public class BasketballActivity_2 extends AppCompatActivity implements View.OnClickListener{
    public static final int POINT_3 = 3;
    public static final int POINT_2 = 2;
    public static final int POINT_FREE = 1;

    // 전체점수 나타내는 변수
    private int mteamAscore;
    private int mteamBscore;

    private TextView mTeam1ScroeText;
    private TextView mTeam2ScroeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basketball);

        findViewById(R.id.team1_button1).setOnClickListener(this);
        findViewById(R.id.team1_button2).setOnClickListener(this);
        findViewById(R.id.team1_button3).setOnClickListener(this);
        findViewById(R.id.team2_button1).setOnClickListener(this);
        findViewById(R.id.team2_button2).setOnClickListener(this);
        findViewById(R.id.team2_button3).setOnClickListener(this);
        findViewById(R.id.reset_button).setOnClickListener(this);

        mTeam1ScroeText = (TextView) findViewById(R.id.team1_score_text);
        mTeam2ScroeText = (TextView) findViewById(R.id.team2_score_text);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.team1_button1:
                mteamAscore += POINT_3;
                threePointMessage();
                break;
            case R.id.team1_button2:
                mteamAscore += POINT_2;
                twoPointMessage();
                break;
            case R.id.team1_button3:
                mteamAscore += POINT_FREE;
                onePointMessage();
                break;
            case R.id.team2_button1:
                mteamBscore += POINT_3;
                threePointMessage();
                break;
            case R.id.team2_button2:
                mteamBscore += POINT_2;
                twoPointMessage();
                break;
            case R.id.team2_button3:
                mteamBscore += POINT_FREE;
                onePointMessage();
                break;
            case R.id.reset_button:
                init();
                resetMessage();
                break;
            default:
        }
        displayResult();
    }
    private void displayResult() {
        mTeam1ScroeText.setText("" + mteamAscore);
        mTeam2ScroeText.setText("" + mteamBscore);
    }
    private void threePointMessage() {
        Toast.makeText(BasketballActivity_2.this, "3점 슛", Toast.LENGTH_SHORT).show();
    }
    private void twoPointMessage() {
        Toast.makeText(BasketballActivity_2.this, "2점 슛", Toast.LENGTH_SHORT).show();
    }
    private void onePointMessage() {
        Toast.makeText(BasketballActivity_2.this, "1점 슛", Toast.LENGTH_SHORT).show();
    }
    private void resetMessage() {
        Toast.makeText(BasketballActivity_2.this, "경기 다시 시작", Toast.LENGTH_SHORT).show();
    }
    private void init() {
        mteamAscore = 0;
        mteamBscore = 0;
    }
}
