package com.project.akejufatai.craze;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by AKEJU  FATAI on 2017-05-14.
 */

public class Runner extends GameObject {

    private double speed;
    private int position;
    private static AnimationFrameSet frameSet;
    private int currentFrameIndex;
    private static final int LAST_FRAME_INDEX = 4;
    //private static Dimension dimension;

    public Runner(Location location, Dimension dimension, int position){

        this(location, dimension,position,1);

    }

    public Runner(Location location, Dimension dimension, int position, double speed){

        super(location, dimension);
        this.position = position;
        this.speed = speed;
        currentFrameIndex = 0;

    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getPosition(){

        return position;

    }

    public void setPosition(int position){

        this.position = position;

    }

    public static AnimationFrameSet getFrameSet() {
        return frameSet;
    }

    public static void setFrameSet(AnimationFrameSet frame_set) {
        frameSet = frame_set;
    }

    public void dragBack(){

        updateLocation(-200);

    }

    public void dragFront(){

        updateLocation(200);

    }

    public void updateLocation(double value){

        double x = location.getX();
        x = x + value;
        location.setX(x);

    }

    public void update(){

        double currentLocationX = location.getX();
        location.setX(currentLocationX + speed);

    }

    @Override
    public void draw(Canvas canvas) {

        //int savedState = canvas.save();
        //canvas.scale(0.5f,0.5f);
        canvas.drawBitmap(getCurrentFrame(),(float)location.getX(),(float)location.getY(),null);
        //canvas.restoreToCount(savedState);

    }

    private Bitmap getCurrentFrame(){

        Bitmap bitmap;
        if(currentFrameIndex == LAST_FRAME_INDEX){
            bitmap = getFrameSet().get(currentFrameIndex);
            currentFrameIndex = 1;
        }
        else{
            bitmap = getFrameSet().get(currentFrameIndex);
            currentFrameIndex++;
        }
        return bitmap;

    }

}
