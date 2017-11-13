package com.project.akejufatai.craze;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKEJU  FATAI on 2017-05-18.
 */

public class RunnerCollection {

    private List<Runner> runners;

    public RunnerCollection(){

        runners = new ArrayList<>();

    }

    public void add(Runner runner){

        runners.add(runner);

    }

    public Runner get(int index){

        if(index < size()){
            return runners.get(index);
        }
        throw new IndexOutOfBoundsException();

    }

    public void remove(int index){

        if(index < size()){
            runners.remove(index);
        }
        else{
            throw new IndexOutOfBoundsException();
        }

    }

    public int size(){

        return runners.size();

    }

    public void resetLocation(double x){

        for(Runner runner : runners){
            Location currentLocation = runner.getLocation();
            currentLocation.setX(x);
            runner.setLocation(currentLocation);
        }

    }

    public void update(){

        // temporary
        for(Runner runner : runners){
            runner.update();
            //Location runnerLocation = runner.getLocation();
            //runnerLocation.setX(runnerLocation.getX()+15);
        }

    }

    public void draw(Canvas canvas){

        for(Runner runner : runners){
            runner.draw(canvas);
        }

    }

}
