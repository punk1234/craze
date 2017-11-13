package com.project.akejufatai.craze;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by AKEJU  FATAI on 2017-05-14.
 */

public class LineDivider extends GameObject  {

    private Paint paint;

    public LineDivider(Location location, Dimension dimension){

        super(location, dimension);

        paint = new Paint();
        paint.setARGB(255,255,255,255);

    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    @Override
    public void draw(Canvas canvas) {

        int savedState = canvas.save();
        // canvas.scale(0.5f,05.f);
        float left = (float) location.getX();
        float top = (float) location.getY();
        float right = left + (float) dimension.getWidth();
        float bottom = top + (float) dimension.getHeight();
        canvas.drawRect(left,top,right,bottom,paint);
        canvas.restoreToCount(savedState);

    }

}
