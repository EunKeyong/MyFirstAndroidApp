package com.example.byg.exam_0120.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.byg.exam_0120.R;

public class BasketballActivity extends AppCompatActivity {

    public static final int POINT_3 = 3;
    public static final int POINT_2 = 2;
    public static final int POINT_FREE = 1;

    private Button mTeam1Button1;
    private Button mTeam1Button2;
    private Button mTeam1Button3;
    private Button mTeam2Button1;
    private Button mTeam2Button2;
    private Button mTeam2Button3;
    private Button mResetButton;

    private TextView mTeam1ScroeText;
    private TextView mTeam2ScroeText;

    // 전체점수 나타내는 변수
    private int mteamAscore;
    private int mteamBscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_basketball);
        if (getIntent() != null) {
            Uri data = this.getIntent().getData();
            if (data != null) {
                Toast.makeText(this, data.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        init();

        mTeam1Button1 = (Button) findViewById(R.id.team1_button1);
        mTeam1Button2 = (Button) findViewById(R.id.team1_button2);
        mTeam1Button3 = (Button) findViewById(R.id.team1_button3);
        mTeam2Button1 = (Button) findViewById(R.id.team2_button1);
        mTeam2Button2 = (Button) findViewById(R.id.team2_button2);
        mTeam2Button3 = (Button) findViewById(R.id.team2_button3);
        mResetButton = (Button) findViewById(R.id.reset_button);

        mTeam1ScroeText = (TextView) findViewById(R.id.team1_score_text);
        mTeam2ScroeText = (TextView) findViewById(R.id.team2_score_text);

        mTeam1Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mteamAscore += POINT_3;
                Toast.makeText(BasketballActivity.this, "3점 슛", Toast.LENGTH_SHORT).show();
                displayResult();
            }
        });
        mTeam1Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mteamAscore += POINT_2;
                Toast.makeText(BasketballActivity.this, "2점 슛", Toast.LENGTH_SHORT).show();
                displayResult();
            }
        });
        mTeam1Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mteamAscore += POINT_FREE;
                Toast.makeText(BasketballActivity.this, "1점 슛", Toast.LENGTH_SHORT).show();
                displayResult();
            }
        });
        mTeam2Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mteamBscore += POINT_3;
                Toast.makeText(BasketballActivity.this, "3점 슛", Toast.LENGTH_SHORT).show();
                displayResult();
            }
        });
        mTeam2Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mteamBscore += POINT_2;
                Toast.makeText(BasketballActivity.this, "2점 슛", Toast.LENGTH_SHORT).show();
                displayResult();
            }
        });
        mTeam2Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mteamBscore += POINT_FREE;
                Toast.makeText(BasketballActivity.this, "1점 슛", Toast.LENGTH_SHORT).show();
                displayResult();
            }
        });
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
                displayResult();
            }
        });
    }

    private void displayResult() {
        mTeam1ScroeText.setText("" + mteamAscore);
        mTeam2ScroeText.setText("" + mteamBscore);
    }

    private void init() {
        mteamAscore = 0;
        mteamBscore = 0;
    }
}
