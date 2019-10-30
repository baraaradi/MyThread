package com.example.asus.mythread;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";

    private Button btnStartThread;

    private Handler mainHandler = new Handler();

    private volatile boolean  stopThread= false;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnStartThread=(Button)findViewById(R.id.button_start_thread);

    }


    public void startThread(View view)
    {
        stopThread=false;
        RunnableThread runnable = new RunnableThread(10);
        new Thread(runnable).start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: ");

            }
        }).start(); */




    }

    public void stopThread(View view) {
        stopThread=true;
    }



    public class RunnableThread  implements Runnable
    {
        int seconds;

        public RunnableThread(int seconds)
        {
            this.seconds=seconds;

        }

        @Override
        public void run() {
            for (int i=0; i<seconds;i++)

            {if(stopThread)
                return;
                if(i==5)
                {
                    /*Handler threadHandler = new Handler(Looper.getMainLooper());*/

                    /*btnStartThread.post(new Runnable() {
                        @Override
                        public void run() {
                            btnStartThread.setText("50%");



                        }
                    });*/
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            btnStartThread.setText("50%");


                        }
                    });
                }
                Log.d(TAG, "startThread: " + i);

                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

        }
    }


}