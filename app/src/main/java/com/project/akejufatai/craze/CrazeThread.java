package com.project.akejufatai.craze;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.view.SurfaceHolder;

/**
 * Created by AKEJU  FATAI on 2017-05-18.
 */

public class CrazeThread extends Thread {

    private final int FPS = 10;
    private SurfaceHolder surfaceHolder;
    private RunnerCollection runners;
    private LineDividerCollection lineDividers;
    private PositionDisplayCollection positionDisplays;
    private Resources resources;
    private Context context;
    private boolean playing;
    private final Object lock = new Object();

    public CrazeThread(Resources resources, Context context, SurfaceHolder surfaceHolder, RunnerCollection runners,
                       LineDividerCollection lineDividers, PositionDisplayCollection positionDisplays){

        this.resources = resources;
        this.context = context;
        this.surfaceHolder = surfaceHolder;
        this.runners = runners;
        this.lineDividers = lineDividers;
        this.positionDisplays = positionDisplays;
        this.playing = true;

    }

    @Override
    public void run() {

        Bitmap spriteSheet = BitmapFactory.decodeResource(resources,R.drawable.crazy_runner_spritesheet);
        AnimationFrameSet animationFrameSet = new AnimationFrameSet(spriteSheet,3,3);
        Runner.setFrameSet(animationFrameSet);
        Canvas canvas = surfaceHolder.lockCanvas();
        int savedState = canvas.save();
        canvas.scale(0.5f,0.5f);
        canvas.drawARGB(255,100,100,255);
//        CountDown countDown = new CountDown(canvas);
//        countDown.start();
        //canvas.drawCircle(500,500,100,new Paint());
        Paint p = new Paint();
        p.setTextSize(300);
        // canvas.drawText("3",1000,500,p);
        lineDividers.draw(canvas);
        positionDisplays.draw(canvas);
        runners.draw(canvas);
        canvas.drawText("3333",(float)GameView.getDimension().getWidth(),(float)GameView.getDimension().getHeight(),p);
        canvas.drawText("9999",0,0,200,200,p);
        canvas.restoreToCount(savedState);
        surfaceHolder.unlockCanvasAndPost(canvas);

        int runnerSize = runners.size();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        double convertDenominator = 1000000;
        while(playing){
            double startTimeMillisec = System.nanoTime() / convertDenominator;
            canvas = surfaceHolder.lockCanvas();
            savedState = canvas.save();
            if(canvas != null){
                canvas.scale(0.5f,0.5f);
                canvas.drawARGB(255,100,100,255);
                lineDividers.draw(canvas);
                runners.update();
                runners.draw(canvas);
                canvas.restoreToCount(savedState);
                surfaceHolder.unlockCanvasAndPost(canvas);

                playing = !isGameOver(runnerSize);

                double endTimeMillisec = System.nanoTime() / convertDenominator;

                double deltaTime = endTimeMillisec - startTimeMillisec;
                double timeBetweenFrames = 100;
                if(deltaTime < timeBetweenFrames){
                    long remainingFrameTime = (long)timeBetweenFrames - (long)deltaTime;
                    try {
                        Thread.sleep(remainingFrameTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // display a win or lose
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("WIN");
                alertDialogBuilder.setNeutralButton("NEXT LEVEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // move to the next level
                        Intent intent = new Intent(context,PlayActivity.class);
                        context.startActivity(intent);
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });


        //super.run();
    }

//    private boolean isGameOver(){
//
//        for(int runnerIndex = 0; runnerIndex < runners.size(); runnerIndex++){
//            Runner runner = runners.get(runnerIndex);
//            Location location = runner.getLocation();
//            Dimension dimension = runner.getDimension();
//            double scaleFactor = 2; // change this to reference a default or static SCALE_FACTOR
//            if(location.getX() > GameView.getDimension().getWidth() * scaleFactor){
//                return true;
//            }
//        }
//        return false;
//
//    }

    private boolean isGameOver(int value){

        for(int runnerIndex = 0; runnerIndex < runners.size(); runnerIndex++){
            Runner runner = runners.get(runnerIndex);
            Location location = runner.getLocation();
            Dimension dimension = runner.getDimension();
            double scaleFactor = 2; // change this to reference a default or static SCALE_FACTOR
            if(location.getX() > GameView.getDimension().getWidth() * scaleFactor){
                int runnerPosition = runner.getPosition();
                runners.remove(runnerIndex);
                if(runnerPosition != (value - runners.size())){
                    return true;
                }
            }
        }
        return false;

    }

    public void stopThread(){

        playing = false;

    }

    public void pauseThread(){

        synchronized (lock){
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void resumeThread(){

        synchronized (lock){
            lock.notify();
        }

    }

}
