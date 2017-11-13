package com.project.akejufatai.craze;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Random;

/**
 * Created by AKEJU  FATAI on 2017-05-13.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private Bitmap spriteSheet;
    private SurfaceHolder surfaceHolder;
    private static Dimension dimension;
    private RunnerCollection runners;
    private LineDividerCollection lineDividers;
    private PositionDisplayCollection positionDisplays;
    private CrazeThread crazeThread;
    private int runnerCount;
    private Context context;

    private GestureDetectorCompat gestureDetectorCompat;

    private double x1;
    private double y1;
    private double x2;
    private double y2;

    public GameView(Context context, int runnerCount) {
        super(context);
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        this.runnerCount = runnerCount;
        this.context = context;
        gestureDetectorCompat = new GestureDetectorCompat(context,new GameViewGestureListener());
        //dimension = new Dimension(this.getWidth(),this.getHeight());
    }

    public CrazeThread getCrazeThread(){

        return crazeThread;

    }

    public static Dimension getDimension(){

        return dimension;

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        dimension = new Dimension(getWidth(),getHeight());

        spriteSheet = BitmapFactory.decodeResource(getResources(),R.drawable.crazy_runner_spritesheet);

        RunnerCollection runners = new RunnerCollection();

        PositionDisplayCollection positionDisplays = new PositionDisplayCollection();

//        Location location1 = new Location(0,0,0);
//        Location location2 = new Location(0,200,0);
//        Location location3 = new Location(0,400,0);
//        Location location4 = new Location(0,600,0);
//        Location location5 = new Location(0,800,0);
//        Location location6 = new Location(0,1000,0);

        RunnerPositionRandomGenerator runnerPositionRandomGenerator = new RunnerPositionRandomGenerator(runnerCount);

        int scaleFactor = 2;

        int positionDisplayRadius = 60;

        double minimumSpeed = 5;
        double maximumSpeed = 20;
        Dimension runnerDimension = new Dimension(200,200);
        double laneHeight = dimension.getHeight() / runnerCount;
        for(int runnerIndex = 0; runnerIndex < runnerCount; runnerIndex++){
            double y = scaleFactor * runnerIndex * laneHeight;
            Location location = new Location(0,y,0);
            int runnerPosition = runnerPositionRandomGenerator.get(runnerIndex);
            Runner runner = new Runner(location,runnerDimension,runnerPosition,getRandomSpeed(minimumSpeed,maximumSpeed));
            runners.add(runner);

            Location positionDisplayLocation = new Location(dimension.getWidth(),(y + laneHeight), 0);
            String runnerPositionString = runnerPosition + "";
            PositionDisplay positionDisplay = new PositionDisplay(positionDisplayLocation,positionDisplayRadius,runnerPositionString);
            positionDisplays.add(positionDisplay);
        }

//        Runner runner1 = new Runner(location1,dim,getRandomSpeed(minimumSpeed,maximumSpeed));
//        Runner runner2 = new Runner(location2,dim,getRandomSpeed(minimumSpeed,maximumSpeed));
//        Runner runner3 = new Runner(location3,dim,getRandomSpeed(minimumSpeed,maximumSpeed));
//        Runner runner4 = new Runner(location4,dim,getRandomSpeed(minimumSpeed,maximumSpeed));
//        Runner runner5 = new Runner(location5,dim,getRandomSpeed(minimumSpeed,maximumSpeed));
//        Runner runner6 = new Runner(location6,dim,getRandomSpeed(minimumSpeed,maximumSpeed));
//        runners.add(runner1);
//        runners.add(runner2);
//        runners.add(runner3);
//        runners.add(runner4);
//        runners.add(runner5);
//        runners.add(runner6);

        LineDividerCollection lineDividers = new LineDividerCollection();
//        Location loc1 = new Location(0,200,0);
//        Location loc2 = new Location(0,400,0);
//        Location loc3 = new Location(0,600,0);
//        Location loc4 = new Location(0,800,0);
//        Location loc5 = new Location(0,1000,0);

        Dimension lineDividerDimension = new Dimension(this.dimension.getWidth() * 2,10);
        laneHeight = dimension.getHeight() / runnerCount;
        for(int lineDividerIndex = 1; lineDividerIndex < runnerCount; lineDividerIndex++){
            double y = scaleFactor * lineDividerIndex * laneHeight;
            Location location = new Location(0,y,0);
            LineDivider lineDivider = new LineDivider(location,lineDividerDimension);
            lineDividers.add(lineDivider);
        }

//        Dimension dimension = new Dimension(this.dimension.getWidth() * 2,10);
//        LineDivider lineDivider1 = new LineDivider(loc1,dimension);
//        LineDivider lineDivider2 = new LineDivider(loc2,dimension);
//        LineDivider lineDivider3 = new LineDivider(loc3,dimension);
//        LineDivider lineDivider4 = new LineDivider(loc4,dimension);
//        LineDivider lineDivider5 = new LineDivider(loc5,dimension);
//        lineDividers.add(lineDivider1);
//        lineDividers.add(lineDivider2);
//        lineDividers.add(lineDivider3);
//        lineDividers.add(lineDivider4);
//        lineDividers.add(lineDivider5);

        this.runners = runners;
        this.lineDividers = lineDividers;
        this.positionDisplays = positionDisplays;

        crazeThread = new CrazeThread(getResources(),context,surfaceHolder,runners,lineDividers,positionDisplays);
        crazeThread.start();

//        Canvas canvas = surfaceHolder.lockCanvas();
//        Bitmap bitmap = Bitmap.createBitmap(spriteSheet,0,0,200,200);
//        Paint paint = new Paint();
//        paint.setARGB(255,0,0,255);
//
//        Location location = new Location(0,200,0);
//        Dimension dimension = new Dimension(this.dimension.getWidth() * 2,10);
//        LineDivider lineDivider = new LineDivider(location,dimension);
//
//        int savedState = canvas.save();
//        canvas.scale(0.5f,0.5f);
//        canvas.drawARGB(255,100,100,255);
//        canvas.drawBitmap(bitmap,0,0,paint);
//        canvas.drawBitmap(bitmap,0,200,paint);
//        canvas.drawBitmap(bitmap,0,400,paint);
//        canvas.drawBitmap(bitmap,0,600,paint);
//        canvas.drawBitmap(bitmap,0,800,paint);
//        canvas.drawBitmap(bitmap,0,1000,paint);
//        lineDivider.draw(canvas);
//        canvas.restoreToCount(savedState);
//        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        System.out.println("surface destroyed");

    }

    private double getRandomSpeed(double minimumSpeed, double maximumSpeed){

        Random random = new Random();
        double speedDifference = maximumSpeed - minimumSpeed;
        double speed = minimumSpeed + Math.abs(random.nextInt() % (speedDifference + 1));
        return speed;

    }

//    @Override
//    public void draw(Canvas canvas) {
//        super.draw(canvas);
//        Bitmap bitmap = Bitmap.createBitmap(spriteSheet,0,0,200,200);
//        Paint paint = new Paint();
//        canvas.drawBitmap(bitmap,0,0,paint);
//
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // InputController inputController = new InputController(runners,lineDividers);
        // inputController.run(event);

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            x1 = event.getX();
            y1 = event.getY();
            return true;
        }

        if(event.getAction() == MotionEvent.ACTION_UP){
            x2 = event.getX();
            double changeInX = x2 - x1;
            if(changeInX == 0){
                // do nothing
            }
            else if(changeInX < 0){
                // get runner and drag back
                Runner runner = getRunner(y1);
                runner.dragBack();
                return true;
            }
            else{
                // get runner and drag forward
                Runner runner = getRunner(y1);
                runner.dragFront();
                return true;
            }
        }

        // gestureDetectorCompat.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    private Runner getRunner(double y1){

        int runnerCount = runners.size();
        double laneHeight = dimension.getHeight() / runnerCount;
        int runnerIndex = (int) (y1 / laneHeight);
        Runner runner = runners.get(runnerIndex);
        return runner;

    }

    public void stop(){

        crazeThread.stopThread();

    }

    public void pause(){

        crazeThread.pauseThread();

//        try {
//            crazeThread.wait();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    public void resume(){

        crazeThread.resumeThread();

        //crazeThread.notify();

    }

    private class GameViewGestureListener extends GestureDetector.SimpleOnGestureListener{

        @Override
        public boolean onDown(MotionEvent e) {
            return super.onDown(e);
        }



        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            x1 = e1.getX();
            y1 = e1.getY();
            x2 = e2.getX();
            double changeInX = x2 - x1;
            if(changeInX == 0){
                // do nothing
            }
            else if(changeInX < 0){
                // get runner and drag back
                Runner runner = getRunner(y1);
                runner.dragBack();
            }
            else{
                // get runner and drag forward
                Runner runner = getRunner(y1);
                runner.dragFront();
            }

            return true;
        }
    }

}
