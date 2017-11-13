package com.project.akejufatai.craze;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by AKEJU  FATAI on 2017-08-25.
 */

public class PositionDisplay extends GameObject {

    private double radius;
    private String text;

    public PositionDisplay(Location location, double radius, String text){

        super(location,null);
        this.radius = radius;
        this.text = text;

    }

    @Override
    public void draw(Canvas canvas) {

        Paint circlePaint = new Paint();
        circlePaint.setColor(Color.WHITE);

        canvas.drawCircle((float) location.getX(),(float) location.getY(), (float) radius, circlePaint);

        Paint textPaint = new Paint();
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50);
        Rect rect = new Rect();
        textPaint.getTextBounds(text,0,text.length(),rect);

//        double drawX = location.getX() - (rect.width() / 2);
//        double drawY = location.getY() + (rect.height() / 2);
        double drawX = location.getX();
        double drawY = location.getY();
        canvas.drawText(text,(float) drawX,(float) drawY,textPaint);

    }

}
