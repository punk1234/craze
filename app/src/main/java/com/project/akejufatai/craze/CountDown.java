package com.project.akejufatai.craze;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by AKEJU  FATAI on 2017-06-04.
 */

public class CountDown {

    private Canvas canvas;

    public CountDown(Canvas canvas){

        this.canvas = canvas;

    }

    public void start(){

        int textValue = 3;
        while(textValue > 0){
            String text = toString(textValue);
            canvas.drawText(text,500,500,new Paint());
            textValue--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private String toString(int value){

        return (value + "");

    }

}
