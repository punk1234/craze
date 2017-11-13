package com.project.akejufatai.craze;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        GameScreenLauncher gameScreenLauncher = new GameScreenLauncher(this);
        Thread thread = new Thread(gameScreenLauncher);
        thread.start();

    }

    private class GameScreenLauncher implements Runnable{

        private Context context;

        public GameScreenLauncher(Context context){

            this.context = context;

        }

        @Override
        public void run() {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(context,PlayActivity.class);
            startActivity(intent);

        }

    }

}
