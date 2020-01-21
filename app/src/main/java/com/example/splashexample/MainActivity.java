package com.example.splashexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Button btn_start, btn_stop, btn_pause, btn_resume;
    private ImageView iv_jw_blue, iv_jw_yellow, iv_jw_red, iv_jw_green, iv_jw_purple,
            iv_chart, iv_favorite, iv_setting;
    private LinearLayout llo_timer, llo_jw, llo_start, llo_stop_pause;
    // 타이머 준비물
    TextView tv_second, tv_minute, tv_time;
    TimerTask timerTask;
    Timer timer = new Timer();

    final static int init = 0;
    final static int run = 1;
    final static int pause = 2;

    int cur_Status = init;
    int myCount = 1;
    long myBaseTime;
    long myPauseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_time = (TextView) findViewById(R.id.tv_time);

        ImageView.OnClickListener onClickListener = new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_start:
                        myBaseTime = SystemClock.elapsedRealtime();
                        System.out.println(myBaseTime);
                        myTimer.sendEmptyMessage(0);
                        //cur_Status = run;

                        llo_start.setVisibility(View.INVISIBLE);
                        llo_stop_pause.setVisibility(View.VISIBLE);
                        llo_timer.setVisibility(View.VISIBLE);
                        llo_jw.setVisibility(View.INVISIBLE);

                        break;
                    case R.id.btn_stop:
                        myTimer.removeMessages(0);
                        //cur_Status = init;
                        tv_time.setText("00:00:00");

                        llo_start.setVisibility(View.VISIBLE);
                        llo_start.setVisibility(View.VISIBLE);
                        llo_timer.setVisibility(View.INVISIBLE);
                        llo_jw.setVisibility(View.VISIBLE);
                        btn_pause.setVisibility(View.VISIBLE);
                        btn_resume.setVisibility(View.INVISIBLE);

                        break;
                    case R.id.btn_pause:
                        myTimer.removeMessages(0);
                        myPauseTime = SystemClock.elapsedRealtime();
                        //cur_Status = pause;

                        btn_pause.setVisibility(View.INVISIBLE);
                        btn_resume.setVisibility(View.VISIBLE);
                        break;
                    case R.id.btn_resume:
                        long now = SystemClock.elapsedRealtime();
                        myTimer.sendEmptyMessage(0);
                        myBaseTime += now - myPauseTime;
                        //cur_Status = run;

                        btn_pause.setVisibility(View.VISIBLE);
                        btn_resume.setVisibility(View.INVISIBLE);

                        break;
                    case R.id.iv_jw_blue:
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                    case R.id.iv_jw_yellow:
                    case R.id.iv_jw_red:
                    case R.id.iv_jw_green:
                    case R.id.iv_jw_purple:
                    case R.id.iv_chart:
                    case R.id.iv_favorite:
                    case R.id.iv_setting:
                }
            }
        };
        iv_jw_blue = (ImageView) findViewById(R.id.iv_jw_blue);
        iv_jw_yellow = (ImageView) findViewById(R.id.iv_jw_yellow);
        iv_jw_red = (ImageView) findViewById(R.id.iv_jw_red);
        iv_jw_green = (ImageView) findViewById(R.id.iv_jw_green);
        iv_jw_purple = (ImageView) findViewById(R.id.iv_jw_purple);
        iv_chart = (ImageView) findViewById(R.id.iv_chart);
        iv_favorite = (ImageView) findViewById(R.id.iv_favorite);
        iv_setting = (ImageView) findViewById(R.id.iv_setting);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_pause = (Button) findViewById(R.id.btn_pause);
        btn_resume = (Button) findViewById(R.id.btn_resume);

        llo_timer = (LinearLayout) findViewById(R.id.llo_timer);
        llo_jw = (LinearLayout) findViewById(R.id.llo_jw);
        llo_start = (LinearLayout) findViewById(R.id.llo_start);
        llo_stop_pause = (LinearLayout) findViewById(R.id.llo_stop_pause);


        iv_jw_blue.setOnClickListener(onClickListener);
        iv_jw_yellow.setOnClickListener(onClickListener);
        iv_jw_red.setOnClickListener(onClickListener);
        iv_jw_green.setOnClickListener(onClickListener);
        iv_jw_purple.setOnClickListener(onClickListener);
        iv_chart.setOnClickListener(onClickListener);
        iv_favorite.setOnClickListener(onClickListener);
        iv_setting.setOnClickListener(onClickListener);
        btn_start.setOnClickListener(onClickListener);
        btn_stop.setOnClickListener(onClickListener);
        btn_pause.setOnClickListener(onClickListener);
        btn_resume.setOnClickListener(onClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    Handler myTimer = new Handler() {
        public void handleMessage(Message msg) {
            tv_time.setText(getTimeOut());
            myTimer.sendEmptyMessage(0);
        }
    };

    String getTimeOut() {
        long now = SystemClock.elapsedRealtime();
        long outTime = now - myBaseTime;
        String easy_outTime = String.format("%02d:%02d:%02d",
                outTime / 1000 / 60 / 60,
                outTime / 1000 / 60,
                (outTime / 1000) % 60);
        return easy_outTime;
    }
}
