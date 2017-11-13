package com.project.akejufatai.craze;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this,6);
        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        System.out.println("PAUSE GAME ACTIVITY");
//        gameView.pause();
//        gameView.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(new GameView(this,6));

//        if(gameView.getCrazeThread() != null){
//            gameView.resume();
//        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("RESTART GAME ACTIVITY");
        Log.i("info_tag", "Fuck you nigga");
        setContentView(new GameView(this,6));
    }

}
