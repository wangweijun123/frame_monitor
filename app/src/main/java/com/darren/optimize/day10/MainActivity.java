package com.darren.optimize.day10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//         SlowMethodMonitor slowMethodMonitor = new SlowMethodMonitor();
//         slowMethodMonitor.start();
        ChoreographerMonitor choreographerMonitor = new ChoreographerMonitor();
        choreographerMonitor.start();

//        sleep(200*1000);
    }

    public void click(View view) {
        //
        Log.e(ChoreographerMonitor.TAG,"--->模拟耗时操作");
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                // handler 关联的looper对象，与在在哪里new Handler对象是没有任何关系的
                Handler handler = new Handler(Looper.getMainLooper()) {
                    @Override
                    public void handleMessage(@NonNull Message msg) {
                        super.handleMessage(msg);
                        Log.e(ChoreographerMonitor.TAG,msg.what + "  thread name:"+ Thread.currentThread().getName());
                    }
                };
                handler.sendEmptyMessage(100);

            }
        }).start();*/


        EditText et = (EditText)findViewById(R.id.et);
        int timeMS = Integer.parseInt(et.getText().toString());
        sleep(timeMS);

    }

    private void sleep(long timeMS) {
        try {

            // 160ms 丢了10帧  320ms 20帧
            Log.e(ChoreographerMonitor.TAG,"睡觉 timeMS: " + timeMS);
            //如果在主线程sleep,出现 明白两点: 阻塞主线程: 掉帧，甚至ANR
            Thread.sleep(timeMS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 解决，想为什么慢，慢在哪里，跟当时场景
    }
}
